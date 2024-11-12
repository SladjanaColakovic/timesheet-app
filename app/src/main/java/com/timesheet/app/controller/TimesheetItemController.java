package com.timesheet.app.controller;

import com.timesheet.app.dto.NewTimesheetItemDto;
import com.timesheet.app.dto.TimesheetItemDto;
import com.timesheet.app.model.TimesheetItem;
import com.timesheet.app.service.TimesheetItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetItemController {

    @Autowired
    private TimesheetItemService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewTimesheetItemDto newTimesheetItem){
        TimesheetItem mappedItem = mapper.map(newTimesheetItem, TimesheetItem.class);
        TimesheetItem result = service.create(mappedItem);
        return new ResponseEntity<>(mapper.map(result, TimesheetItemDto.class), HttpStatus.CREATED);
    }
}
