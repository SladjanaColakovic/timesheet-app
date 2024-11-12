package com.timesheet.app.repository;

import com.timesheet.app.model.TimesheetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetItemRepository extends JpaRepository<TimesheetItem, Long> {
}
