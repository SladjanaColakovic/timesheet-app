package com.timesheet.app.service;

import com.timesheet.app.dto.auth.LoginRequest;
import com.timesheet.app.model.Employee;

public interface AuthService {
    String login(LoginRequest loginRequest);
    Employee register(Employee employee);
}
