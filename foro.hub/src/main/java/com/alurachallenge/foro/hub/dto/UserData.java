package com.alurachallenge.foro.hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserData(
        @NotNull Long id,
        @NotBlank String username) {
}
// This record is used to encapsulate user data, ensuring that the ID is not
// null and the username is not blank.