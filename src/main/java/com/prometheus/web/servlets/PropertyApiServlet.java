package com.prometheus.web.servlets;

import com.prometheus.web.model.Property;
import com.prometheus.web.repo.PropertyRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.util.JsonUtils;
import com.prometheus.web.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/propiedades")
public class PropertyApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            // Obtener parámetros de paginación
            var p = Pagination.readParams(req);
            var pr = PropertyRepository.searchPage(p.q, p.page, p.size);
            
            // Crear respuesta con datos paginados
            var response = ApiResponse.success(pr);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (Exception e) {
            var response = ApiResponse.error("Error al obtener propiedades: " + e.getMessage());
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
            
            // Convertir JSON a objeto Property
            Property property = JsonUtils.readJsonRequest(json, Property.class);
            
            // Validar datos
            if (property.getNombre() == null || property.getNombre().trim().isEmpty()) {
                var response = ApiResponse.error("El nombre es requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Guardar propiedad
            PropertyRepository.save(property);
            
            var response = ApiResponse.success("Propiedad creada exitosamente", property);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (Exception e) {
            var response = ApiResponse.error("Error al crear propiedad: " + e.getMessage());
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
                var response = ApiResponse.error("ID de propiedad requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            long id = Long.parseLong(pathInfo.substring(1));
            
            // Leer JSON del request
            String json = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
            
            // Convertir JSON a objeto Property
            Property property = JsonUtils.readJsonRequest(json, Property.class);
            property.setId(id);
            
            // Verificar que existe
            Property existing = PropertyRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Propiedad no encontrada");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Actualizar
            PropertyRepository.update(property);
            
            var response = ApiResponse.success("Propiedad actualizada exitosamente", property);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al actualizar propiedad: " + e.getMessage());
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
                var response = ApiResponse.error("ID de propiedad requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            long id = Long.parseLong(pathInfo.substring(1));
            
            // Verificar que existe
            Property existing = PropertyRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Propiedad no encontrada");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            
            // Eliminar
            boolean deleted = PropertyRepository.deleteById(id);
            
            if (deleted) {
                var response = ApiResponse.success("Propiedad eliminada exitosamente");
                JsonUtils.writeJsonResponse(resp, response);
            } else {
                var response = ApiResponse.error("No se pudo eliminar la propiedad");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JsonUtils.writeJsonResponse(resp, response);
            }
            
        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al eliminar propiedad: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Manejar preflight requests para CORS
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
