package com.timesheet.app.service;

import com.timesheet.app.data.ReportServiceConstants;
import com.timesheet.app.helper.Report;
import com.timesheet.app.helper.ReportItem;
import com.timesheet.app.helper.ReportSearchParams;
import com.timesheet.app.model.Category;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTests {

    @Mock
    private TimesheetItemRepository timesheetItemRepository;

    @InjectMocks
    private ReportServiceImpl service;

    @BeforeEach
    public void setUp(){
        when(timesheetItemRepository.getTimesheetReport(any())).thenReturn(Arrays.asList(
                new ReportItem(ReportServiceConstants.DATE, new Employee(), new Project(), new Client(), new Category(), ReportServiceConstants.REPORT_ITEM_DESCRIPTION, ReportServiceConstants.TIME),
                new ReportItem(ReportServiceConstants.DATE, new Employee(), new Project(), new Client(), new Category(), ReportServiceConstants.REPORT_ITEM_DESCRIPTION, ReportServiceConstants.TIME)
        ));
    }

    @Test
    void testGetTimesheetReport_Successful(){
        Report result = service.getTimesheetReport(new ReportSearchParams());
        assertThat(result).isNotNull();
        assertThat(result.getItems()).hasSize(ReportServiceConstants.LIST_SIZE);
        assertThat(result.getTotalHours()).isEqualTo(ReportServiceConstants.TOTAL_HOURS);
        verify(timesheetItemRepository, times(1)).getTimesheetReport(any());

    }
}