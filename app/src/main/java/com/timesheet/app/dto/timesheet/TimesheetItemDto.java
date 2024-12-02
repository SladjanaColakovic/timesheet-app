package com.timesheet.app.dto.timesheet;

import com.timesheet.app.dto.category.CategoryDto;
import com.timesheet.app.dto.client.ClientDto;
import com.timesheet.app.dto.employee.EmployeeDto;
import com.timesheet.app.dto.project.ProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class TimesheetItemDto {
    private Long id;
    private LocalDate date;
    private String description;
    private double hours;
    private double overtime;
    private ClientDto client;
    private ProjectDto project;
    private CategoryDto category;
    private EmployeeDto employee;
}
