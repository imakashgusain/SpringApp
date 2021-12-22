package com.example.BackendExample.controller;

import com.example.BackendExample.exception.ResourceNotFoundException;
import com.example.BackendExample.model.Employee;
import com.example.BackendExample.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    build create employee REST API
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee( @RequestBody Employee employee){
        log.info("New Employee Added");
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
//     build get all employee REST API
    @GetMapping()
    public List<Employee> getAllEmployees(){
        log.info("Listing all employees");
        return employeeService.getAllEmployees();
}

//         build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<? extends Object> getEmployeeById(@PathVariable("id") long employeeId){
        try {
            log.info("Listing employee by id :{}",employeeId);
            return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
        }
        catch (ResourceNotFoundException r){
            log.info("Cannot retrieve Employee details as Employee data not found with id : {} ",employeeId);
            return new ResponseEntity<String>("Employee id doesn't Exist",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<String>("Error in Fetching Data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<? extends Object> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        try {
            log.info("Employee details with id={} have been modified",id);
            return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
        }
        catch (ResourceNotFoundException r){

            return new ResponseEntity<String>("Employee id doesn't Exist",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<String>("Error in Fetching Data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        try {
            employeeService.deleteEmployee(id);
            log.info("employee details with id = {} deleted",id);
            return new ResponseEntity<String>("Employee deleted successfully.",HttpStatus.OK);
        }
        catch (ResourceNotFoundException r){

            return new ResponseEntity<String>("Employee id doesn't Exist",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<String>("Error in Fetching Data",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
