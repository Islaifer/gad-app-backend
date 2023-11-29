package com.fatec.gad.constants;

import org.springframework.stereotype.Component;

public enum RolesConstant {
    USER("USER"),
    MEDIC("MEDIC");
    private final String role;

    RolesConstant(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
