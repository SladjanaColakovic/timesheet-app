package com.timesheet.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimesheetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String description;
    private double hours;
    private double overtime;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Employee employee;

}
