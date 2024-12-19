package com.ofg.oauth.model.response;

import com.ofg.oauth.model.entity.Role;

import java.util.UUID;

public record RoleResponse(
        UUID id,
        String name) {
    public RoleResponse(Role role) {
        this(role.getId(), role.getName());
    }
}
