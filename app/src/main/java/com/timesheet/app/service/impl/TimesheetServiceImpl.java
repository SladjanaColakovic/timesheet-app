package com.timesheet.app.service.impl;

import com.timesheet.app.enums.Flag;
import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.helper.*;
import com.timesheet.app.model.TimesheetItem;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Timesheet createEmployeeMonthlyTimesheet(MonthlyTimesheetRequest timesheetRequest) {
        employeeRepository.findByIdAndDeletedFalse(timesheetRequest.getEmployeeId()).orElseThrow(EmployeeNotFoundException::new);

        LocalDate from = DateUtils.getFirstMondayForTimesheet(timesheetRequest.getYear(), timesheetRequest.getMonth());
        LocalDate to = DateUtils.getLastSundayForTimesheet(timesheetRequest.getYear(), timesheetRequest.getMonth());

        List<MonthlyTimesheetItem> items = new ArrayList<>();
        Map<LocalDate, MonthlyTimesheetItem> existingItems = loadTimesheetItemsByDateRange(new MonthlyTimesheetParams(from, to, timesheetRequest.getEmployeeId()));
        for(LocalDate date = from; !date.isAfter(to);  date = date.plusDays(TimesheetConstants.DAY_INCREMENT)){
            MonthlyTimesheetItem item = existingItems.getOrDefault(date, new MonthlyTimesheetItem(date, TimesheetConstants.INITIAL_HOURS, Flag.Blank));

            if(existingItems.containsKey(date)){
                item.updateFlagBasedOnHours();
            }
            items.add(item);
        }
        double totalHours = items.stream().mapToDouble(MonthlyTimesheetItem::getHours).sum();
        return new Timesheet(items, totalHours);
    }

    private Map<LocalDate, MonthlyTimesheetItem> loadTimesheetItemsByDateRange(MonthlyTimesheetParams timesheetParams){
        List<MonthlyTimesheetItem> items = timesheetItemRepository.findEmployeeItemsForDateRange(timesheetParams);
        return items.stream().collect(Collectors.toMap(MonthlyTimesheetItem::getDate, item -> item));
    }

}
