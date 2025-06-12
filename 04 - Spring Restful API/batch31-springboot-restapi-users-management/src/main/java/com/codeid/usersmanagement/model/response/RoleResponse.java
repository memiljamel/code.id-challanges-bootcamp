package com.codeid.usersmanagement.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    private Short roleId;

    private String roleName;

    private List<Short> permissionIds;
}
