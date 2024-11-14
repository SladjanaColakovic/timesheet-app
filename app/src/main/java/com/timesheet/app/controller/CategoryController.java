package com.timesheet.app.controller;

import com.timesheet.app.dto.category.CategoryDto;
import com.timesheet.app.dto.category.NewCategoryDto;
import com.timesheet.app.dto.category.UpdateCategoryDto;
import com.timesheet.app.model.Category;
import com.timesheet.app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCategoryDto newCategory){
        Category mappedCategory = mapper.map(newCategory, Category.class);
        Category result = service.create(mappedCategory);
        return new ResponseEntity<>(mapper.map(result, CategoryDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        List<Category> result = service.getAll();
        return new ResponseEntity<>(
                result.stream().map(element -> mapper.map(element, CategoryDto.class)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category result = service.getById(id);
        return new ResponseEntity<>(mapper.map(result, CategoryDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateCategoryDto category){
        Category mappedCategory = mapper.map(category, Category.class);
        Category result = service.update(mappedCategory);
        return new ResponseEntity<>(mapper.map(result, CategoryDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("The category has been deleted", HttpStatus.OK);
    }

}
