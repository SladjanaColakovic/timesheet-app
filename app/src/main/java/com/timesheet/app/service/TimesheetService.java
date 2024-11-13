package com.timesheet.app.service;

import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.helper.MonthlyTimesheetRequest;
import com.timesheet.app.helper.Timesheet;

import java.time.LocalDate;

public interface TimesheetService {
    DailyTimesheetItems getEmployeeTimesheetItemsForDate(Long employeeId, LocalDate date);
    Timesheet createEmployeeMonthlyTimesheet(MonthlyTimesheetRequest timesheetRequest);
}
