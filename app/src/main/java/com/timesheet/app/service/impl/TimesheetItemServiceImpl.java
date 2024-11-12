package com.timesheet.app.service.impl;

import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.model.TimesheetItem;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.TimesheetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimesheetItemServiceImpl implements TimesheetItemService {

    @Autowired
    private TimesheetItemRepository repository;

    @Override
    public TimesheetItem create(TimesheetItem timesheetItem) {
        return repository.save(timesheetItem);
    }

    @Override
    public DailyTimesheetItems getEmployeeTimesheetItemsForDate(Long employeeId, LocalDate date) {
        List<TimesheetItem> items = repository.findByEmployeeIdAndDate(employeeId, date);
        double totalHours = items.stream().mapToDouble(item -> item.getHours() + item.getOvertime()).sum();
        return new DailyTimesheetItems(items, totalHours);
    }
}
