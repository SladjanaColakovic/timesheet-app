package com.timesheet.app.service.impl;

import com.timesheet.app.exception.CountryNotFoundException;
import com.timesheet.app.exception.OptimisticLockException;
import com.timesheet.app.model.Country;
import com.timesheet.app.repository.CountryRepository;
import com.timesheet.app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
        return repository.findByDeletedFalse();
    }

    @Override
    public Country getById(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(CountryNotFoundException::new);
    }

    @Override
    public Country update(Country country) {
        Country existingCountry = repository.findByIdAndDeletedFalse(country.getId()).orElseThrow(CountryNotFoundException::new);
        existingCountry.setName(country.getName());
        Country result = null;
        try{
            result = repository.save(existingCountry);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new OptimisticLockException();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        Country country = repository.findByIdAndDeletedFalse(id).orElseThrow(CountryNotFoundException::new);
        country.setDeleted(true);
        try{
            repository.save(country);
        } catch (ObjectOptimisticLockingFailureException ex){
            throw new OptimisticLockException();
        }
    }
}
