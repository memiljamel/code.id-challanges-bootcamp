package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.request.CreatePermissionRequest;
import com.codeid.usersmanagement.model.request.UpdatePermissionRequest;
import com.codeid.usersmanagement.model.response.PermissionResponse;
import com.codeid.usersmanagement.service.BaseCrudService;
import com.codeid.usersmanagement.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/permissions")
public class PermissionController extends BaseCrudController<
        CreatePermissionRequest,
        UpdatePermissionRequest,
        PermissionResponse,
        Short> {

    @Autowired
    private PermissionService permissionService;

    @Override
    protected BaseCrudService<CreatePermissionRequest, UpdatePermissionRequest, PermissionResponse, Short> getService() {
        return permissionService;
    }
}
