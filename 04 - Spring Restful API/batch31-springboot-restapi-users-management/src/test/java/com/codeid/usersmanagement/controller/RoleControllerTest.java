package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import com.codeid.usersmanagement.model.request.CreateRoleRequest;
import com.codeid.usersmanagement.model.request.UpdateRoleRequest;
import com.codeid.usersmanagement.model.response.RoleResponse;
import com.codeid.usersmanagement.service.RoleService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RoleService roleService;

    private final Short ROLE_ID = 100;
    private final String ROLE_NAME = "Role 1";
    private final Short USER_ID = 200;
    private final List<PermissionType> PERMISSIONS = List.of();
    private final Instant CREATED_DATE = Instant.now();
    private final Instant MODIFIED_DATE = Instant.now();

    @Test
    void getAll_whenRolesExists_shouldReturnPageAnd200Response() throws Exception {
        // Arrange
        RoleResponse response = new RoleResponse(
                ROLE_ID,
                ROLE_NAME,
                USER_ID,
                PERMISSIONS,
                CREATED_DATE,
                MODIFIED_DATE
        );

        List<RoleResponse> responses = List.of(response);

        when(roleService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/roles/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data[0].roleName", is(ROLE_NAME)))
                .andExpect(jsonPath("$.data[0].userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data[0].permissions", is(PERMISSIONS)))
                .andExpect(jsonPath("$.data[0].createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data[0].modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getAll_whenRolesExists_shouldReturnFilterAnd200Response() throws Exception {
        // Arrange
        RoleResponse response = new RoleResponse(
                ROLE_ID,
                ROLE_NAME,
                USER_ID,
                PERMISSIONS,
                CREATED_DATE,
                MODIFIED_DATE
        );

        List<RoleResponse> responses = List.of(response);

        when(roleService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/roles/")
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                        .queryParam("sort", "roleId,desc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data[0].roleName", is(ROLE_NAME)))
                .andExpect(jsonPath("$.data[0].userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data[0].permissions", is(PERMISSIONS)))
                .andExpect(jsonPath("$.data[0].createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data[0].modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getAll_whenRolesNotExists_shouldReturnEmptyAnd200Response() throws Exception {
        // Arrange
        List<RoleResponse> responses = List.of();

        when(roleService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/roles/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    void getById_whenRoleExists_shouldReturnPageAnd200Response() throws Exception {
        // Arrange
        RoleResponse response = new RoleResponse(
                ROLE_ID,
                ROLE_NAME,
                USER_ID,
                PERMISSIONS,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(roleService.findById(eq(ROLE_ID)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/roles/{roleId}", ROLE_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully by ID")))
                .andExpect(jsonPath("$.data.roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data.roleName", is(ROLE_NAME)))
                .andExpect(jsonPath("$.data.userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data.permissions", is(PERMISSIONS)))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getById_whenRoleNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        when(roleService.findById(eq(ROLE_ID)))
                .thenThrow(new EntityNotFoundException("Role not found with id " + ROLE_ID));

        // Act & Assert
        mockMvc.perform(get("/roles/{roleId}", ROLE_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Role not found with id " + ROLE_ID)));
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturn200Response() throws Exception {
        // Arrange
        CreateRoleRequest request = new CreateRoleRequest(
                ROLE_NAME,
                USER_ID
        );

        RoleResponse response = new RoleResponse(
                ROLE_ID,
                ROLE_NAME,
                USER_ID,
                PERMISSIONS,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(roleService.save(any(CreateRoleRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/roles/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("CREATED")))
                .andExpect(jsonPath("$.code", is(201)))
                .andExpect(jsonPath("$.message", is("Data created successfully")))
                .andExpect(jsonPath("$.data.roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data.roleName", is(ROLE_NAME)))
                .andExpect(jsonPath("$.data.userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data.permissions", is(PERMISSIONS)))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void create_whenUserNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        CreateRoleRequest request = new CreateRoleRequest(
                ROLE_NAME,
                USER_ID
        );

        when(roleService.save(any(CreateRoleRequest.class)))
                .thenThrow(new EntityNotFoundException("User not found with id " + USER_ID));

        // Act & Assert
        mockMvc.perform(post("/roles/")
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
    void create_whenRequestIsInvalid_shouldReturn400Response() throws Exception {
        // Arrange
        CreateRoleRequest request = new CreateRoleRequest(
                null,
                null
        );

        // Act & Assert
        mockMvc.perform(post("/roles/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("Validation failed")))
                .andExpect(jsonPath("$.errors", hasKey("roleName")))
                .andExpect(jsonPath("$.errors", hasKey("userId")));
    }

    @Test
    void update_whenRoleExists_shouldUpdateAndReturn200Response() throws Exception {
        UpdateRoleRequest request = new UpdateRoleRequest(
                "New Role 1",
                USER_ID
        );

        RoleResponse response = new RoleResponse(
                ROLE_ID,
                "New Role 1",
                USER_ID,
                PERMISSIONS,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(roleService.update(eq(ROLE_ID), any(UpdateRoleRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/roles/{roleId}", ROLE_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data updated successfully")))
                .andExpect(jsonPath("$.data.roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data.roleName", is("New Role 1")))
                .andExpect(jsonPath("$.data.userId", is(USER_ID.intValue())))
                .andExpect(jsonPath("$.data.permissions", is(PERMISSIONS)))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void update_whenUserNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        UpdateRoleRequest request = new UpdateRoleRequest(
                "New Role 1",
                USER_ID
        );

        when(roleService.update(eq(ROLE_ID), any(UpdateRoleRequest.class)))
                .thenThrow(new EntityNotFoundException("User not found with id " + USER_ID));

        // Act & Assert
        mockMvc.perform(put("/roles/{roleId}", ROLE_ID)
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
    void update_whenRoleNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        UpdateRoleRequest request = new UpdateRoleRequest(
                "New Role 1",
                USER_ID
        );

        when(roleService.update(eq(ROLE_ID), any(UpdateRoleRequest.class)))
                .thenThrow(new EntityNotFoundException("Role not found with id " + ROLE_ID));

        // Act & Assert
        mockMvc.perform(put("/roles/{roleId}", ROLE_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Role not found with id " + ROLE_ID)));
    }

    @Test
    void update_whenRequestIsInvalid_shouldReturn400Response() throws Exception {
        // Arrange
        UpdateRoleRequest request = new UpdateRoleRequest(
                null,
                null
        );

        // Act & Assert
        mockMvc.perform(put("/roles/{roleId}", ROLE_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("Validation failed")))
                .andExpect(jsonPath("$.errors", hasKey("roleName")))
                .andExpect(jsonPath("$.errors", hasKey("userId")));
    }

    @Test
    void delete_whenRoleExists_shouldDeleteAndReturn200Response() throws Exception {
        // Arrange
        doNothing().when(roleService).delete(eq(ROLE_ID));

        // Act & Assert
        mockMvc.perform(delete("/roles/{roleId}", ROLE_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_whenRoleNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        doThrow(new EntityNotFoundException("Role not found with id " + ROLE_ID))
                .when(roleService).delete(eq(ROLE_ID));

        // Act & Assert
        mockMvc.perform(delete("/roles/{roleId}", ROLE_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Role not found with id " + ROLE_ID)));
    }
}