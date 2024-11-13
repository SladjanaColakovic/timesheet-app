package com.timesheet.app.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timesheet {
    private List<MonthlyTimesheetItem> items;
    private double totalHours;
}
