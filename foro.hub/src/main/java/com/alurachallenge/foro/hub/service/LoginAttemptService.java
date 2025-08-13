package com.alurachallenge.foro.hub.service;

import com.alurachallenge.foro.hub.model.LoginAttempt;
import com.alurachallenge.foro.hub.repository.LoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginAttemptService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    // Método para registrar un intento de inicio de sesión
    public void recordLoginAttempt(String username, String ipAddress, boolean success) {
        LoginAttempt loginAttempt = new LoginAttempt(
                username, ipAddress, LocalDateTime.now(), success);
        loginAttemptRepository.save(loginAttempt);
    }
}
