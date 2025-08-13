package com.alurachallenge.foro.hub.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alurachallenge.foro.hub.dto.AuthUserData;
import com.alurachallenge.foro.hub.model.User;
import com.alurachallenge.foro.hub.service.TokenService;

import com.alurachallenge.foro.hub.service.LoginAttemptService;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("login")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    // Iniciar sesión
    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody AuthUserData authUserData, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr(); // Obtener la dirección IP del cliente

        try {
            // Crear token de autenticación
            Authentication authToken = new UsernamePasswordAuthenticationToken(authUserData.username(),
                    authUserData.password());
            Authentication authUser = authManager.authenticate(authToken);

            // Generar JWT para el usuario autenticado
            String jwt = tokenService.generateToken((User) authUser.getPrincipal());

            // Registrar intento exitoso en la auditoría
            loginAttemptService.recordLoginAttempt(authUserData.username(), ipAddress, true);

            // Crear objeto de respuesta
            JwtResponse response = new JwtResponse(jwt);
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            // Registrar intento fallido en la auditoría
            loginAttemptService.recordLoginAttempt(authUserData.username(), ipAddress, false);

            // Devolver respuesta de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas.");
        } catch (Exception e) {
            // Registrar error general en la auditoría
            loginAttemptService.recordLoginAttempt(authUserData.username(), ipAddress, false);

            // Manejo de cualquier otra excepción general
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el proceso de autenticación.");
        }
    }

    // DTO de respuesta
    public static class JwtResponse {
        private String jwtToken;

        public JwtResponse(String jwtToken) {
            this.jwtToken = jwtToken;
        }

        public String getJwtToken() {
            return jwtToken;
        }

        public void setJwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
        }
    }
}
