package com.timesheet.app.repository;

import com.timesheet.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByDeletedFalse();
    Optional<Client> findByIdAndDeletedFalse(Long id);

}
