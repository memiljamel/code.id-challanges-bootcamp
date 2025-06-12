package com.codeid.usersmanagement.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String username;

    private String password;

    private Short roleId;
}
