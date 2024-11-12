package com.timesheet.app.service;


import com.timesheet.app.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> getAll();
    Employee getById(Long id);
    Employee update(Employee employee);
    void delete(Long id);
}
