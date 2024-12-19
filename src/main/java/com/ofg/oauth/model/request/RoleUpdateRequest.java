package com.ofg.oauth.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleUpdateRequest(
        @NotBlank(message = "{app.constraint.role-name.not-blank}")
        @Size(min = 3, max = 100, message = "{app.constraint.role-name.size}")
        String name) {

}