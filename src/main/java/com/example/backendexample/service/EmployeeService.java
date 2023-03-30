package com.example.backendexample.service;

import com.example.backendexample.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
