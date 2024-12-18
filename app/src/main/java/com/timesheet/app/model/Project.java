package com.timesheet.app.model;

import com.timesheet.app.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @Version
    private Long version;

    private boolean deleted = false;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    public Project(Long id, String name, String description, ProjectStatus status, Client client, Employee lead, boolean deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.client = client;
        this.lead = lead;
        this.deleted = deleted;
    }
}
