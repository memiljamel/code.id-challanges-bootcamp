package com.codeid.usersmanagement.model.request;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePermissionRequest extends UpdateIdRequest<Short> {

    @NotBlank
    private PermissionType permissionType;
}
