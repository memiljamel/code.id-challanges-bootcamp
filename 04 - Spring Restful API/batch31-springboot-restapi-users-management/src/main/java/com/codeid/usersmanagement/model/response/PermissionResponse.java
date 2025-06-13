package com.codeid.usersmanagement.model.response;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {

    private Short permissionId;

    private PermissionType permissionType;

    private Short roleId;

    private Instant createdDate;

    private Instant modifiedDate;
}
