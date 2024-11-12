package com.timesheet.app.service.impl;

import com.timesheet.app.exception.CountryNotFoundException;
import com.timesheet.app.model.Country;
import com.timesheet.app.repository.CountryRepository;
import com.timesheet.app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public Country create(Country country) {
        return repository.save(country);
    }

    @Override
    public List<Country> getAll() {
        return repository.findAll();
    }

    @Override
    public Country getById(Long id) {
        return repository.findById(id).orElseThrow(CountryNotFoundException::new);
    }

    @Override
    public Country update(Country country) {
        Country existingCountry = repository.findById(country.getId()).orElseThrow(CountryNotFoundException::new);
        existingCountry.setName(country.getName());
        return repository.save(existingCountry);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
