package com.timesheet.app.service;

import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.model.TimesheetItem;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetItemService {
    TimesheetItem create(TimesheetItem timesheetItem);
}
