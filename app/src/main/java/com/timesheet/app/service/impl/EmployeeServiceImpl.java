package com.timesheet.app.service.impl;

import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.exception.OptimisticLockException;
import com.timesheet.app.model.Employee;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

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
        Employee result = null;
        try{
            result = repository.save(existingEmployee);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new OptimisticLockException();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        Employee employee = repository.findByIdAndDeletedFalse(id).orElseThrow(EmployeeNotFoundException::new);
        employee.setDeleted(true);
        try{
            repository.save(employee);
        } catch (ObjectOptimisticLockingFailureException ex){
            throw new OptimisticLockException();
        }
    }
}
