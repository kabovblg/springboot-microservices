package com.example.department_service.controller;


import com.example.department_service.client.EmployeeClient;
import com.example.department_service.model.Department;
import com.example.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private final DepartmentRepository repository;
    @Autowired
    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department added: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Departments found");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department found: id = {}", id);
        return repository.findById(id);
    }

    @GetMapping("with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Departments found");
        List<Department> departments = repository.findAll();
        departments.forEach(department ->
                department
                        .setEmployees(
                                employeeClient.findByDepartment(department.getId())
                        )
        );

        return departments;
    }
}
