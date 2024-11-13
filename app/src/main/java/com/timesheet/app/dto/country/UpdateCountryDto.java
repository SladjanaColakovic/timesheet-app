package com.timesheet.app.dto.country;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateCountryDto {
    private Long id;
    private String name;
}
