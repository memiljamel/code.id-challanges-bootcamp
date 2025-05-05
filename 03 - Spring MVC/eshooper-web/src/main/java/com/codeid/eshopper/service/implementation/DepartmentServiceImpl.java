package com.codeid.eshopper.service.implementation;

import com.codeid.eshopper.entities.Department;
import com.codeid.eshopper.repository.DepartmentRepository;
import com.codeid.eshopper.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }
}
