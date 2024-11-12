package com.timesheet.app.dto;

import com.timesheet.app.enums.EmployeeStatus;
import com.timesheet.app.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private double hoursPerWeek;
    private String password;
    private EmployeeStatus status;
    private Role role;
}
