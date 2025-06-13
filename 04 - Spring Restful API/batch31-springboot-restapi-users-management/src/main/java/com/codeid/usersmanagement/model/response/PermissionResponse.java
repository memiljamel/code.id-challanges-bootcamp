package com.codeid.usersmanagement.model.response;

import com.codeid.usersmanagement.model.enumeration.PermissionType;

import java.time.Instant;


public record PermissionResponse(
        Short permissionId,
        PermissionType permissionType,
        Short roleId,
        Instant createdDate,
        Instant modifiedDate
) {
}
