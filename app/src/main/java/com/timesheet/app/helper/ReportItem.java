package com.timesheet.app.helper;

import com.timesheet.app.model.Category;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportItem {
    private LocalDate date;
    private Employee employee;
    private Project project;
    private Client client;
    private Category category;
    private String description;
    private double time;
}
