package com.timesheet.app.controller;

import com.timesheet.app.dto.client.ClientDto;
import com.timesheet.app.dto.employee.EmployeeDto;
import com.timesheet.app.dto.project.ProjectDto;
import com.timesheet.app.helper.EmployeeProject;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;
import com.timesheet.app.service.TimesheetItemDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheet/data")
public class TimesheetItemDataController {

    @Autowired
    private TimesheetItemDataService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/clients")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getEmployeeClients(@RequestParam(name = "employeeId") Long employeeId){
        List<Client> result = service.getEmployeeClients(employeeId);
        return new ResponseEntity<>(result.stream().map(element -> mapper.map(element, ClientDto.class)), HttpStatus.OK);
    }

    @PostMapping("/add/project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addClientToEmployee(@RequestBody EmployeeProject employeeProject){
        Employee result = service.addProjectToEmployee(employeeProject);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.OK);
    }

    @GetMapping("/projects")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getEmployeeProjects(@RequestParam(name = "employeeId") Long employeeId,
                                                 @RequestParam(name = "clientId") Long clientId){
        List<Project> result = service.getEmployeeProjectsForCertainClient(employeeId, clientId);
        return new ResponseEntity<>(result.stream().map(element -> mapper.map(element, ProjectDto.class)), HttpStatus.OK);
    }
}
