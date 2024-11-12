package com.timesheet.app.controller;

import com.timesheet.app.dto.NewProjectDto;
import com.timesheet.app.dto.ProjectDto;
import com.timesheet.app.dto.UpdateProjectDto;
import com.timesheet.app.model.Project;
import com.timesheet.app.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewProjectDto newProject){
        Project mappedProject= mapper.map(newProject, Project.class);
        Project result = service.create(mappedProject);
        return new ResponseEntity<>(mapper.map(result, ProjectDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Project> result = service.getAll();
        return new ResponseEntity<>(
                result.stream().map(element -> mapper.map(element, ProjectDto.class)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Project result = service.getById(id);
        return new ResponseEntity<>(mapper.map(result, ProjectDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateProjectDto project){
        Project mappedProject = mapper.map(project, Project.class);
        Project result = service.update(mappedProject);
        return new ResponseEntity<>(mapper.map(result, ProjectDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("The project has been deleted", HttpStatus.OK);
    }
}
