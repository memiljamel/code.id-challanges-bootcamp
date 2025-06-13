package com.codeid.usersmanagement.model.request;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreatePermissionRequest(

        @NotNull
        PermissionType permissionType,

        @NotNull
        @PositiveOrZero
        Short roleId
) {
}
