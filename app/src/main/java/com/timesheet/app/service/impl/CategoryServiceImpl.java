package com.timesheet.app.service.impl;

import com.timesheet.app.exception.CategoryNotFoundException;
import com.timesheet.app.exception.OptimisticLockException;
import com.timesheet.app.model.Category;
import com.timesheet.app.repository.CategoryRepository;
import com.timesheet.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return repository.findByDeletedFalse();
    }

    @Override
    public Category getById(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    @Transactional
    public Category update(Category category) {
        Category existingCategory = repository.findByIdAndDeletedFalse(category.getId()).orElseThrow(CategoryNotFoundException::new);
        existingCategory.setName(category.getName());
        Category result = null;
        try{
            result = repository.save(existingCategory);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new OptimisticLockException();
        }
        return result;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = repository.findByIdAndDeletedFalse(id).orElseThrow(CategoryNotFoundException::new);
        category.setDeleted(true);
        try{
            repository.save(category);
        } catch (ObjectOptimisticLockingFailureException ex){
            throw new OptimisticLockException();
        }

    }
}
