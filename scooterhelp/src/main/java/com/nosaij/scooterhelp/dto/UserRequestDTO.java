package com.nosaij.scooterhelp.dto;

import com.nosaij.scooterhelp.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotBlank
        String phone,

        @NotNull
        UserType userType,

        AddressRequestDTO address
) {
}
