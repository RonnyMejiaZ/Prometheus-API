package com.prometheus.web.servlets;

import com.prometheus.web.model.Inquilino;
import com.prometheus.web.model.Property;
import com.prometheus.web.repo.InquilinoRepository;
import com.prometheus.web.repo.PropertyRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.util.JsonUtils;
import com.prometheus.web.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/api/inquilinos/*")
public class InquilinoApiServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Si hay pathInfo, es para obtener una propiedad específica
            String pathInfo = req.getPathInfo();
            if (pathInfo != null && !pathInfo.equals("/")) {
                long id = Long.parseLong(pathInfo.substring(1));
                Inquilino inquilino = InquilinoRepository.findById(id);

                if (inquilino == null) {
                    var response = ApiResponse.error("Propiedad no encontrada");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    JsonUtils.writeJsonResponse(resp, response);
                    return;
                }

                var response = ApiResponse.success(inquilino);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }
            var p = Pagination.readParams(req);
            var pr = InquilinoRepository.searchPage(p.q, p.page, p.size);

            var response = ApiResponse.success(pr);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (Exception e) {
            var response = ApiResponse.error("Error al obtener inquilinos: " + e.getMessage());
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

            Inquilino inquilino = JsonUtils.readJsonRequest(json, Inquilino.class);

            if (inquilino.getNombre() == null || inquilino.getNombre().trim().isEmpty()) {
                var response = ApiResponse.error("El nombre es requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            InquilinoRepository.save(inquilino);

            var response = ApiResponse.success("Inquilino creado exitosamente", inquilino);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (Exception e) {
            var response = ApiResponse.error("Error al crear inquilino: " + e.getMessage());
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
                var response = ApiResponse.error("ID de inquilino requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            long id = Long.parseLong(pathInfo.substring(1));

            String json = req.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            Inquilino inquilino = JsonUtils.readJsonRequest(json, Inquilino.class);
            inquilino.setId(id);

            Inquilino existing = InquilinoRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Inquilino no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            InquilinoRepository.update(inquilino);

            var response = ApiResponse.success("Inquilino actualizado exitosamente", inquilino);
            JsonUtils.writeJsonResponse(resp, response);

        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al actualizar inquilino: " + e.getMessage());
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
                var response = ApiResponse.error("ID de inquilino requerido");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            long id = Long.parseLong(pathInfo.substring(1));

            Inquilino existing = InquilinoRepository.findById(id);
            if (existing == null) {
                var response = ApiResponse.error("Inquilino no encontrado");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonUtils.writeJsonResponse(resp, response);
                return;
            }

            boolean deleted = InquilinoRepository.deleteById(id);

            if (deleted) {
                var response = ApiResponse.success("Inquilino eliminado exitosamente");
                JsonUtils.writeJsonResponse(resp, response);
            } else {
                var response = ApiResponse.error("No se pudo eliminar el inquilino");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JsonUtils.writeJsonResponse(resp, response);
            }

        } catch (NumberFormatException e) {
            var response = ApiResponse.error("ID inválido");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtils.writeJsonResponse(resp, response);
        } catch (Exception e) {
            var response = ApiResponse.error("Error al eliminar inquilino: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonUtils.writeJsonResponse(resp, response);
        }
    }
}
