package com.timesheet.app.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthlyTimesheetRequest {
    private int year;
    private int month;
    private Long employeeId;
}
