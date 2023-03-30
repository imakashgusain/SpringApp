package com.example.backendexample.repository;

import com.example.backendexample.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// repository is needed so that we can perform crud operations on the entity
// we don't have to mention @Repository and @Transactional because JpaRepository internally annotate that
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
