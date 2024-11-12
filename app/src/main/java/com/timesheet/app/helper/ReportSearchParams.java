package com.timesheet.app.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportSearchParams {
    private Long clientId;
    private Long projectId;
    private Long categoryId;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
}
