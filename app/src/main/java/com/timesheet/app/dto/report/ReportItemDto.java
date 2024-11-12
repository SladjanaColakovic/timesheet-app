package com.timesheet.app.dto.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ReportItemDto {
    private LocalDate date;
    private ReportEmployeeDto employee;
    private ReportProjectDto project;
    private ReportClientDto client;
    private ReportCategoryDto category;
    private String description;
    private double time;
}
