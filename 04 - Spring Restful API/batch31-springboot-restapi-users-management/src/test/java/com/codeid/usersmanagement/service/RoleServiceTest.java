package com.codeid.usersmanagement.service;

import com.codeid.usersmanagement.model.entity.Role;
import com.codeid.usersmanagement.model.entity.User;
import com.codeid.usersmanagement.model.enumeration.PermissionType;
import com.codeid.usersmanagement.model.request.CreateRoleRequest;
import com.codeid.usersmanagement.model.request.UpdateRoleRequest;
import com.codeid.usersmanagement.model.response.RoleResponse;
import com.codeid.usersmanagement.repository.RoleRepository;
import com.codeid.usersmanagement.repository.UserRepository;
import com.codeid.usersmanagement.service.implementation.RoleServiceImpl;
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
class RoleServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private final Short ROLE_ID = 100;
    private final String ROLE_NAME = "Role 1";
    private final Short USER_ID = 200;
    private final List<PermissionType> PERMISSIONS = List.of();
    private final Instant CREATED_DATE = Instant.now();
    private final Instant MODIFIED_DATE = Instant.now();

    @Test
    void findAll_whenRolesExists_shouldReturnPageOfRoleResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);

        Role role = new Role();
        role.setRoleId(ROLE_ID);
        role.setRoleName(ROLE_NAME);
        role.setUser(user);
        role.setCreatedDate(CREATED_DATE);
        role.setModifiedDate(MODIFIED_DATE);

        Pageable pageable = PageRequest.of(0, 20);

        Page<Role> roles = new PageImpl<>(List.of(role));

        when(roleRepository.findAll(any(Pageable.class)))
                .thenReturn(roles);

        // Act
        List<RoleResponse> responses = roleService.findAll(pageable);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(ROLE_ID, responses.get(0).roleId());
        assertEquals(ROLE_NAME, responses.get(0).roleName());
        assertEquals(USER_ID, responses.get(0).userId());
        assertEquals(PERMISSIONS, responses.get(0).permissions());
        assertEquals(CREATED_DATE, responses.get(0).createdDate());
        assertEquals(MODIFIED_DATE, responses.get(0).modifiedDate());

        verify(roleRepository, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    void findAll_whenRolesNotExists_shouldReturnEmptyOfRoleResponse() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);

        Page<Role> roles = new PageImpl<>(List.of());

        when(roleRepository.findAll(any(Pageable.class)))
                .thenReturn(roles);

        // Act
        List<RoleResponse> responses = roleService.findAll(pageable);

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(roleRepository, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    void findById_whenRoleExists_shouldReturnRoleResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);

        Role role = new Role();
        role.setRoleId(ROLE_ID);
        role.setRoleName(ROLE_NAME);
        role.setUser(user);
        role.setCreatedDate(CREATED_DATE);
        role.setModifiedDate(MODIFIED_DATE);

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.of(role));

        // Act
        RoleResponse response = roleService.findById(ROLE_ID);

        // Assert
        assertNotNull(response);
        assertEquals(ROLE_ID, response.roleId());
        assertEquals(ROLE_NAME, response.roleName());
        assertEquals(USER_ID, response.userId());
        assertEquals(PERMISSIONS, response.permissions());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
    }

    @Test
    void findById_whenRoleNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.findById(ROLE_ID);
        });

        assertEquals("Role not found with id " + ROLE_ID, exception.getMessage());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
    }

    @Test
    void save_whenRequestIsValid_shouldSaveAndReturnRoleResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);

        Role role = new Role();
        role.setRoleId(ROLE_ID);
        role.setRoleName(ROLE_NAME);
        role.setUser(user);
        role.setCreatedDate(CREATED_DATE);
        role.setModifiedDate(MODIFIED_DATE);

        CreateRoleRequest request = new CreateRoleRequest(
                ROLE_NAME,
                USER_ID
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.of(user));
        when(roleRepository.save(any(Role.class)))
                .thenReturn(role);

        // Act
        RoleResponse response = roleService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(ROLE_ID, response.roleId());
        assertEquals(ROLE_NAME, response.roleName());
        assertEquals(USER_ID, response.userId());
        assertEquals(PERMISSIONS, response.permissions());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(roleRepository, times(1))
                .save(any(Role.class));
    }

    @Test
    void save_whenUserNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        CreateRoleRequest request = new CreateRoleRequest(
                ROLE_NAME,
                USER_ID
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.save(request);
        });

        assertEquals("User not found with id " + USER_ID, exception.getMessage());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(roleRepository, never())
                .findById(eq(ROLE_ID));
        verify(roleRepository, never())
                .save(any(Role.class));
    }

    @Test
    void update_whenRoleExists_shouldUpdateAndReturnRoleResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);

        Role role = new Role();
        role.setRoleId(ROLE_ID);
        role.setRoleName(ROLE_NAME);
        role.setUser(user);
        role.setCreatedDate(CREATED_DATE);
        role.setModifiedDate(MODIFIED_DATE);

        UpdateRoleRequest request = new UpdateRoleRequest(
                "New Role 1",
                USER_ID
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.of(user));
        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.of(role));
        when(roleRepository.save(any(Role.class)))
                .thenReturn(role);

        // Act
        RoleResponse response = roleService.update(ROLE_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(ROLE_ID, response.roleId());
        assertEquals("New Role 1", response.roleName());
        assertEquals(USER_ID, response.userId());
        assertEquals(PERMISSIONS, response.permissions());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(roleRepository, times(1))
                .save(any(Role.class));
    }

    @Test
    void update_whenUserNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        UpdateRoleRequest request = new UpdateRoleRequest(
                "New Role 1",
                USER_ID
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.update(ROLE_ID, request);
        });

        assertEquals("User not found with id " + USER_ID, exception.getMessage());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(roleRepository, never())
                .findById(eq(ROLE_ID));
        verify(roleRepository, never())
                .save(any(Role.class));
    }

    @Test
    void update_whenRoleNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);

        UpdateRoleRequest request = new UpdateRoleRequest(
                "New Role 1",
                USER_ID
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.of(user));
        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.update(ROLE_ID, request);
        });

        assertEquals("Role not found with id " + ROLE_ID, exception.getMessage());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(roleRepository, never())
                .save(any(Role.class));
    }

    @Test
    void delete_whenRoleExists_shouldDeleteRole() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);

        Role role = new Role();
        role.setRoleId(ROLE_ID);
        role.setRoleName(ROLE_NAME);
        role.setUser(user);
        role.setCreatedDate(CREATED_DATE);
        role.setModifiedDate(MODIFIED_DATE);

        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.of(role));
        doNothing().when(roleRepository)
                .delete(role);

        // Act
        roleService.delete(ROLE_ID);

        // Assert
        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(roleRepository, times(1))
                .delete(role);
    }

    @Test
    void delete_whenRoleNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        when(roleRepository.findById(eq(ROLE_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.delete(ROLE_ID);
        });

        assertEquals("Role not found with id " + ROLE_ID, exception.getMessage());

        verify(roleRepository, times(1))
                .findById(eq(ROLE_ID));
        verify(roleRepository, never())
                .delete(any(Role.class));
    }
}