package com.timesheet.app.dto.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ReportDto {
    private List<ReportItemDto> items;
    private double totalHours;
}
