package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
import com.codeid.usersmanagement.model.request.CreatePermissionRequest;
import com.codeid.usersmanagement.model.request.UpdatePermissionRequest;
import com.codeid.usersmanagement.model.response.PermissionResponse;
import com.codeid.usersmanagement.service.PermissionService;
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
class PermissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PermissionService permissionService;

    private final Short PERMISSION_ID = 100;
    private final PermissionType PERMISSION_TYPE = PermissionType.READ;
    private final Short ROLE_ID = 200;
    private final Instant CREATED_DATE = Instant.now();
    private final Instant MODIFIED_DATE = Instant.now();

    @Test
    void getAll_whenPermissionsExists_shouldReturnPageAnd200Response() throws Exception {
        // Arrange
        PermissionResponse response = new PermissionResponse(
                PERMISSION_ID,
                PERMISSION_TYPE,
                ROLE_ID,
                CREATED_DATE,
                MODIFIED_DATE
        );

        List<PermissionResponse> responses = List.of(response);

        when(permissionService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/permissions/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].permissionId", is(PERMISSION_ID.intValue())))
                .andExpect(jsonPath("$.data[0].permissionType", is(PERMISSION_TYPE.toString())))
                .andExpect(jsonPath("$.data[0].roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data[0].createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data[0].modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getAll_whenPermissionsExists_shouldReturnFilterAnd200Response() throws Exception {
        // Arrange
        PermissionResponse response = new PermissionResponse(
                PERMISSION_ID,
                PERMISSION_TYPE,
                ROLE_ID,
                CREATED_DATE,
                MODIFIED_DATE
        );

        List<PermissionResponse> responses = List.of(response);

        when(permissionService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/permissions/")
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                        .queryParam("sort", "permissionId,desc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].permissionId", is(PERMISSION_ID.intValue())))
                .andExpect(jsonPath("$.data[0].permissionType", is(PERMISSION_TYPE.toString())))
                .andExpect(jsonPath("$.data[0].roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data[0].createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data[0].modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getAll_whenPermissionsNotExists_shouldReturnEmptyAnd200Response() throws Exception {
        // Arrange
        List<PermissionResponse> responses = List.of();

        when(permissionService.findAll(any(Pageable.class)))
                .thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/permissions/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    void getById_whenPermissionExists_shouldReturnPageAnd200Response() throws Exception {
        // Arrange
        PermissionResponse response = new PermissionResponse(
                PERMISSION_ID,
                PERMISSION_TYPE,
                ROLE_ID,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(permissionService.findById(eq(PERMISSION_ID)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/permissions/{permissionId}", PERMISSION_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data retrieved successfully by ID")))
                .andExpect(jsonPath("$.data.permissionId", is(PERMISSION_ID.intValue())))
                .andExpect(jsonPath("$.data.permissionType", is(PERMISSION_TYPE.toString())))
                .andExpect(jsonPath("$.data.roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void getById_whenPermissionNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        when(permissionService.findById(eq(PERMISSION_ID)))
                .thenThrow(new EntityNotFoundException("Permission not found with id " + PERMISSION_ID));

        // Act & Assert
        mockMvc.perform(get("/permissions/{permissionId}", PERMISSION_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Permission not found with id " + PERMISSION_ID)));
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturn200Response() throws Exception {
        // Arrange
        CreatePermissionRequest request = new CreatePermissionRequest(
                PERMISSION_TYPE,
                ROLE_ID
        );

        PermissionResponse response = new PermissionResponse(
                PERMISSION_ID,
                PERMISSION_TYPE,
                ROLE_ID,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(permissionService.save(any(CreatePermissionRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/permissions/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("CREATED")))
                .andExpect(jsonPath("$.code", is(201)))
                .andExpect(jsonPath("$.message", is("Data created successfully")))
                .andExpect(jsonPath("$.data.permissionId", is(PERMISSION_ID.intValue())))
                .andExpect(jsonPath("$.data.permissionType", is(PERMISSION_TYPE.toString())))
                .andExpect(jsonPath("$.data.roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void create_whenRoleNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        CreatePermissionRequest request = new CreatePermissionRequest(
                PERMISSION_TYPE,
                ROLE_ID
        );

        when(permissionService.save(any(CreatePermissionRequest.class)))
                .thenThrow(new EntityNotFoundException("Role not found with id " + ROLE_ID));

        // Act & Assert
        mockMvc.perform(post("/permissions/")
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
    void create_whenRequestIsInvalid_shouldReturn400Response() throws Exception {
        // Arrange
        CreatePermissionRequest request = new CreatePermissionRequest(
                null,
                null
        );

        // Act & Assert
        mockMvc.perform(post("/permissions/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("Validation failed")))
                .andExpect(jsonPath("$.errors", hasKey("permissionType")))
                .andExpect(jsonPath("$.errors", hasKey("roleId")));
    }

    @Test
    void update_whenPermissionExists_shouldUpdateAndReturn200Response() throws Exception {
        UpdatePermissionRequest request = new UpdatePermissionRequest(
                PermissionType.READ_WRITE,
                ROLE_ID
        );

        PermissionResponse response = new PermissionResponse(
                PERMISSION_ID,
                PermissionType.READ_WRITE,
                ROLE_ID,
                CREATED_DATE,
                MODIFIED_DATE
        );

        when(permissionService.update(eq(PERMISSION_ID), any(UpdatePermissionRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/permissions/{permissionId}", PERMISSION_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Data updated successfully")))
                .andExpect(jsonPath("$.data.permissionId", is(PERMISSION_ID.intValue())))
                .andExpect(jsonPath("$.data.permissionType", is(PermissionType.READ_WRITE.toString())))
                .andExpect(jsonPath("$.data.roleId", is(ROLE_ID.intValue())))
                .andExpect(jsonPath("$.data.createdDate", is(CREATED_DATE.toString())))
                .andExpect(jsonPath("$.data.modifiedDate", is(MODIFIED_DATE.toString())));
    }

    @Test
    void update_whenRoleNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        UpdatePermissionRequest request = new UpdatePermissionRequest(
                PermissionType.READ_WRITE,
                ROLE_ID
        );

        when(permissionService.update(eq(PERMISSION_ID), any(UpdatePermissionRequest.class)))
                .thenThrow(new EntityNotFoundException("Role not found with id " + ROLE_ID));

        // Act & Assert
        mockMvc.perform(put("/permissions/{permissionId}", PERMISSION_ID)
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
    void update_whenPermissionNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        UpdatePermissionRequest request = new UpdatePermissionRequest(
                PermissionType.READ_WRITE,
                ROLE_ID
        );

        when(permissionService.update(eq(PERMISSION_ID), any(UpdatePermissionRequest.class)))
                .thenThrow(new EntityNotFoundException("Permission not found with id " + PERMISSION_ID));

        // Act & Assert
        mockMvc.perform(put("/permissions/{permissionId}", PERMISSION_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Permission not found with id " + PERMISSION_ID)));
    }

    @Test
    void update_whenRequestIsInvalid_shouldReturn400Response() throws Exception {
        // Arrange
        UpdatePermissionRequest request = new UpdatePermissionRequest(
                null,
                null
        );

        // Act & Assert
        mockMvc.perform(put("/permissions/{permissionId}", PERMISSION_ID)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("Validation failed")))
                .andExpect(jsonPath("$.errors", hasKey("permissionType")))
                .andExpect(jsonPath("$.errors", hasKey("roleId")));
    }

    @Test
    void delete_whenPermissionExists_shouldDeleteAndReturn200Response() throws Exception {
        // Arrange
        doNothing().when(permissionService).delete(eq(PERMISSION_ID));

        // Act & Assert
        mockMvc.perform(delete("/permissions/{permissionId}", PERMISSION_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_whenPermissionNotExists_shouldReturn404Response() throws Exception {
        // Arrange
        doThrow(new EntityNotFoundException("Permission not found with id " + PERMISSION_ID))
                .when(permissionService).delete(eq(PERMISSION_ID));

        // Act & Assert
        mockMvc.perform(delete("/permissions/{permissionId}", PERMISSION_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Permission not found with id " + PERMISSION_ID)));
    }
}