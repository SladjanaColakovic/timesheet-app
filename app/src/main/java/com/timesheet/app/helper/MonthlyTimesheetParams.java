package com.timesheet.app.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthlyTimesheetParams {
    private LocalDate from;
    private LocalDate to;
    private Long employeeId;

}
