package com.timesheet.app.dto.timesheet;

import com.timesheet.app.enums.Flag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class MonthlyTimesheetItemDto {
    private LocalDate date;
    private double hours;
    private Flag flag;
}
