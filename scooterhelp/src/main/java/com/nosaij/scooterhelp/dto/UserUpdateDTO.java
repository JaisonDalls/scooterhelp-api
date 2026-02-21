package com.nosaij.scooterhelp.dto;

import com.nosaij.scooterhelp.domain.Address;

public record UserUpdateDTO(String name, String password, String phone, String email) {
}
