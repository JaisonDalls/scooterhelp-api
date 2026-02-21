package com.nosaij.scooterhelp.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressUpdateDTO(
        @NotBlank
        String id,
        String street,
        String number,
        String city,
        String neighborhood,
        String complement,
        String state,
        String zipCode
) {
}
