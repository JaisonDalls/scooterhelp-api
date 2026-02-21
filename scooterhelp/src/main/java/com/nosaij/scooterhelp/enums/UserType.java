package com.nosaij.scooterhelp.enums;

public enum UserType {
    USER(RoleName.ROLE_USER),
    SUPPLIER(RoleName.ROLE_SUPPLIER),
    TECHNICIAN(RoleName.ROLE_TECHNICIAN),
    SELLER(RoleName.ROLE_SELLER);

    private final RoleName role;

    UserType(RoleName role) {
        this.role = role;
    }

    public RoleName getRole() {
        return role;
    }
}

