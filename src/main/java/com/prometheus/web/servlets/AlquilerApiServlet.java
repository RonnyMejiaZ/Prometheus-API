package com.prometheus.web.servlets;

import com.prometheus.web.model.Alquiler;
import com.prometheus.web.repo.AlquilerRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.util.JsonUtils;
import com.prometheus.web.util.Pagination;
import com.prometheus.web.util.FileUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * PASO 2: Agregar @MultipartConfig
 * Esta anotación le dice al servlet que puede recibir archivos
 * maxFileSize: tamaño máximo de un archivo (5MB)
 * maxRequestSize: tamaño máximo de toda la petición (5MB)
 */
@MultipartConfig(
    maxFileSize = 5242880, // 5MB en bytes
    maxRequestSize = 5242880 // 5MB en bytes
)
@WebServlet("/api/alquileres/*")
public class AlquilerApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Si hay pathInfo, es para obtener un alquiler específico
            String pathInfo = req.getPathInfo();
            if (pathInfo != null && !pathInfo.equals("/")) {
                long id = Long.parseLong(pathInfo.substring(1));
                Alquiler alquiler = AlquilerRepository.findById(id);

                if (alquiler == null) {
                    var response = ApiResponse.error("Alquiler no encontrado");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    JsonUtils.writeJsonResponse(resp, response);
                    return;
                }

                var response = ApiResponse.success(alquiler);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            var p = Pagination.readParams(req);
            var pr = AlquilerRepository.searchPage(p.q, p.page, p.size);

            var response = ApiResponse.success(pr);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al obtener alquileres: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    /**
     * PASO 3: Modificar doPost para recibir archivos
     * 
     * Ahora el método detecta si la petición viene con archivos (multipart/form-data)
     * o solo con JSON. Si viene con archivos, los procesa y guarda.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Alquiler alquiler;
            String contentType = req.getContentType();

            // Verificar si la petición trae archivos
            if (contentType != null && contentType.toLowerCase().startsWith("multipart/form-data")) {
                // Si viene con archivos, usar parseMultipartRequest
                alquiler = parseMultipartRequest(req);
            } else {
                // Si no viene con archivos, procesar como JSON normal
                String json = req.getReader().lines()
                        .reduce("", (accumulator, actual) -> accumulator + actual);
                alquiler = JsonUtils.readJsonRequest(json, Alquiler.class);
            }

            if (alquiler.getPropiedadId() == 0 || alquiler.getInquilinoId() == 0) {
                var response = ApiResponse.error("ID de propiedad e inquilino son requeridos");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            AlquilerRepository.save(alquiler);

            var response = ApiResponse.success("Alquiler creado exitosamente", alquiler);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (Exception e) {
            var response = ApiResponse.error("Error al crear alquiler: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    /**
     * PASO 4: Modificar doPut para actualizar con archivos
     * 
     * Similar a doPost, pero también maneja la eliminación del archivo anterior
     * si se sube uno nuevo.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                var response = ApiResponse.error("ID de alquiler requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            long id = Long.parseLong(pathInfo.substring(1));

            // Buscar el alquiler existente primero
            Alquiler existing = AlquilerRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Alquiler no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            Alquiler alquiler;
            String contentType = req.getContentType();

            // Verificar si la petición trae archivos
            if (contentType != null && contentType.toLowerCase().startsWith("multipart/form-data")) {
                // Si viene con archivos, procesar multipart
                alquiler = parseMultipartRequest(req);
                alquiler.setId(id);
                
                // Si se subió un nuevo archivo, eliminar el anterior
                if (alquiler.getContrato() != null && !alquiler.getContrato().isEmpty()) {
                    // Si había un archivo anterior, eliminarlo
                    if (existing.getContrato() != null && !existing.getContrato().isEmpty()) {
                        FileUtils.deleteFile(existing.getContrato(), getServletContext().getRealPath("/"));
                    }
                } else {
                    // Si no se subió archivo nuevo, mantener el anterior
                    alquiler.setContrato(existing.getContrato());
                }
            } else {
                // Si no viene con archivos, procesar como JSON normal
                String json = req.getReader().lines()
                        .reduce("", (accumulator, actual) -> accumulator + actual);
                alquiler = JsonUtils.readJsonRequest(json, Alquiler.class);
                alquiler.setId(id);
                
                // Si no se envió contrato en el JSON, mantener el anterior
                if (alquiler.getContrato() == null || alquiler.getContrato().isEmpty()) {
                    alquiler.setContrato(existing.getContrato());
                }
            }

            AlquilerRepository.update(alquiler);

            var response = ApiResponse.success("Alquiler actualizado exitosamente", alquiler);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al actualizar alquiler: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                var response = ApiResponse.error("ID de alquiler requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            long id = Long.parseLong(pathInfo.substring(1));

            Alquiler existing = AlquilerRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Alquiler no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            boolean deleted = AlquilerRepository.deleteById(id);

            if (deleted) {
                // PASO 5: Eliminar el archivo asociado cuando se elimina el alquiler
                if (existing.getContrato() != null && !existing.getContrato().isEmpty()) {
                    FileUtils.deleteFile(existing.getContrato(), getServletContext().getRealPath("/"));
                }
                
                var response = ApiResponse.success("Alquiler eliminado exitosamente");
                JsonUtils.writeJsonResponse(resp, response);
            } else {
                var response = ApiResponse.error("No se pudo eliminar el alquiler");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JsonUtils.writeJsonResponse(resp, response);
            }

        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al eliminar alquiler: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    /**
     * PASO 6: Método auxiliar para procesar peticiones multipart
     * 
     * Este método extrae todos los datos del formulario multipart:
     * - Los campos normales (propiedadId, inquilinoId, etc.)
     * - El archivo (contrato)
     * 
     * Luego guarda el archivo y retorna el objeto Alquiler con la ruta del archivo.
     */
    private Alquiler parseMultipartRequest(HttpServletRequest req) throws IOException, ServletException {
        Alquiler alquiler = new Alquiler();
        
        // Extraer los parámetros del formulario
        String nombreStr = req.getParameter("nombre");
        String propiedadIdStr = req.getParameter("propiedadId");
        String inquilinoIdStr = req.getParameter("inquilinoId");
        String fechaInicioStr = req.getParameter("fechaInicio");
        String fechaFinStr = req.getParameter("fechaFin");
        String mesesStr = req.getParameter("meses");
        String montoMensualStr = req.getParameter("montoMensual");
        String personasStr = req.getParameter("personas");
        String activoStr = req.getParameter("activo");
        
        // Convertir y asignar los valores al objeto Alquiler
        if (nombreStr != null && !nombreStr.isEmpty()) {
            alquiler.setNombre(nombreStr);
        }
        
        if (propiedadIdStr != null && !propiedadIdStr.isEmpty()) {
            alquiler.setPropiedadId(Long.parseLong(propiedadIdStr));
        }
        
        if (inquilinoIdStr != null && !inquilinoIdStr.isEmpty()) {
            alquiler.setInquilinoId(Long.parseLong(inquilinoIdStr));
        }
        
        if (fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
            alquiler.setFechaInicio(LocalDate.parse(fechaInicioStr));
        }
        
        if (fechaFinStr != null && !fechaFinStr.isEmpty()) {
            alquiler.setFechaFin(LocalDate.parse(fechaFinStr));
        }
        
        if (mesesStr != null && !mesesStr.isEmpty()) {
            alquiler.setMeses(Integer.parseInt(mesesStr));
        }
        
        if (montoMensualStr != null && !montoMensualStr.isEmpty()) {
            alquiler.setMontoMensual(new BigDecimal(montoMensualStr));
        }
        
        if (personasStr != null && !personasStr.isEmpty()) {
            alquiler.setPersonas(Integer.parseInt(personasStr));
        }
        
        if (activoStr != null && !activoStr.isEmpty()) {
            alquiler.setActivo(Boolean.parseBoolean(activoStr));
        }
        
        // Extraer y guardar el archivo
        Part filePart = req.getPart("contrato");
        if (filePart != null && filePart.getSize() > 0) {
            // Guardar el archivo y obtener la ruta
            String filePath = FileUtils.saveFile(filePart, getServletContext().getRealPath("/"));
            // Guardar la ruta en el campo contrato
            alquiler.setContrato(filePath);
        }
        
        return alquiler;
    }
}
