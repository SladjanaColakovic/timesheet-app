package com.timesheet.app.controller;

import com.timesheet.app.dto.DailyTimesheetItemsDto;
import com.timesheet.app.helper.DailyTimesheetItems;
import com.timesheet.app.service.TimesheetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<?> getEmployeeTimesheetItemsForDate(@RequestParam(name = "employeeId") Long employeeId,
                                                              @RequestParam(name = "date") LocalDate date){
        DailyTimesheetItems result = service.getEmployeeTimesheetItemsForDate(employeeId, date);
        return new ResponseEntity<>(mapper.map(result, DailyTimesheetItemsDto.class), HttpStatus.OK);
    }
}
