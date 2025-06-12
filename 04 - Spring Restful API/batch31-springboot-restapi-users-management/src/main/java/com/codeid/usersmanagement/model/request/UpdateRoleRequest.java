package com.codeid.usersmanagement.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleRequest extends UpdateIdRequest<Short> {

    @NotBlank
    private String roleName;

    @NotBlank
    @PositiveOrZero
    private List<Short> permissionIds;
}
