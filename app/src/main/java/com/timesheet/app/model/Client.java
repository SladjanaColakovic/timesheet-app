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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String address;
    private String city;
    private String postalCode;

    @ManyToOne
    private Country country;

    @Version
    private Long version;

    private boolean deleted = false;

    public Client(Long id, String name, String address, String city, String postalCode, Country country, boolean deleted) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.deleted = deleted;
    }
}
