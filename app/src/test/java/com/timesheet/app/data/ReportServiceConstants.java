package com.timesheet.app.data;

import com.timesheet.app.helper.ReportItem;
import com.timesheet.app.model.Category;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportServiceConstants {
    public static final String REPORT_ITEM_DESCRIPTION = "Test Description";
    public static final LocalDate DATE = LocalDate.of(2024, 11, 12);
    public static final double TIME = 5;
    public static final int LIST_SIZE = 2;
    public static final double TOTAL_HOURS = 10;

    public static final List<ReportItem> REPORT_ITEMS = new ArrayList<>(Arrays.asList(
            new ReportItem(DATE, new Employee(), new Project(), new Client(), new Category(), REPORT_ITEM_DESCRIPTION, TIME),
            new ReportItem(DATE, new Employee(), new Project(), new Client(), new Category(), REPORT_ITEM_DESCRIPTION, TIME)
    ));
}
