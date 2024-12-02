package com.timesheet.app.data;

import com.timesheet.app.helper.MonthlyTimesheetItem;
import com.timesheet.app.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimesheetServiceConstants {

    public static final Long EMPLOYEE_ID = 1L;
    public static final String EMPLOYEE_NAME = "Test Employee";

    public static final LocalDate DATE = LocalDate.now();

    public static final Long ITEM_ID_1 = 1L;
    public static final String ITEM_DESCRIPTION_1 = "Timesheet Item Test Description 1";
    public static final double ITEM_HOURS_1 = 4.0;
    public static final double ITEM_OVERTIME_1 = 1.0;

    public static final Long ITEM_ID_2 = 2L;
    public static final String ITEM_DESCRIPTION_2 = "Timesheet Item Test Description 2";
    public static final double ITEM_HOURS_2 = 2.0;
    public static final double ITEM_OVERTIME_2 = 0.0;

    public static final Long ITEM_ID_3 = 3L;
    public static final String ITEM_DESCRIPTION_3 = "Timesheet Item Test Description 3";
    public static final double ITEM_HOURS_3 = 1.0;
    public static final double ITEM_OVERTIME_3 = 0.0;

    public static final LocalDate MONTHLY_TIMESHEET_ITEM_DATE_1 = LocalDate.of(2024, 11, 11);
    public static final double MONTHLY_TIMESHEET_ITEM_HOURS_1 = 8.0;

    public static final LocalDate MONTHLY_TIMESHEET_ITEM_DATE_2 = LocalDate.of(2024, 11, 12);
    public static final double MONTHLY_TIMESHEET_ITEM_HOURS_2 = 7.0;

    public static final LocalDate MONTHLY_TIMESHEET_ITEM_DATE_3 = LocalDate.of(2024, 11, 13);
    public static final double MONTHLY_TIMESHEET_ITEM_HOURS_3 = 3.0;

    public static final LocalDate MONTHLY_TIMESHEET_ITEM_DATE_4 = LocalDate.of(2024, 11, 14);
    public static final double MONTHLY_TIMESHEET_ITEM_HOURS_4 = 7.5;



    public static final List<TimesheetItem> ITEMS = new ArrayList<>(Arrays.asList(
            new TimesheetItem(ITEM_ID_1, DATE, ITEM_DESCRIPTION_1, ITEM_HOURS_1, ITEM_OVERTIME_1, new Client(), new Project(), new Category(), new Employee(EMPLOYEE_ID, EMPLOYEE_NAME)),
            new TimesheetItem(ITEM_ID_2, DATE, ITEM_DESCRIPTION_2, ITEM_HOURS_2, ITEM_OVERTIME_2, new Client(), new Project(), new Category(), new Employee(EMPLOYEE_ID, EMPLOYEE_NAME)),
            new TimesheetItem(ITEM_ID_3, DATE,  ITEM_DESCRIPTION_3, ITEM_HOURS_3, ITEM_OVERTIME_3, new Client(), new Project(), new Category(), new Employee(EMPLOYEE_ID, EMPLOYEE_NAME))
    ));

    public static final Employee EMPLOYEE = new Employee(EMPLOYEE_ID, EMPLOYEE_NAME);

    public static final List<MonthlyTimesheetItem> MONTHLY_TIMESHEET_ITEMS = new ArrayList<>(Arrays.asList(
            new MonthlyTimesheetItem(MONTHLY_TIMESHEET_ITEM_DATE_1, MONTHLY_TIMESHEET_ITEM_HOURS_1),
            new MonthlyTimesheetItem(MONTHLY_TIMESHEET_ITEM_DATE_2, MONTHLY_TIMESHEET_ITEM_HOURS_2),
            new MonthlyTimesheetItem(MONTHLY_TIMESHEET_ITEM_DATE_3, MONTHLY_TIMESHEET_ITEM_HOURS_3),
            new MonthlyTimesheetItem(MONTHLY_TIMESHEET_ITEM_DATE_4, MONTHLY_TIMESHEET_ITEM_HOURS_4)
    ));

    public static final int MONTHLY_TIMESHEET_ITEMS_SIZE = 35;
    public static final int INDEX_OF_GREEN_FLAGGED_ITEM_8_HOURS = 14;
    public static final int INDEX_OF_GREEN_FLAGGED_ITEM_7_5_HOURS = 17;
    public static final int INDEX_OF_RED_FLAGGED_ITEM = 15;
    public static final int INDEX_OF_BLANK_FLAGGED_ITEM = 7;

    public static final double TOTAL_HOURS = 25.5;


    public static final int EMPLOYEE_ITEMS_FOR_CERTAIN_DATE = 3;
    public static final double EMPLOYEE_HOURS_FOR_CERTAIN_DATE = 8.0;
}
