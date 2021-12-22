package com.example.BackendExample.repository;

import com.example.BackendExample.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// repository is needed so that we can perform crud operations on the entity
// we don't have to mention @Repository and @Transactional because JpaRepository internally annotate that
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
