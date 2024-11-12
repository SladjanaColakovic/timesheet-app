package com.timesheet.app.service;

import com.timesheet.app.helper.DailyTimesheetItems;

import java.time.LocalDate;

public interface TimesheetService {
    DailyTimesheetItems getEmployeeTimesheetItemsForDate(Long employeeId, LocalDate date);
}
