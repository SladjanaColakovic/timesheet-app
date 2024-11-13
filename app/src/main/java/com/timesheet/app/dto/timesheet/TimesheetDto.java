package com.timesheet.app.dto.timesheet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TimesheetDto {
    private List<MonthlyTimesheetItemDto> items;
    private double totalHours;
}
