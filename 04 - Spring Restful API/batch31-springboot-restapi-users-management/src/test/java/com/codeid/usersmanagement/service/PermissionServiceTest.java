package com.codeid.usersmanagement.service;

import com.codeid.usersmanagement.model.entity.Permission;
import com.codeid.usersmanagement.model.entity.Role;
import com.codeid.usersmanagement.model.enumeration.PermissionType;
import com.codeid.usersmanagement.model.request.CreatePermissionRequest;
import com.codeid.usersmanagement.model.request.UpdatePermissionRequest;
import com.codeid.usersmanagement.model.response.PermissionResponse;
import com.codeid.usersmanagement.repository.PermissionRepository;
import com.codeid.usersmanagement.repository.RoleRepository;
import com.codeid.usersmanagement.service.implementation.PermissionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    private final Short PERMISSION_ID = 100;
    private final PermissionType PERMISSION_TYPE = PermissionType.READ;
    private final Short ROLE_ID = 200;
    private final Instant CREATED_DATE = Instant.now();
    private final Instant MODIFIED_DATE = Instant.now();

    @Test
    void findAll_whenPermissionsExists_shouldReturnPageOfPermissionResponse() {
        // Arrange
        Role role = new Role();
        role.setRoleId(ROLE_ID);

        Permission permission = new Permission();
        permission.setPermissionId(PERMISSION_ID);
        permission.setPermissionType(PERMISSION_TYPE);
        permission.setRole(role);
        permission.setCreatedDate(CREATED_DATE);
        permission.setModifiedDate(MODIFIED_DATE);

        Pageable pageable = PageRequest.of(0, 20);

        Page<Permission> permissions = new PageImpl<>(List.of(permission));

        when(permissionRepository.findAll(any(Pageable.class)))
                .thenReturn(permissions);

        // Act
        List<PermissionResponse> responses = permissionService.findAll(pageable);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(PERMISSION_ID, responses.get(0).permissionId());
        assertEquals(PERMISSION_TYPE, responses.get(0).permissionType());
        assertEquals(ROLE_ID, responses.get(0).roleId());
        assertEquals(CREATED_DATE, responses.get(0).createdDate());
        assertEquals(MODIFIED_DATE, responses.get(0).modifiedDate());

        verify(permissionRepository, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    void findAll_whenPermissionsNotExists_shouldReturnEmptyOfPermissionResponse() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);

        Page<Permission> permission = new PageImpl<>(List.of());

        when(permissionRepository.findAll(any(Pageable.class)))
                .thenReturn(permission);

        // Act
        List<PermissionResponse> responses = permissionService.findAll(pageable);

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(permissionRepository, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    void findById_whenPermissionExists_shouldReturnPermissionResponse() {
        // Arrange
        Role role = new Role();
        role.setRoleId(ROLE_ID);

        Permission permission = new Permission();
        permission.setPermissionId(PERMISSION_ID);
        permission.setPermissionType(PERMISSION_TYPE);
        permission.setRole(role);
        permission.setCreatedDate(CREATED_DATE);
        permission.setModifiedDate(MODIFIED_DATE);

        when(permissionRepository.findById(eq(PERMISSION_ID)))
                .thenReturn(Optional.of(permission));

        // Act
        PermissionResponse response = permissionService.findById(PERMISSION_ID);

        // Assert
        assertNotNull(response);
        assertEquals(PERMISSION_ID, response.permissionId());
        assertEquals(PERMISSION_TYPE, response.permissionType());
        assertEquals(ROLE_ID, response.roleId());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(permissionRepository, times(1))
                .findById(eq(PERMISSION_ID));
    }

    @Test
    void findById_whenPermissionNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        when(permissionRepository.findById(eq(PERMISSION_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            permissionService.findById(PERMISSION_ID);
        });

        assertEquals("Permission not found with id " + PERMISSION_ID, exception.getMessage());

        verify(permissionRepository, times(1))
                .findById(eq(PERMISSION_ID));
    }

    @Test
    void save_whenRequestIsValid_shouldSaveAndReturnPermissionResponse() {
        // Arrange
        Role role = new Role();
        role.setRoleId(ROLE_ID);

        Permission permission = new Permission();
        permission.setPermissionId(PERMISSION_ID);
        permission.setPermissionType(PERMISSION_TYPE);
        permission.setRole(role);
        permission.setCreatedDate(CREATED_DATE);
        permission.setModifiedDate(MODIFIED_DATE);

        CreatePermissionRequest request = new CreatePermissionRequest(
                PERMISSION_TYPE,
                ROLE_ID
        );

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.of(role));
        when(permissionRepository.save(any(Permission.class)))
                .thenReturn(permission);

        // Act
        PermissionResponse response = permissionService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(PERMISSION_ID, response.permissionId());
        assertEquals(PERMISSION_TYPE, response.permissionType());
        assertEquals(ROLE_ID, response.roleId());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(permissionRepository, times(1))
                .save(any(Permission.class));
    }

    @Test
    void save_whenRoleNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        CreatePermissionRequest request = new CreatePermissionRequest(
                PERMISSION_TYPE,
                ROLE_ID
        );

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            permissionService.save(request);
        });

        assertEquals("Role not found with id " + ROLE_ID, exception.getMessage());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(permissionRepository, never())
                .findById(eq(PERMISSION_ID));
        verify(permissionRepository, never())
                .save(any(Permission.class));
    }

    @Test
    void update_whenPermissionExists_shouldUpdateAndReturnPermissionResponse() {
        // Arrange
        Role role = new Role();
        role.setRoleId(ROLE_ID);

        Permission permission = new Permission();
        permission.setPermissionId(PERMISSION_ID);
        permission.setPermissionType(PERMISSION_TYPE);
        permission.setRole(role);
        permission.setCreatedDate(CREATED_DATE);
        permission.setModifiedDate(MODIFIED_DATE);

        UpdatePermissionRequest request = new UpdatePermissionRequest(
                PermissionType.READ_WRITE,
                ROLE_ID
        );

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.of(role));
        when(permissionRepository.findById(eq(PERMISSION_ID)))
                .thenReturn(Optional.of(permission));
        when(permissionRepository.save(any(Permission.class)))
                .thenReturn(permission);

        // Act
        PermissionResponse response = permissionService.update(PERMISSION_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(PERMISSION_ID, response.permissionId());
        assertEquals(PermissionType.READ_WRITE, response.permissionType());
        assertEquals(ROLE_ID, response.roleId());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(permissionRepository, times(1))
                .findById(eq(PERMISSION_ID));
        verify(permissionRepository, times(1))
                .save(any(Permission.class));
    }

    @Test
    void update_whenRoleNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        UpdatePermissionRequest request = new UpdatePermissionRequest(
                PermissionType.READ_WRITE,
                ROLE_ID
        );

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            permissionService.update(PERMISSION_ID, request);
        });

        assertEquals("Role not found with id " + ROLE_ID, exception.getMessage());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(permissionRepository, never())
                .findById(eq(ROLE_ID));
        verify(permissionRepository, never())
                .save(any(Permission.class));
    }

    @Test
    void update_whenPermissionNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        Role role = new Role();
        role.setRoleId(ROLE_ID);

        UpdatePermissionRequest request = new UpdatePermissionRequest(
                PermissionType.READ_WRITE,
                ROLE_ID
        );

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.of(role));
        when(permissionRepository.findById(eq(PERMISSION_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            permissionService.update(PERMISSION_ID, request);
        });

        assertEquals("Permission not found with id " + PERMISSION_ID, exception.getMessage());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(permissionRepository, times(1))
                .findById(eq(PERMISSION_ID));
        verify(permissionRepository, never())
                .save(any(Permission.class));
    }

    @Test
    void delete_whenPermissionExists_shouldDeletePermission() {
        // Arrange
        Role role = new Role();
        role.setRoleId(ROLE_ID);

        Permission permission = new Permission();
        permission.setPermissionId(PERMISSION_ID);
        permission.setPermissionType(PERMISSION_TYPE);
        permission.setRole(role);
        permission.setCreatedDate(CREATED_DATE);
        permission.setModifiedDate(MODIFIED_DATE);

        when(permissionRepository.findById(eq(PERMISSION_ID)))
                .thenReturn(Optional.of(permission));
        doNothing().when(permissionRepository)
                .delete(permission);

        // Act
        permissionService.delete(PERMISSION_ID);

        // Assert
        verify(permissionRepository, times(1))
                .findById(eq(PERMISSION_ID));
        verify(permissionRepository, times(1))
                .delete(permission);
    }

    @Test
    void delete_whenPermissionNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        when(permissionRepository.findById(eq(PERMISSION_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            permissionService.delete(PERMISSION_ID);
        });

        assertEquals("Permission not found with id " + PERMISSION_ID, exception.getMessage());

        verify(permissionRepository, times(1))
                .findById(eq(PERMISSION_ID));
        verify(permissionRepository, never())
                .delete(any(Permission.class));
    }
}