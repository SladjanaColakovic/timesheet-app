package com.timesheet.app.service.impl;

import com.timesheet.app.dto.auth.LoginRequest;
import com.timesheet.app.exception.DuplicateUsernameException;
import com.timesheet.app.model.Employee;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.RoleRepository;
import com.timesheet.app.security.service.CustomUserDetailsService;
import com.timesheet.app.security.token.TokenUtils;
import com.timesheet.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = userService.loadUserByUsername(loginRequest.getUsername());
        return tokenUtils.generateToken((User) user);
    }

    @Override
    public Employee register(Employee employee) {
        Employee existingEmployee = employeeRepository.findByUsernameAndDeletedFalse(employee.getUsername()).orElse(null);
        if(existingEmployee != null) throw new DuplicateUsernameException();
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }
}
