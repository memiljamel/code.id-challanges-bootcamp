package com.codeid.usersmanagement.model.response;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {

    private Short permissionId;

    private PermissionType permissionType;
}
