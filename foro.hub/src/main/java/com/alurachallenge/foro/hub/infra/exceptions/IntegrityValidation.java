package com.alurachallenge.foro.hub.infra.exceptions;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String message) {
        super(message);
    }
}
// This class extends RuntimeException to create a custom exception for
// integrity validation errors.
// It takes a message as a parameter to provide details about the error.