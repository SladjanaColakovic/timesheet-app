package com.timesheet.app.service.impl;

import com.timesheet.app.exception.CategoryNotFoundException;
import com.timesheet.app.exception.ClientNotFoundException;
import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.exception.ProjectNotFoundException;
import com.timesheet.app.model.*;
import com.timesheet.app.repository.*;
import com.timesheet.app.service.TimesheetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimesheetItemServiceImpl implements TimesheetItemService {

    @Autowired
    private TimesheetItemRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public TimesheetItem create(TimesheetItem timesheetItem) {
        Client client = clientRepository.findByIdAndDeletedFalse(timesheetItem.getClient().getId()).orElseThrow(ClientNotFoundException::new);
        Employee employee = employeeRepository.findByIdAndDeletedFalse(timesheetItem.getEmployee().getId()).orElseThrow(EmployeeNotFoundException::new);
        Project project = projectRepository.findByIdAndDeletedFalse(timesheetItem.getProject().getId()).orElseThrow(ProjectNotFoundException::new);
        Category category = categoryRepository.findByIdAndDeletedFalse(timesheetItem.getCategory().getId()).orElseThrow(CategoryNotFoundException::new);
        timesheetItem.setCategory(category);
        timesheetItem.setClient(client);
        timesheetItem.setEmployee(employee);
        timesheetItem.setProject(project);
        return repository.save(timesheetItem);
    }
}
