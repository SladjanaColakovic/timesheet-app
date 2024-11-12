package com.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DailyTimesheetItemsDto {
    private List<TimesheetItemDto> items;
    private double totalHours;
}
