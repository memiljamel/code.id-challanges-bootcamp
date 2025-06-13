package com.codeid.usersmanagement.model.response;

import java.time.Instant;
import java.util.List;

public record UserResponse(
        Short userId,
        String username,
        List<String> roles,
        Instant createdDate,
        Instant modifiedDate
) {
}