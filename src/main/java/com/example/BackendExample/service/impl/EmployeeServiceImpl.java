package com.example.BackendExample.service.impl;

import com.example.BackendExample.exception.ResourceNotFoundException;
import com.example.BackendExample.model.Employee;
import com.example.BackendExample.repository.EmployeeRepository;
import com.example.BackendExample.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) throws ResourceNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new ResourceNotFoundException("Employee","ID",id);
        }
//        return employeeRepository.findById(id).orElseThrow(()->
//                new ResourceNotFoundException("Employee","ID",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
//         check employee exist with id or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee","Id",id)
        );
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //         check employee exist with id or not
        employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee","Id",id)
        );
    employeeRepository.deleteById(id);
    }
}
