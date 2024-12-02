package com.timesheet.app.data;

import com.timesheet.app.model.Employee;

public class AuthServiceConstants {
    public static final String JWT_TOKEN = "Test JWT token";
    public static final String PASSWORD = "Password123.";
    public static final Long EMPLOYEE_ID = 1L;
    public static final String EMPLOYEE_NAME = "Test User";
    public static final String BAD_CREDENTIALS_EXCEPTION_MESSAGE = "Failed";

    public static final Employee EMPLOYEE = new Employee(EMPLOYEE_ID, EMPLOYEE_NAME);
}
