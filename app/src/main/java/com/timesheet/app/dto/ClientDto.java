package com.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private CountryDto country;
}
