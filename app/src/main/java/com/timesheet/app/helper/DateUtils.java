package com.timesheet.app.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {

    public static LocalDate getFirstMondayForTimesheet(int year, int month){
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        return firstDayOfMonth.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getLastSundayForTimesheet(int year, int month){
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        return lastDayOfMonth.with(DayOfWeek.SUNDAY);
    }
}
