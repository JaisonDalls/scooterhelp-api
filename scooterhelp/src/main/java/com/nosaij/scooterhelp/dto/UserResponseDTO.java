package com.nosaij.scooterhelp.dto;

import java.util.List;

public record UserResponseDTO(String id, String name, String email, List<String> roles) {
}
