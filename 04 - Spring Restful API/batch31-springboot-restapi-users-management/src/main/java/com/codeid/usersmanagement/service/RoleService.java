package com.codeid.usersmanagement.service;

import com.codeid.usersmanagement.model.request.CreateRoleRequest;
import com.codeid.usersmanagement.model.request.UpdateRoleRequest;
import com.codeid.usersmanagement.model.response.RoleResponse;

public interface RoleService extends BaseCrudService<
        CreateRoleRequest,
        UpdateRoleRequest,
        RoleResponse,
        Short> {
}
