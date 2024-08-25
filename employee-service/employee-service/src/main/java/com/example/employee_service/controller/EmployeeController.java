package com.example.employee_service.controller;

import com.example.employee_service.model.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee added: {}", employee);
        return repository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee found");
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable Long departmentId) {
        LOGGER.info("Employee found: department id = {}", departmentId);
        return repository.findByDepartment(departmentId);
    }

}
