package com.timesheet.app.service;

import com.timesheet.app.helper.Report;
import com.timesheet.app.helper.ReportSearchParams;

public interface ReportService {
    Report getTimesheetReport(ReportSearchParams reportParams);
}
