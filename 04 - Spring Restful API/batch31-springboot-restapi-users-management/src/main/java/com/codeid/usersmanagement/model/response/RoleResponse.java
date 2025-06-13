package com.codeid.usersmanagement.model.response;

import com.codeid.usersmanagement.model.enumeration.PermissionType;

import java.time.Instant;
import java.util.List;

public record RoleResponse(
        Short roleId,
        String roleName,
        Short userId,
        List<PermissionType> permissions,
        Instant createdDate,
        Instant modifiedDate
) {
}
