package com.ofg.oauth.service.abstracts;

import com.ofg.oauth.model.entity.Role;
import com.ofg.oauth.model.request.RoleCreateRequest;
import com.ofg.oauth.model.request.RoleUpdateRequest;
import com.ofg.oauth.model.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {
    Page<RoleResponse> getAllRoles(Pageable pageable);

    RoleResponse getRoleById(UUID roleId);

    Role getRoleByName(String name);

    RoleResponse addRole(RoleCreateRequest roleCreateRequest);

    RoleResponse updateRole(UUID roleId, RoleUpdateRequest roleUpdateRequest);

    void deleteRole(UUID roleId);
}

