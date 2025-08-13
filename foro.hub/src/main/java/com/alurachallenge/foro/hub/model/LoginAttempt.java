package com.alurachallenge.foro.hub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String ipAddress;
    private LocalDateTime timestamp;
    private boolean success;

    // Constructor vacío
    public LoginAttempt() {
    }

    // Constructor con parámetros
    public LoginAttempt(String username, String ipAddress, LocalDateTime timestamp, boolean success) {
        this.username = username;
        this.ipAddress = ipAddress;
        this.timestamp = timestamp;
        this.success = success;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
