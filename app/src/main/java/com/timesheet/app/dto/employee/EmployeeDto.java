package com.timesheet.app.dto.employee;

import com.timesheet.app.enums.EmployeeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private Set<RoleDto> roles;
}
