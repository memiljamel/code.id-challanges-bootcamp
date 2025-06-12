package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.request.CreateRoleRequest;
import com.codeid.usersmanagement.model.request.UpdateRoleRequest;
import com.codeid.usersmanagement.model.response.RoleResponse;
import com.codeid.usersmanagement.service.BaseCrudService;
import com.codeid.usersmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roles")
public class RoleController extends BaseCrudController<
        CreateRoleRequest,
        UpdateRoleRequest,
        RoleResponse,
        Short> {

    @Autowired
    private RoleService roleService;


    @Override
    protected BaseCrudService<CreateRoleRequest, UpdateRoleRequest, RoleResponse, Short> getService() {
        return roleService;
    }
}
