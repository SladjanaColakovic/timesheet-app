package com.timesheet.app.service;

import com.timesheet.app.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> getAll();
    Category getById(Long id);
    Category update(Category category);
    void delete(Long id);
}
