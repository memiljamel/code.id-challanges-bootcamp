package com.codeid.usersmanagement.service.implementation;

import com.codeid.usersmanagement.model.entity.Permission;
import com.codeid.usersmanagement.model.entity.Role;
import com.codeid.usersmanagement.model.request.CreatePermissionRequest;
import com.codeid.usersmanagement.model.request.UpdatePermissionRequest;
import com.codeid.usersmanagement.model.response.PermissionResponse;
import com.codeid.usersmanagement.repository.PermissionRepository;
import com.codeid.usersmanagement.repository.RoleRepository;
import com.codeid.usersmanagement.service.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<PermissionResponse> findAll(Pageable pageable) {
        Page<Permission> permissions = permissionRepository.findAll(pageable);

        return permissions.getContent().stream()
                .map(PermissionServiceImpl::mapToPermissionResponse)
                .toList();
    }

    @Override
    public PermissionResponse findById(Short id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + id));

        return mapToPermissionResponse(permission);
    }

    @Transactional
    @Override
    public PermissionResponse save(CreatePermissionRequest request) {
        Permission permission = new Permission();
        permission.setPermissionType(request.permissionType());

        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + request.roleId()));
        permission.setRole(role);

        permissionRepository.save(permission);

        return mapToPermissionResponse(permission);
    }

    @Transactional
    @Override
    public PermissionResponse update(Short id, UpdatePermissionRequest request) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + id));
        permission.setPermissionType(request.permissionType());

        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + request.roleId()));
        permission.setRole(role);

        permissionRepository.save(permission);

        return mapToPermissionResponse(permission);
    }

    @Override
    public void delete(Short id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + id));

        permissionRepository.delete(permission);
    }

    protected static PermissionResponse mapToPermissionResponse(Permission permission) {
        return new PermissionResponse(
                permission.getPermissionId(),
                permission.getPermissionType(),
                permission.getRole().getRoleId(),
                permission.getCreatedDate(),
                permission.getModifiedDate()
        );
    }
}
