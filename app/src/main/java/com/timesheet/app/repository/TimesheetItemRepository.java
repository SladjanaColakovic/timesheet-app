package com.timesheet.app.repository;

import com.timesheet.app.helper.MonthlyTimesheetItem;
import com.timesheet.app.helper.MonthlyTimesheetParams;
import com.timesheet.app.helper.ReportItem;
import com.timesheet.app.helper.ReportSearchParams;
import com.timesheet.app.model.TimesheetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetItemRepository extends JpaRepository<TimesheetItem, Long> {
    List<TimesheetItem> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
    @Query("SELECT new com.timesheet.app.helper.ReportItem(i.date, i.employee, i.project, i.client, i.category, i.description, i.hours + i.overtime) " +
            "FROM TimesheetItem i " +
            "WHERE (COALESCE(:#{#reportParams.startDate}, null) IS null OR i.date >= :#{#reportParams.startDate}) AND" +
            "(COALESCE(:#{#reportParams.endDate}, null) IS null OR i.date <= :#{#reportParams.endDate}) AND" +
            "(:#{#reportParams.employeeId} IS null OR :#{#reportParams.employeeId} = i.employee.id) AND" +
            "(:#{#reportParams.projectId} IS null OR :#{#reportParams.projectId} = i.project.id) AND" +
            "(:#{#reportParams.clientId} IS null OR :#{#reportParams.clientId} = i.client.id) AND" +
            "(:#{#reportParams.categoryId} IS null OR :#{#reportParams.categoryId} = i.category.id)")
    List<ReportItem> getTimesheetReport(ReportSearchParams reportParams);

    @Query("SELECT new com.timesheet.app.helper.MonthlyTimesheetItem(i.date, sum(i.hours + i.overtime)) " +
            "FROM TimesheetItem i " +
            "WHERE i.employee.id = :#{#timesheetParams.employeeId} AND " +
            "i.date >= :#{#timesheetParams.from} AND " +
            "i.date <= :#{#timesheetParams.to} " +
            "GROUP BY i.date")
    List<MonthlyTimesheetItem> findEmployeeItemsForDateRange(MonthlyTimesheetParams timesheetParams);

}
