package com.codeid.usersmanagement.service;

import com.codeid.usersmanagement.model.request.CreatePermissionRequest;
import com.codeid.usersmanagement.model.request.UpdatePermissionRequest;
import com.codeid.usersmanagement.model.response.PermissionResponse;

public interface PermissionService extends BaseCrudService<
        CreatePermissionRequest,
        UpdatePermissionRequest,
        PermissionResponse,
        Short> {
}
