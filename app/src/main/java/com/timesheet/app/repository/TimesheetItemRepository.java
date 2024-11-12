package com.timesheet.app.repository;

import com.timesheet.app.model.TimesheetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetItemRepository extends JpaRepository<TimesheetItem, Long> {
    List<TimesheetItem> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}
