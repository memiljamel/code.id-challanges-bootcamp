package com.codeid.usersmanagement.service;

import com.codeid.usersmanagement.model.request.CreateUserRequest;
import com.codeid.usersmanagement.model.request.UpdateUserRequest;
import com.codeid.usersmanagement.model.response.UserResponse;

public interface UserService extends BaseCrudService<
        CreateUserRequest,
        UpdateUserRequest,
        UserResponse,
        String> {
}
