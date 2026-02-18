package com.nosaij.scooterhelp.dto;

import com.nosaij.scooterhelp.domain.Role;

public record LoginResponseDTO(
        String id,
        String name,
        String email,
        Role role
) {}
