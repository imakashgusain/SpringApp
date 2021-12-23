package com.example.BackendExample.controller;

import com.example.BackendExample.exception.ResourceNotFoundException;
import com.example.BackendExample.model.Employee;
import com.example.BackendExample.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@Api(value = "ems",description = "Operations related to the Employee Management")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    build create employee REST API
@ApiOperation(value = "Add a new employee entry")
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee( @RequestBody Employee employee){
        log.info("New Employee Added");
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
//     build get all employee REST API

    @ApiOperation(value = "View a list of all the employees", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public List<Employee> getAllEmployees(){
        log.info("Listing all employees");
        return employeeService.getAllEmployees();
}

//         build get employee by id REST API
@ApiOperation(value = "get employee details by employee ID")
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

    @ApiOperation(value = "Update employee details")
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

    @ApiOperation(value = "Delete an employee record")
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
