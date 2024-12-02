package com.timesheet.app.helper;

import com.timesheet.app.enums.Flag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthlyTimesheetItem {
    private LocalDate date;
    private double hours;
    private Flag flag;

    public MonthlyTimesheetItem(LocalDate date, double hours){
        this.date = date;
        this.hours = hours;
    }

    public void updateFlagBasedOnHours(){
        setFlag(hours >= 7.5 ? Flag.Green : Flag.Red);
    }
}
