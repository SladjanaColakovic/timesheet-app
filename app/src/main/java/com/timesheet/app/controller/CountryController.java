package com.timesheet.app.controller;

import com.timesheet.app.dto.country.CountryDto;
import com.timesheet.app.dto.country.NewCountryDto;
import com.timesheet.app.dto.country.UpdateCountryDto;
import com.timesheet.app.model.Country;
import com.timesheet.app.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCountryDto newCountry){
        Country mappedCountry = mapper.map(newCountry, Country.class);
        Country result = service.create(mappedCountry);
        return new ResponseEntity<>(mapper.map(result, CountryDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Country> result = service.getAll();
        return new ResponseEntity<>(
                result.stream().map(element -> mapper.map(element, CountryDto.class)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Country result = service.getById(id);
        return new ResponseEntity<>(mapper.map(result, CountryDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateCountryDto country){
        Country mappedCountry = mapper.map(country, Country.class);
        Country result = service.update(mappedCountry);
        return new ResponseEntity<>(mapper.map(result, CountryDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("The country has been deleted", HttpStatus.OK);
    }
}
