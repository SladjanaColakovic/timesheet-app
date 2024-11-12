package com.timesheet.app.model;

import com.timesheet.app.enums.EmployeeStatus;
import com.timesheet.app.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private double hoursPerWeek;

    @Column(nullable = false)
    private String password;

    private EmployeeStatus status;
    private Role role;



}
