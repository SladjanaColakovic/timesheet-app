package com.timesheet.app.controller;

import com.timesheet.app.dto.employee.EmployeeDto;
import com.timesheet.app.dto.employee.NewEmployeeDto;
import com.timesheet.app.dto.employee.UpdateEmployeeDto;
import com.timesheet.app.model.Employee;
import com.timesheet.app.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        List<Employee> result = service.getAll();
        return new ResponseEntity<>(
                result.stream().map(element -> mapper.map(element, EmployeeDto.class)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Employee result = service.getById(id);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody UpdateEmployeeDto employee){
        Employee mappedEmployee = mapper.map(employee, Employee.class);
        Employee result = service.update(mappedEmployee);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("The employee has been deleted", HttpStatus.OK);
    }
}
