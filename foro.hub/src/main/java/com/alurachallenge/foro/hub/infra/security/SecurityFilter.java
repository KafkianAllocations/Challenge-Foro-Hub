package com.alurachallenge.foro.hub.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alurachallenge.foro.hub.repository.UserRepository;
import com.alurachallenge.foro.hub.service.TokenService;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Lee un token
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);

            if (subject != null) {
                // Token valido
                var user = userRepository.findByUsername(subject);
                System.out.println(user);
                // Forzar el inicio de sesion
                var autenticacion = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(autenticacion);
            }
        }

        filterChain.doFilter(request, response);
    }
}
