package com.codeid.usersmanagement.model.request;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionRequest {

    @NotNull
    private PermissionType permissionType;

    @NotNull
    @PositiveOrZero
    private Short roleId;
}
