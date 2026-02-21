package com.nosaij.scooterhelp.enums;

import java.util.ArrayList;
import java.util.List;

public enum RoleName {

    ROLE_USER(),
    ROLE_SUPPLIER(ROLE_USER),
    ROLE_TECHNICIAN(ROLE_USER),
    ROLE_SELLER(ROLE_USER),
    ROLE_ADMIN(ROLE_USER);

    private final List<RoleName> inheritedRoles;

    RoleName(RoleName... inheritedRoles) {
        this.inheritedRoles = List.of(inheritedRoles);
    }

    public List<RoleName> getAllRoles() {
        List<RoleName> roles = new ArrayList<>();
        roles.add(this);

        for (RoleName inherited : inheritedRoles) {
            roles.addAll(inherited.getAllRoles());
        }

        return roles.stream().distinct().toList();
    }
}
