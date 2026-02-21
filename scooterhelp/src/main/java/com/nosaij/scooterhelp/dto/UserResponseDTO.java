package com.nosaij.scooterhelp.dto;

import java.util.Set;

public record UserResponseDTO(String id, String name, String email, Set<String> roles) {
}
