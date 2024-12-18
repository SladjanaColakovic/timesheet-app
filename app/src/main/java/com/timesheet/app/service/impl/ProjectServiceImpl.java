package com.timesheet.app.service.impl;

import com.timesheet.app.exception.OptimisticLockException;
import com.timesheet.app.exception.ProjectNotFoundException;
import com.timesheet.app.model.Project;
import com.timesheet.app.repository.ProjectRepository;
import com.timesheet.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public Project create(Project project) {
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
}
