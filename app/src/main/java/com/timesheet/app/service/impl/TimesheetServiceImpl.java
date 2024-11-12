package com.timesheet.app.service.impl;

import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.model.TimesheetItem;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetItemRepository timesheetItemRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DailyTimesheetItems getEmployeeTimesheetItemsForDate(Long employeeId, LocalDate date) {
        employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(EmployeeNotFoundException::new);
        List<TimesheetItem> items = timesheetItemRepository.findByEmployeeIdAndDate(employeeId, date);
        double totalHours = items.stream().mapToDouble(item -> item.getHours() + item.getOvertime()).sum();
        return new DailyTimesheetItems(items, totalHours);
    }
}
