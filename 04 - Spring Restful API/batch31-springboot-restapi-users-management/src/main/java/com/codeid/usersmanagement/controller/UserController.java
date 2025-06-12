package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.request.CreateUserRequest;
import com.codeid.usersmanagement.model.request.UpdateUserRequest;
import com.codeid.usersmanagement.model.response.UserResponse;
import com.codeid.usersmanagement.service.BaseCrudService;
import com.codeid.usersmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController extends BaseCrudController<
        CreateUserRequest,
        UpdateUserRequest,
        UserResponse,
        String> {

    @Autowired
    private UserService userService;

    @Override
    protected BaseCrudService<CreateUserRequest, UpdateUserRequest, UserResponse, String> getService() {
        return userService;
    }
}
