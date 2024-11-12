package com.timesheet.app.model;

import com.timesheet.app.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private ProjectStatus status;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Employee lead;

    private boolean deleted = false;
}
