package com.ofg.oauth.service.abstracts;

import com.ofg.oauth.model.request.AssignRoleRequest;

import java.util.UUID;

public interface UserRoleService {
    void assignRoleToUser(AssignRoleRequest assignRoleRequest);

    void revokeRoleFromUser(UUID userId, String roleName);
}
