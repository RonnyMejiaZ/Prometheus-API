package com.prometheus.web.servlets;

import com.prometheus.web.model.Alquiler;
import com.prometheus.web.repo.AlquilerRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.util.JsonUtils;
import com.prometheus.web.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/alquileres")
public class AlquilerApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            var p = Pagination.readParams(req);
            var pr = AlquilerRepository.searchPage(p.q, p.page, p.size);
            
            var response = ApiResponse.success(pr);
            JsonUtils.writeJsonResponse(resp, response);
            
        } catch (Exception e) {
            var response = ApiResponse.error("Error al obtener alquileres: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            String json = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
            
            Alquiler alquiler = JsonUtils.readJsonRequest(json, Alquiler.class);
            
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
            
            String json = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
            
            Alquiler alquiler = JsonUtils.readJsonRequest(json, Alquiler.class);
            alquiler.setId(id);
            
            Alquiler existing = AlquilerRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Alquiler no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
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

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
