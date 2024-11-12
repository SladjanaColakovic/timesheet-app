package com.timesheet.app.service;

import com.timesheet.app.model.Country;

import java.util.List;

public interface CountryService {
    Country create(Country country);
    List<Country> getAll();
    Country getById(Long id);
    Country update(Country country);
    void delete(Long id);
}
