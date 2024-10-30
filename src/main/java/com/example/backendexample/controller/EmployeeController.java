package com.example.backendexample.controller;

import com.example.backendexample.exception.ResourceNotFoundException;
import com.example.backendexample.model.Employee;
import com.example.backendexample.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@Tag(name = "ems",description = "Operations related to the Employee Management")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    build create employee REST API
@Operation(summary = "Add a new employee entry")
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee( @RequestBody Employee employee){
        log.info("New Employee Added");
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
//     build get all employee REST API

    @Operation(summary = "View a list of all the employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public List<Employee> getAllEmployees(){
        log.info("Listing all employees");
        return employeeService.getAllEmployees();
}

//         build get employee by id REST API
@Operation(summary = "get employee details by employee ID")
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
            log.error("Error in fetching data");
            return  new ResponseEntity<String>("Error in Fetching Data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build update employee REST API

    @Operation(summary = "Update employee details")
    @PutMapping("{id}")
    public ResponseEntity<? extends Object> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
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

    @Operation(summary = "Delete an employee record")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){
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
