package com.example.backendexample.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Database generated unique ID of Employee ")
    private long id;

    @Column(name = "first_name",nullable = false)
    @Schema(name = "First Name of Employee")
    private String firstName;

    @Column(name = "last_name")
    @Schema(name = "Last Name of  Employee")
    private String lastName;

    @Column(name = "email")
    @Schema(name = " Email ID of Employee")
    private String email;


    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
