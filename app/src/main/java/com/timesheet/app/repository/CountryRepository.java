package com.timesheet.app.repository;

import com.timesheet.app.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByDeletedFalse();
    Optional<Country> findByIdAndDeletedFalse(Long id);
}
