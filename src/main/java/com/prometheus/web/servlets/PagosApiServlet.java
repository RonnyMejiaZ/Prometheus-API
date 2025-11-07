package com.prometheus.web.servlets;

import com.prometheus.web.model.Pago;
import com.prometheus.web.repo.PagoRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.util.JsonUtils;
import com.prometheus.web.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/pagos/*")
public class PagosApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            // Si hay pathInfo, es para obtener una propiedad específica
            String pathInfo = req.getPathInfo();
            if (pathInfo != null && !pathInfo.equals("/")) {
                long id = Long.parseLong(pathInfo.substring(1));
                Pago pago = PagoRepository.findById(id);
                
                if (pago == null) {
                    var response = ApiResponse.error("Pago no encontrado");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    JsonUtils.writeJsonResponse(resp, response);
                    return;
                }
                
                var response = ApiResponse.success(pago);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Si no hay pathInfo, listar todas con paginación
            var p = Pagination.readParams(req);
            var pr = PagoRepository.searchPage(p.q, p.page, p.size);
            
            // Crear respuesta con datos paginados
            var response = ApiResponse.success(pr);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al obtener pagos: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            // Leer JSON del request
            String json = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
            
            // Convertir JSON a objeto Pago
            Pago pago = JsonUtils.readJsonRequest(json, Pago.class);
            
            // Validar datos
            if (pago.getAlquilerId() == 0 || pago.getFechaPago() == null || pago.getMontoMensual() == null) {
                var response = ApiResponse.error("El alquiler ID, fecha de pago y monto mensual son requeridos");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Guardar propiedad
            PagoRepository.save(pago);
            
            var response = ApiResponse.success("Pago creado exitosamente", pago);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (Exception e) {
            var response = ApiResponse.error("Error al crear pago: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            // Obtener ID de la URL
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                var response = ApiResponse.error("ID de pago requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            long id = Long.parseLong(pathInfo.substring(1));
            
            // Leer JSON del request
            String json = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
            
            // Convertir JSON a objeto Pago
            Pago pago = JsonUtils.readJsonRequest(json, Pago.class);
            pago.setId(id);
            
            // Verificar que existe
            Pago existing = PagoRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Pago no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Actualizar
            PagoRepository.update(pago);
            
            var response = ApiResponse.success("Pago actualizado exitosamente", pago);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al actualizar pago: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            // Obtener ID de la URL
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                var response = ApiResponse.error("ID de pago requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            long id = Long.parseLong(pathInfo.substring(1));
            
            // Verificar que existe
            Pago existing = PagoRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Pago no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Eliminar
            boolean deleted = PagoRepository.deleteById(id);
            
            if (deleted) {
                var response = ApiResponse.success("Pago eliminado exitosamente");
                JsonUtils.writeJsonResponse(resp, response);
            } else {
                var response = ApiResponse.error("No se pudo eliminar el pago");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JsonUtils.writeJsonResponse(resp, response);
            }
            
        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al eliminar pago: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

}
