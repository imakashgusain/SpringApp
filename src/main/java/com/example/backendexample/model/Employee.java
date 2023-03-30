package com.example.backendexample.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Database generated unique ID of Employee ")
    private long id;

    @Column(name = "first_name",nullable = false)
    @ApiModelProperty(notes = "First Name of Employee")
    private String firstName;

    @Column(name = "last_name")
    @ApiModelProperty(notes = "Last Name of  Employee")
    private String lastName;

    @Column(name = "email")
    @ApiModelProperty(notes = " Email ID of Employee")
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
