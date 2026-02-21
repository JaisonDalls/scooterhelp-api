package com.nosaij.scooterhelp.dto;

public record AddressResponseDTO(
        String id,
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        String complement
) {
}
