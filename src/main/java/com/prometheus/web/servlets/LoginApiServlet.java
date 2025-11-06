package com.prometheus.web.servlets;

import com.prometheus.web.model.User;
import com.prometheus.web.repo.UserRepository;
import com.prometheus.web.shared.ApiResponse;
import com.prometheus.web.shared.LoginRequest;
import com.prometheus.web.shared.UserResponse;
import com.prometheus.web.util.JsonUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/login")
public class LoginApiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String json = req.getReader().lines().reduce("", (a, b) -> a + b);
            LoginRequest request = JsonUtils.readJsonRequest(json, LoginRequest.class);
            
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
            
            boolean isValid = UserRepository.validate(request.getEmail(), request.getPassword());
            
            if (isValid) {
                User user = UserRepository.find(request.getEmail());
                UserResponse userResponse = new UserResponse(user.getUserId(), user.getName(), user.getEmail());
                
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);
                
                JsonUtils.writeJsonResponse(resp, ApiResponse.success("Login exitoso", userResponse));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                JsonUtils.writeJsonResponse(resp, ApiResponse.error("Credenciales inválidas"));
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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

