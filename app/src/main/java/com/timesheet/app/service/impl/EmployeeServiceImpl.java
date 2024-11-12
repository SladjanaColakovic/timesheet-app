package com.timesheet.app.service.impl;

import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.model.Employee;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findByDeletedFalse();
    }

    @Override
    public Employee getById(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee update(Employee employee) {
        Employee existingEmployee = repository.findByIdAndDeletedFalse(employee.getId()).orElseThrow(EmployeeNotFoundException::new);
        existingEmployee.setName(employee.getName());
        existingEmployee.setUsername(employee.getUsername());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setHoursPerWeek(employee.getHoursPerWeek());
        existingEmployee.setStatus(employee.getStatus());
        existingEmployee.setRole(employee.getRole());
        return repository.save(existingEmployee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = repository.findByIdAndDeletedFalse(id).orElseThrow(EmployeeNotFoundException::new);
        employee.setDeleted(true);
        repository.save(employee);
    }
}
