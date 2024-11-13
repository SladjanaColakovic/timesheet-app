package com.timesheet.app.dto.project;

import com.timesheet.app.dto.employee.EmployeeDto;
import com.timesheet.app.dto.client.ClientDto;
import com.timesheet.app.enums.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private ClientDto client;
    private EmployeeDto lead;
}
