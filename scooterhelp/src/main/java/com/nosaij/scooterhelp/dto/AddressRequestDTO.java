package com.nosaij.scooterhelp.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
        @NotBlank
        String street,

        @NotBlank
        String number,

        @NotBlank
        String city,

        @NotBlank
        String neighborhood,

        String complement,

        @NotBlank
        String state,

        @NotBlank
        String zipCode
) {
}
