package com.codeid.usersmanagement.service.implementation;

import com.codeid.usersmanagement.model.entity.Permission;
import com.codeid.usersmanagement.model.entity.Role;
import com.codeid.usersmanagement.model.request.CreateRoleRequest;
import com.codeid.usersmanagement.model.request.UpdateRoleRequest;
import com.codeid.usersmanagement.model.response.RoleResponse;
import com.codeid.usersmanagement.repository.PermissionRepository;
import com.codeid.usersmanagement.repository.RoleRepository;
import com.codeid.usersmanagement.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

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

    @Override
    public RoleResponse save(CreateRoleRequest request) {
        Role role = new Role();
        role.setRoleName(request.getRoleName());

        List<Permission> permissions = request.getPermissionIds().stream()
                .map((permissionId) -> permissionRepository.findById(permissionId)
                        .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + permissionId))).toList();
        role.setPermissions(permissions);

        roleRepository.save(role);

        return mapToRoleResponse(role);
    }

    @Override
    public RoleResponse update(UpdateRoleRequest request) {
        Role role = roleRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + request.getId()));
        role.setRoleName(request.getRoleName());

        List<Permission> permissions = request.getPermissionIds().stream()
                .map((permissionId) -> permissionRepository.findById(permissionId)
                        .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + permissionId))).toList();
        role.setPermissions(permissions);

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
        List<Short> permissionIds = role.getPermissions().stream()
                .map(Permission::getPermissionId)
                .toList();

        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .permissionIds(permissionIds)
                .build();
    }
}
