package com.timesheet.app.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryDto {
    private Long id;
    private String name;
}
