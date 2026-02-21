package com.nosaij.scooterhelp.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(
        @NotBlank
        String name
) {
}
