package com.timesheet.app.service;

import com.timesheet.app.data.DateUtilsConstants;
import com.timesheet.app.data.TimesheetServiceConstants;
import com.timesheet.app.enums.Flag;
import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.helper.MonthlyTimesheetRequest;
import com.timesheet.app.helper.Timesheet;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.impl.TimesheetServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static  org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TimesheetServiceTests {

    @Mock
    private TimesheetItemRepository timesheetItemRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private TimesheetServiceImpl service;

    @Test
    void testGetEmployeeTimesheetItemsForDate_Successful(){
        setUpGetEmployeeTimesheetItemsForDateSuccessful();
        DailyTimesheetItems result = service.getEmployeeTimesheetItemsForDate(TimesheetServiceConstants.EMPLOYEE_ID, TimesheetServiceConstants.DATE);
        assertThat(result).isNotNull();
        assertThat(result.getItems()).hasSize(TimesheetServiceConstants.EMPLOYEE_ITEMS_FOR_CERTAIN_DATE);
        assertThat(result.getTotalHours()).isEqualTo(TimesheetServiceConstants.EMPLOYEE_HOURS_FOR_CERTAIN_DATE);
    }

    @Test
    void testGetEmployeeTimesheetItemsForDate_Failure(){
        setUpGetEmployeeTimesheetItemsForDateFailure();
        DailyTimesheetItems result = null;
        try {
            result = service.getEmployeeTimesheetItemsForDate(TimesheetServiceConstants.EMPLOYEE_ID, TimesheetServiceConstants.DATE);
        } catch (EmployeeNotFoundException ex){
            assertThat(result).isNull();
            verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(timesheetItemRepository, never()).findByEmployeeIdAndDate(any(), any());
        }
    }

    @Test
    void testCreateEmployeeMonthlyTimesheet_Successful(){
        setUpCreateEmployeeMonthlyTimesheetSuccessful();
        Timesheet result = service.createEmployeeMonthlyTimesheet(new MonthlyTimesheetRequest(DateUtilsConstants.YEAR, DateUtilsConstants.MONTH, TimesheetServiceConstants.EMPLOYEE_ID));
        assertThat(result.getItems()).hasSize(TimesheetServiceConstants.MONTHLY_TIMESHEET_ITEMS_SIZE);
        assertThat(result.getItems().get(TimesheetServiceConstants.INDEX_OF_GREEN_FLAGGED_ITEM_8_HOURS).getFlag()).isEqualTo(Flag.Green);
        assertThat(result.getItems().get(TimesheetServiceConstants.INDEX_OF_GREEN_FLAGGED_ITEM_7_5_HOURS).getFlag()).isEqualTo(Flag.Green);
        assertThat(result.getItems().get(TimesheetServiceConstants.INDEX_OF_RED_FLAGGED_ITEM).getFlag()).isEqualTo(Flag.Red);
        assertThat(result.getItems().get(TimesheetServiceConstants.INDEX_OF_BLANK_FLAGGED_ITEM).getFlag()).isEqualTo(Flag.Blank);
        assertThat(result.getTotalHours()).isEqualTo(TimesheetServiceConstants.TOTAL_HOURS);
        verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(timesheetItemRepository, times(1)).findEmployeeItemsForDateRange(any());
    }

    @Test
    void testCreateEmployeeMonthlyTimesheet_Failure(){
        setUpCreateEmployeeMonthlyTimesheetFailure();
        Timesheet result = null;
        try {
            result = service.createEmployeeMonthlyTimesheet(new MonthlyTimesheetRequest(DateUtilsConstants.YEAR, DateUtilsConstants.MONTH, TimesheetServiceConstants.EMPLOYEE_ID));
        } catch (EmployeeNotFoundException ex){
            assertThat(result).isNull();
            verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(timesheetItemRepository, never()).findEmployeeItemsForDateRange(any());
        }
    }

    private void setUpCreateEmployeeMonthlyTimesheetFailure(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenThrow(new EmployeeNotFoundException());

    }

    private void setUpCreateEmployeeMonthlyTimesheetSuccessful(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetServiceConstants.EMPLOYEE));
        when(timesheetItemRepository.findEmployeeItemsForDateRange(any())).thenReturn(TimesheetServiceConstants.MONTHLY_TIMESHEET_ITEMS);
    }

    private void setUpGetEmployeeTimesheetItemsForDateFailure(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenThrow(new EmployeeNotFoundException());
    }

    private void setUpGetEmployeeTimesheetItemsForDateSuccessful(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetServiceConstants.EMPLOYEE));
        when(timesheetItemRepository.findByEmployeeIdAndDate(any(), any())).thenReturn(TimesheetServiceConstants.ITEMS);
    }

}
