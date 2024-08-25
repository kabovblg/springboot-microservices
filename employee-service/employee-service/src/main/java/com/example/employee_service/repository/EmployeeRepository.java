package com.example.employee_service.repository;

import com.example.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employees
                .stream()
                .filter(employee -> employee.departmentId().equals(departmentId))
                .toList();
    }

    public List<Employee> findAll() {
        return employees;
    }
}
