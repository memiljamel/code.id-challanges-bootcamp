package com.codeid.usersmanagement.service;

import com.codeid.usersmanagement.model.entity.User;
import com.codeid.usersmanagement.model.request.CreateUserRequest;
import com.codeid.usersmanagement.model.request.UpdateUserRequest;
import com.codeid.usersmanagement.model.response.UserResponse;
import com.codeid.usersmanagement.repository.UserRepository;
import com.codeid.usersmanagement.service.implementation.UserServiceImpl;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final Short USER_ID = 100;
    private final String USERNAME = "Username 1";
    private final String PASSWORD = "Password 1";
    private final List<String> ROLES = List.of();
    private final Instant CREATED_DATE = Instant.now();
    private final Instant MODIFIED_DATE = Instant.now();

    @Test
    void findAll_whenUsersExists_shouldReturnPageOfUserResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setCreatedDate(CREATED_DATE);
        user.setModifiedDate(MODIFIED_DATE);

        Pageable pageable = PageRequest.of(0, 20);

        Page<User> users = new PageImpl<>(List.of(user));

        when(userRepository.findAll(any(Pageable.class)))
                .thenReturn(users);

        // Act
        List<UserResponse> responses = userService.findAll(pageable);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(USER_ID, responses.get(0).userId());
        assertEquals(USERNAME, responses.get(0).username());
        assertEquals(ROLES, responses.get(0).roles());
        assertEquals(CREATED_DATE, responses.get(0).createdDate());
        assertEquals(MODIFIED_DATE, responses.get(0).modifiedDate());

        verify(userRepository, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    void findAll_whenUsersNotExists_shouldReturnEmptyOfUserResponse() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);

        Page<User> users = new PageImpl<>(List.of());

        when(userRepository.findAll(any(Pageable.class)))
                .thenReturn(users);

        // Act
        List<UserResponse> responses = userService.findAll(pageable);

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(userRepository, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    void findById_whenUserExists_shouldReturnUserResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setCreatedDate(CREATED_DATE);
        user.setModifiedDate(MODIFIED_DATE);

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.of(user));

        // Act
        UserResponse response = userService.findById(USER_ID);

        // Assert
        assertNotNull(response);
        assertEquals(USER_ID, response.userId());
        assertEquals(USERNAME, response.username());
        assertEquals(ROLES, response.roles());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
    }

    @Test
    void findById_whenUserNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(USER_ID);
        });

        assertEquals("User not found with id " + USER_ID, exception.getMessage());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
    }

    @Test
    void save_whenRequestIsValid_shouldSaveAndReturnUserResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setCreatedDate(CREATED_DATE);
        user.setModifiedDate(MODIFIED_DATE);

        CreateUserRequest request = new CreateUserRequest(
                USERNAME,
                PASSWORD
        );

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        // Act
        UserResponse response = userService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(USER_ID, response.userId());
        assertEquals(USERNAME, response.username());
        assertEquals(ROLES, response.roles());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(userRepository, times(1))
                .save(any(User.class));
    }

    @Test
    void update_whenUserExists_shouldUpdateAndReturnUserResponse() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setCreatedDate(CREATED_DATE);
        user.setModifiedDate(MODIFIED_DATE);

        UpdateUserRequest request = new UpdateUserRequest(
                "New Username",
                "New Password"
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        // Act
        UserResponse response = userService.update(USER_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(USER_ID, response.userId());
        assertEquals("New Username", response.username());
        assertEquals(ROLES, response.roles());
        assertEquals(CREATED_DATE, response.createdDate());
        assertEquals(MODIFIED_DATE, response.modifiedDate());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(userRepository, times(1))
                .save(any(User.class));
    }

    @Test
    void update_whenUserNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        UpdateUserRequest request = new UpdateUserRequest(
                "New Username",
                "New Password"
        );

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.update(USER_ID, request);
        });

        assertEquals("User not found with id " + USER_ID, exception.getMessage());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(userRepository, never())
                .save(any(User.class));
    }

    @Test
    void delete_whenUserExists_shouldDeleteUser() {
        // Arrange
        User user = new User();
        user.setUserId(USER_ID);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setCreatedDate(CREATED_DATE);
        user.setModifiedDate(MODIFIED_DATE);

        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.of(user));
        doNothing().when(userRepository)
                .delete(user);

        // Act
        userService.delete(USER_ID);

        // Assert
        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(userRepository, times(1))
                .delete(user);
    }

    @Test
    void delete_whenUserNotExists_shouldThrowEntityNotFoundException() {
        // Arrange
        when(userRepository.findById(eq(USER_ID)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.delete(USER_ID);
        });

        assertEquals("User not found with id " + USER_ID, exception.getMessage());

        verify(userRepository, times(1))
                .findById(eq(USER_ID));
        verify(userRepository, never())
                .delete(any(User.class));
    }
}