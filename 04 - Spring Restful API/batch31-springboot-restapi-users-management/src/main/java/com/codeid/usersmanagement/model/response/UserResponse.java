package com.codeid.usersmanagement.model.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Short userId;

    private String username;

    private List<String> roles;

    private Instant createdDate;

    private Instant modifiedDate;
}
