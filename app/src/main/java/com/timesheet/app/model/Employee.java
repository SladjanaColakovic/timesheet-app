package com.timesheet.app.model;

import com.timesheet.app.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private double hoursPerWeek;

    @Column(nullable = false)
    private String password;

    private EmployeeStatus status;

    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employees_roles",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    /*@ManyToMany
    @JoinTable(name = "employee_clients",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
    private List<Client> clients;*/

    @ManyToMany
    @JoinTable(name = "employee_projects",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private List<Project> projects;

    public Employee(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Employee(Long id, String name, List<Project> projects){
        this(id, name);
        this.projects = projects;
    }


}
