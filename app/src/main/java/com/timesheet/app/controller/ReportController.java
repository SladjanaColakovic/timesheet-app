package com.timesheet.app.controller;

import com.timesheet.app.dto.report.ReportDto;
import com.timesheet.app.helper.Report;
import com.timesheet.app.helper.ReportSearchParams;
import com.timesheet.app.service.ReportService;
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
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTimesheetReport(@RequestParam(name = "clientId", required = false) Long clientId,
                                                @RequestParam(name = "projectId", required = false) Long projectId,
                                                @RequestParam(name = "categoryId", required = false) Long categoryId,
                                                @RequestParam(name = "employeeId", required = false) Long employeeId,
                                                @RequestParam(name = "startDate", required = false) LocalDate startDate,
                                                @RequestParam(name = "endDate", required = false) LocalDate endDate){
        ReportSearchParams reportParams = new ReportSearchParams(clientId, projectId, categoryId, employeeId, startDate, endDate);
        Report result = service.getTimesheetReport(reportParams);
        return new ResponseEntity<>(mapper.map(result, ReportDto.class), HttpStatus.OK);

    }
}
