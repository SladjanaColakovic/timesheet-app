package com.timesheet.app.helper;

import com.timesheet.app.model.TimesheetItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailyTimesheetItems {
    private List<TimesheetItem> items;
    private double totalHours;
}
