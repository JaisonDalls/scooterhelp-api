package com.nosaij.scooterhelp.dto;

import com.nosaij.scooterhelp.domain.UserRole;

public record LoginResponseDTO(
        String id,
        String name,
        String email,
        UserRole role
) {}
