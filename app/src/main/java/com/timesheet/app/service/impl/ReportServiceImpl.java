package com.timesheet.app.service.impl;

import com.timesheet.app.helper.Report;
import com.timesheet.app.helper.ReportItem;
import com.timesheet.app.helper.ReportSearchParams;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TimesheetItemRepository timesheetItemRepository;

    @Override
    public Report getTimesheetReport(ReportSearchParams reportParams) {
        List<ReportItem> items = timesheetItemRepository.getTimesheetReport(reportParams);
        double totalHours = items.stream().map(ReportItem::getTime).reduce(0.0, Double::sum);
        return new Report(items, totalHours);
    }
}
