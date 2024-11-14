package com.timesheet.app.controller;

import com.timesheet.app.dto.auth.LoginRequest;
import com.timesheet.app.dto.employee.EmployeeDto;
import com.timesheet.app.dto.employee.NewEmployeeDto;
import com.timesheet.app.model.Employee;
import com.timesheet.app.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewEmployeeDto newEmployee){
        System.out.println(newEmployee.getRoles().toString());
        Employee mappedEmployee = mapper.map(newEmployee, Employee.class);
        Employee result = service.register(mappedEmployee);
        return new ResponseEntity<>(mapper.map(result, EmployeeDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String token = service.login(loginRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
