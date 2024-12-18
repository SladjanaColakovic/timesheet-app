package com.timesheet.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Version
    private Long version;

    private boolean deleted = false;

    public Category(String name, boolean deleted){
        this.name = name;
        this.deleted = deleted;
    }

    public Category(Long id, String name, boolean deleted){
        this(name, deleted);
        this.id = id;
    }
}
