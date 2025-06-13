package com.codeid.usersmanagement.service.implementation;

import com.codeid.usersmanagement.model.entity.Permission;
import com.codeid.usersmanagement.model.entity.Role;
import com.codeid.usersmanagement.model.entity.User;
import com.codeid.usersmanagement.model.enumeration.PermissionType;
import com.codeid.usersmanagement.model.request.CreateRoleRequest;
import com.codeid.usersmanagement.model.request.UpdateRoleRequest;
import com.codeid.usersmanagement.model.response.RoleResponse;
import com.codeid.usersmanagement.repository.RoleRepository;
import com.codeid.usersmanagement.repository.UserRepository;
import com.codeid.usersmanagement.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RoleResponse> findAll(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);

        return roles.getContent().stream()
                .map(RoleServiceImpl::mapToRoleResponse)
                .toList();
    }

    @Override
    public RoleResponse findById(Short id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));

        return mapToRoleResponse(role);
    }

    @Transactional
    @Override
    public RoleResponse save(CreateRoleRequest request) {
        Role role = new Role();
        role.setRoleName(request.roleName());

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + request.userId()));
        role.setUser(user);

        roleRepository.save(role);

        return mapToRoleResponse(role);
    }

    @Transactional
    @Override
    public RoleResponse update(Short id, UpdateRoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));
        role.setRoleName(request.roleName());

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + request.userId()));
        role.setUser(user);

        roleRepository.save(role);

        return mapToRoleResponse(role);
    }

    @Override
    public void delete(Short id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));

        roleRepository.delete(role);
    }

    protected static RoleResponse mapToRoleResponse(Role role) {
        List<PermissionType> permissions = Stream.ofNullable(role.getPermissions())
                .flatMap(Collection::stream)
                .map(Permission::getPermissionType)
                .toList();

        return new RoleResponse(
                role.getRoleId(),
                role.getRoleName(),
                role.getUser().getUserId(),
                permissions,
                role.getCreatedDate(),
                role.getModifiedDate()
        );
    }
}
