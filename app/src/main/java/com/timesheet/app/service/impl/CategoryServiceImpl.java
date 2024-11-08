package com.timesheet.app.service.impl;

import com.timesheet.app.exception.CategoryNotFoundException;
import com.timesheet.app.model.Category;
import com.timesheet.app.repository.CategoryRepository;
import com.timesheet.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category update(Category category) {
        Category existingCategory = repository.findById(category.getId()).orElseThrow(CategoryNotFoundException::new);
        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
