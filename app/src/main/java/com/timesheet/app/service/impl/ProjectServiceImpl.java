package com.timesheet.app.service.impl;

import com.timesheet.app.exception.ClientNotFoundException;
import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.exception.OptimisticLockException;
import com.timesheet.app.exception.ProjectNotFoundException;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;
import com.timesheet.app.repository.ClientRepository;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.ProjectRepository;
import com.timesheet.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Project create(Project project) {
        Client client = clientRepository.findByIdAndDeletedFalse(project.getClient().getId()).orElseThrow(ClientNotFoundException::new);
        Employee lead = employeeRepository.findByIdAndDeletedFalse(project.getLead().getId()).orElseThrow(EmployeeNotFoundException::new);
        project.setClient(client);
        project.setLead(lead);
        return repository.save(project);
    }

    @Override
    public List<Project> getAll() {
        return repository.findByDeletedFalse();
    }

    @Override
    public Project getById(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public Project update(Project project) {
        Project existingProject = repository.findByIdAndDeletedFalse(project.getId()).orElseThrow(ProjectNotFoundException::new);
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStatus(project.getStatus());
        existingProject.setClient(project.getClient());
        existingProject.setLead(project.getLead());
        Project result = null;
        try{
            result = repository.save(existingProject);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new OptimisticLockException();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        Project project = repository.findByIdAndDeletedFalse(id).orElseThrow(ProjectNotFoundException::new);
        project.setDeleted(true);
        try{
            repository.save(project);
        } catch (ObjectOptimisticLockingFailureException ex){
            throw new OptimisticLockException();
        }
    }

    @Override
    public List<Employee> getEmployeesForProject(Long id) {
        Project project = repository.findByIdAndDeletedFalse(id).orElseThrow(ProjectNotFoundException::new);
        List<Employee> employees = project.getEmployees();
        employees.add(project.getLead());
        return employees.stream().distinct().collect(Collectors.toList());
    }
}
