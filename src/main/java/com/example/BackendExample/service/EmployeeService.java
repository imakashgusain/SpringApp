package com.example.BackendExample.service;

import com.example.BackendExample.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
