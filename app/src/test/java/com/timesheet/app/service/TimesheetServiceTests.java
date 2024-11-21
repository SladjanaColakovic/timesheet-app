package com.timesheet.app.service;

import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.helper.ReportItem;
import com.timesheet.app.model.*;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.TimesheetItemRepository;
import com.timesheet.app.service.impl.TimesheetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static  org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TimesheetServiceTests {

    @Mock
    private TimesheetItemRepository timesheetItemRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private TimesheetServiceImpl service;

    @BeforeEach
    void setUp(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(new Employee(1L, "Test Employee")));
        when(timesheetItemRepository.findByEmployeeIdAndDate(any(), any())).thenReturn(Arrays.asList(
                new TimesheetItem(1L, LocalDate.now(), "Timesheet Item Test Description 1", 4.0, 1.0, new Client(), new Project(), new Category(), new Employee(1L, "Test Employee")),
                new TimesheetItem(2L, LocalDate.now(), "Timesheet Item Test Description 2", 2.0, 0.0, new Client(), new Project(), new Category(), new Employee(1L, "Test Employee")),
                new TimesheetItem(3L, LocalDate.now(), "Timesheet Item Test Description 3", 1.0, 0.0, new Client(), new Project(), new Category(), new Employee(1L, "Test Employee"))
                ));
    }

    @Test
    void testGetEmployeeTimesheetItemsForDate(){
        DailyTimesheetItems result = service.getEmployeeTimesheetItemsForDate(1L, LocalDate.now());
        assertThat(result).isNotNull();
        assertThat(result.getItems()).hasSize(3);
        assertThat(result.getTotalHours()).isEqualTo(8.0);
    }

}
