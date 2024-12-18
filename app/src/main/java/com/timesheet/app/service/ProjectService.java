package com.timesheet.app.service;


import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;

import java.util.List;

public interface ProjectService {
    Project create(Project project);
    List<Project> getAll();
    Project getById(Long id);
    Project update(Project project);
    void delete(Long id);
    List<Employee> getEmployeesForProject(Long id);
}
