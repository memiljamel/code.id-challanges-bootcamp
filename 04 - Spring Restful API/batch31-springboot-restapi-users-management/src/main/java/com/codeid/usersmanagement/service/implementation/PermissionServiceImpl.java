package com.codeid.usersmanagement.service.implementation;

import com.codeid.usersmanagement.model.entity.Permission;
import com.codeid.usersmanagement.model.request.CreatePermissionRequest;
import com.codeid.usersmanagement.model.request.UpdatePermissionRequest;
import com.codeid.usersmanagement.model.response.PermissionResponse;
import com.codeid.usersmanagement.repository.PermissionRepository;
import com.codeid.usersmanagement.service.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

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

    @Override
    public PermissionResponse save(CreatePermissionRequest request) {
        Permission permission = new Permission();
        permission.setPermissionType(request.getPermissionType());
        permissionRepository.save(permission);

        return mapToPermissionResponse(permission);
    }

    @Override
    public PermissionResponse update(UpdatePermissionRequest request) {
        Permission permission = permissionRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + request.getId()));
        permission.setPermissionType(request.getPermissionType());
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
        return PermissionResponse.builder()
                .permissionId(permission.getPermissionId())
                .permissionType(permission.getPermissionType())
                .build();
    }
}
