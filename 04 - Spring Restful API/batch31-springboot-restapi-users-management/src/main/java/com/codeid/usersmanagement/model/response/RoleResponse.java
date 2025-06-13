package com.codeid.usersmanagement.model.response;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    private Short roleId;

    private String roleName;

    private Short userId;

    private List<PermissionType> permissions;

    private Instant createdDate;

    private Instant modifiedDate;
}
