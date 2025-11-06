package com.prometheus.web.servlets;

import com.prometheus.web.model.User;
import com.prometheus.web.repo.UserRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.shared.RegisterRequest;
import com.prometheus.web.shared.UserResponse;
import com.prometheus.web.util.JsonUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet("/api/register")
public class RegisterApiServlet extends HttpServlet {

    private static long timeRandomId() {
        long millis = System.currentTimeMillis();
        int rnd = ThreadLocalRandom.current().nextInt(1000, 10_000);
        return millis * 10_000L + rnd;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String json = req.getReader().lines().reduce("", (a, b) -> a + b);
            RegisterRequest request = JsonUtils.readJsonRequest(json, RegisterRequest.class);
            
            if (request.getName() == null || request.getName().trim().isEmpty()) {
                JsonUtils.writeJsonResponse(resp, ApiResponse.error("El nombre es requerido"));
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                JsonUtils.writeJsonResponse(resp, ApiResponse.error("El email es requerido"));
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                JsonUtils.writeJsonResponse(resp, ApiResponse.error("La contraseña es requerida"));
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            
            long userId = timeRandomId();
            User newUser = new User(userId, request.getName(), request.getEmail(), request.getPassword());
            
            boolean saved = UserRepository.save(newUser);
            
            if (saved) {
                UserResponse userResponse = new UserResponse(newUser.getUserId(), newUser.getName(), newUser.getEmail());
                JsonUtils.writeJsonResponse(resp, ApiResponse.success("Registro exitoso", userResponse));
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                JsonUtils.writeJsonResponse(resp, ApiResponse.error("El correo ya existe"));
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        } catch (Exception e) {
            JsonUtils.writeJsonResponse(resp, ApiResponse.error("Error: " + e.getMessage()));
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

