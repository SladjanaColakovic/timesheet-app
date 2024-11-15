package com.timesheet.app.service.impl;

import com.timesheet.app.exception.ClientNotFoundException;
import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.exception.ProjectNotFoundException;
import com.timesheet.app.helper.EmployeeProject;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;
import com.timesheet.app.repository.ClientRepository;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.ProjectRepository;
import com.timesheet.app.service.TimesheetItemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetItemDataServiceImpl implements TimesheetItemDataService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Client> getEmployeeClients(Long employeeId) {
        Employee employee = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(EmployeeNotFoundException::new);
        List<Project> workingProjects = employee.getProjects();
        List<Project> leadingProjects = projectRepository.findByLeadIdAndDeletedFalse(employeeId);
        workingProjects.addAll(leadingProjects);
        return workingProjects.stream().map(Project::getClient).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Project> getEmployeeProjectsForCertainClient(Long employeeId, Long clientId) {
        Employee employee = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(EmployeeNotFoundException::new);
        Client client = clientRepository.findByIdAndDeletedFalse(clientId).orElseThrow(ClientNotFoundException::new);
        List<Project> workingProjects = employee.getProjects();
        List<Project> leadingProjects = projectRepository.findByLeadIdAndDeletedFalse(employeeId);
        workingProjects.addAll(leadingProjects);
        return workingProjects.stream().filter(project -> project.getClient().getId().equals(client.getId())).collect(Collectors.toList());
    }

    @Override
    public Employee addProjectToEmployee(EmployeeProject employeeProject) {
        Employee employee = employeeRepository.findByIdAndDeletedFalse(employeeProject.getEmployeeId()).orElseThrow(EmployeeNotFoundException::new);
        Project project = projectRepository.findByIdAndDeletedFalse(employeeProject.getProjectId()).orElseThrow(ProjectNotFoundException::new);
        List<Project> employeeProjects = employee.getProjects();
        employeeProjects.add(project);
        employee.setProjects(employeeProjects);
        return  employeeRepository.save(employee);
    }

}
