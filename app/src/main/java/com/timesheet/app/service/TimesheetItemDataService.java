package com.timesheet.app.service;

import com.timesheet.app.helper.EmployeeProject;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;

import java.util.List;

public interface TimesheetItemDataService {
    List<Client> getEmployeeClients(Long employeeId);
    List<Project> getEmployeeProjectsForCertainClient(Long employeeId, Long clientId);
    Employee addProjectToEmployee(EmployeeProject employeeProject);
}
