package com.timesheet.app.repository;

import com.timesheet.app.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByDeletedFalse();
    Optional<Project> findByIdAndDeletedFalse(Long id);
}
