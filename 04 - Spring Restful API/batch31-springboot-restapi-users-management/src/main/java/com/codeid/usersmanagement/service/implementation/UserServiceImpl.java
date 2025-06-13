package com.codeid.usersmanagement.service.implementation;

import com.codeid.usersmanagement.model.entity.Role;
import com.codeid.usersmanagement.model.entity.User;
import com.codeid.usersmanagement.model.request.CreateUserRequest;
import com.codeid.usersmanagement.model.request.UpdateUserRequest;
import com.codeid.usersmanagement.model.response.UserResponse;
import com.codeid.usersmanagement.repository.UserRepository;
import com.codeid.usersmanagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.getContent().stream()
                .map(UserServiceImpl::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Short id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        return mapToUserResponse(user);
    }

    @Override
    public UserResponse save(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        return mapToUserResponse(user);
    }

    @Override
    public UserResponse update(UpdateUserRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + request.getId()));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        return mapToUserResponse(user);
    }

    @Override
    public void delete(Short id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        userRepository.delete(user);
    }

    protected static UserResponse mapToUserResponse(User user) {
        List<String> roles = Stream.ofNullable(user.getRoles())
                .flatMap(Collection::stream)
                .map(Role::getRoleName)
                .toList();

        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .roles(roles)
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
