package com.timesheet.app.controller;

import com.timesheet.app.dto.timesheet.DailyTimesheetItemsDto;
import com.timesheet.app.dto.timesheet.TimesheetDto;
import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.helper.MonthlyTimesheetRequest;
import com.timesheet.app.helper.Timesheet;
import com.timesheet.app.service.TimesheetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/daily")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getEmployeeTimesheetItemsForDate(@RequestParam(name = "employeeId") Long employeeId,
                                                              @RequestParam(name = "date") LocalDate date){
        DailyTimesheetItems result = service.getEmployeeTimesheetItemsForDate(employeeId, date);
        return new ResponseEntity<>(mapper.map(result, DailyTimesheetItemsDto.class), HttpStatus.OK);
    }

    @GetMapping("/monthly")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> createEmployeeMonthlyTimesheet(@RequestParam(name = "employeeId") Long employeeId,
                                                            @RequestParam(name = "year") int year,
                                                            @RequestParam(name = "month") int month){
        Timesheet result = service.createEmployeeMonthlyTimesheet(new MonthlyTimesheetRequest(year, month, employeeId));
        return new ResponseEntity<>(mapper.map(result, TimesheetDto.class), HttpStatus.OK);

    }
}
