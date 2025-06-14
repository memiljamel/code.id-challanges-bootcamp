package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.request.CreateUserRequest;
import com.codeid.usersmanagement.model.request.UpdateUserRequest;
import com.codeid.usersmanagement.model.response.UserResponse;
import com.codeid.usersmanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private final Short USER_ID = 100;
    private final String USERNAME = "Username 1";
    private final String PASSWORD = "Password 1";
    private final List<String> ROLES = List.of();
    private final Instant CREATED_DATE = Instant.now();
    private final Instant MODIFIED_DATE = Instant.now();

    @Test
    void getAll_whenUsersExists_shouldReturnPageAnd200Response() throws Exception {
        // Arrange
        UserResponse response = new UserResponse(
                USER_ID,
                USERNAME,
                ROLES,
                CREATED_DATE,
                MODIFIED_DATE
        );

        List<UserResponse> responses = List.of(response);

        when(userService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/users/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data[0].username", is(USERNAME)))
                .andExpect(jsonPath("$.data[0].roles", is(ROLES)))
                .andExpect(jsonPath("$.data[0].createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data[0].modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getAll_whenUsersExists_shouldReturnFilterAnd200Response() throws Exception {
        // Arrange
        UserResponse response = new UserResponse(
                USER_ID,
                USERNAME,
                ROLES,
                CREATED_DATE,
                MODIFIED_DATE
        );

        List<UserResponse> responses = List.of(response);

        when(userService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/users/")
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                        .queryParam("sort", "userId,desc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data[0].username", is(USERNAME)))
                .andExpect(jsonPath("$.data[0].roles", is(ROLES)))
                .andExpect(jsonPath("$.data[0].createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data[0].modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getAll_whenUsersNotExists_shouldReturnEmptyAnd200Response() throws Exception {
        // Arrange
        List<UserResponse> responses = List.of();

        when(userService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/users/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    void getById_whenUserExists_shouldReturnPageAnd200Response() throws Exception {
        // Arrange
        UserResponse response = new UserResponse(
                USER_ID,
                USERNAME,
                ROLES,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(userService.findById(eq(USER_ID)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", USER_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully by ID")))
                .andExpect(jsonPath("$.data.userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data.username", is(USERNAME)))
                .andExpect(jsonPath("$.data.roles", is(ROLES)))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getById_whenUserNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        when(userService.findById(eq(USER_ID)))
                .thenThrow(new EntityNotFoundException("User not found with id " + USER_ID));

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", USER_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("User not found with id " + USER_ID)));
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturn200Response() throws Exception {
        // Arrange
        CreateUserRequest request = new CreateUserRequest(
                USERNAME,
                PASSWORD
        );

        UserResponse response = new UserResponse(
                USER_ID,
                USERNAME,
                ROLES,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(userService.save(any(CreateUserRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/users/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("CREATED")))
                .andExpect(jsonPath("$.code", is(201)))
                .andExpect(jsonPath("$.message", is("Data created successfully")))
                .andExpect(jsonPath("$.data.userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data.username", is(USERNAME)))
                .andExpect(jsonPath("$.data.roles", is(ROLES)))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void create_whenRequestIsInvalid_shouldReturn400Response() throws Exception {
        // Arrange
        CreateUserRequest request = new CreateUserRequest(
                null,
                null
        );

        // Act & Assert
        mockMvc.perform(post("/users/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("Validation failed")))
                .andExpect(jsonPath("$.errors", hasKey("username")))
                .andExpect(jsonPath("$.errors", hasKey("password")));
    }

    @Test
    void update_whenUserExists_shouldUpdateAndReturn200Response() throws Exception {
        // Arrange
        UpdateUserRequest request = new UpdateUserRequest(
                "New Username 1",
                "New Password 1"
        );

        UserResponse response = new UserResponse(
                USER_ID,
                USERNAME,
                ROLES,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(userService.update(eq(USER_ID), any(UpdateUserRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/users/{userId}", USER_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data updated successfully")))
                .andExpect(jsonPath("$.data.userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data.username", is(USERNAME)))
                .andExpect(jsonPath("$.data.roles", is(ROLES)))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void update_whenUserNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        UpdateUserRequest request = new UpdateUserRequest(
                "New Username 1",
                "New Password 1"
        );

        when(userService.update(eq(USER_ID), any(UpdateUserRequest.class)))
                .thenThrow(new EntityNotFoundException("User not found with id " + USER_ID));

        // Act & Assert
        mockMvc.perform(put("/users/{userId}", USER_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("User not found with id " + USER_ID)));
    }

    @Test
    void update_whenRequestIsInvalid_shouldReturn400Response() throws Exception {
        // Arrange
        UpdateUserRequest request = new UpdateUserRequest(
                null,
                null
        );

        // Act & Assert
        mockMvc.perform(put("/users/{userId}", USER_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("Validation failed")))
                .andExpect(jsonPath("$.errors", hasKey("username")))
                .andExpect(jsonPath("$.errors", hasKey("password")));
    }

    @Test
    void delete_whenUserExists_shouldDeleteAndReturn200Response() throws Exception {
        // Arrange
        doNothing().when(userService).delete(eq(USER_ID));

        // Act & Assert
        mockMvc.perform(delete("/users/{userId}", USER_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_whenUserNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        doThrow(new EntityNotFoundException("User not found with id " + USER_ID))
                .when(userService).delete(eq(USER_ID));

        // Act & Assert
        mockMvc.perform(delete("/users/{userId}", USER_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("User not found with id " + USER_ID)));
    }
}