package com.timesheet.app.controller;

import com.timesheet.app.dto.EmployeeDto;
import com.timesheet.app.dto.NewEmployeeDto;
import com.timesheet.app.dto.UpdateEmployeeDto;
import com.timesheet.app.model.Employee;
import com.timesheet.app.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewEmployeeDto newEmployee){
        Employee mappedEmployee = mapper.map(newEmployee, Employee.class);
        Employee result = service.create(mappedEmployee);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Employee> result = service.getAll();
        return new ResponseEntity<>(
                result.stream().map(element -> mapper.map(element, EmployeeDto.class)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Employee result = service.getById(id);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateEmployeeDto employee){
        Employee mappedEmployee = mapper.map(employee, Employee.class);
        Employee result = service.update(mappedEmployee);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("The employee has been deleted", HttpStatus.OK);
    }
}
