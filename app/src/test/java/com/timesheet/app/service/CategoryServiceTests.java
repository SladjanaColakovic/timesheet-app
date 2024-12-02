package com.timesheet.app.service;

import com.timesheet.app.data.CategoryServiceConstants;
import com.timesheet.app.exception.CategoryNotFoundException;
import com.timesheet.app.model.Category;
import com.timesheet.app.repository.CategoryRepository;
import com.timesheet.app.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryServiceImpl service;

    @Test
    void testGetById_Successful(){
        setUpTestsSuccessful();
        Category result = service.getById(CategoryServiceConstants.CATEGORY_ID);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(CategoryServiceConstants.CATEGORY_ID);
        verify(repository, times(1)).findByIdAndDeletedFalse(any());
    }

    @Test
    void testGetById_Failure(){
        setUpTestsFailure();
        Category result = null;
        try {
            result = service.getById(CategoryServiceConstants.CATEGORY_ID);
        } catch (CategoryNotFoundException ex){
            assertThat(result).isNull();
            verify(repository, times(1)).findByIdAndDeletedFalse(any());
        }
    }

    @Test
    void testUpdate_Successful(){
        setUpTestUpdateSuccessful();
        Category category = new Category(CategoryServiceConstants.CATEGORY_ID, CategoryServiceConstants.CATEGORY_NAME_UPDATED, CategoryServiceConstants.IS_DELETED_FALSE);
        Category result = service.update(category);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(CategoryServiceConstants.CATEGORY_NAME_UPDATED);
        verify(repository, times(1)).findByIdAndDeletedFalse(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate_Failure(){
        setUpTestsFailure();
        Category category = new Category(CategoryServiceConstants.CATEGORY_ID, CategoryServiceConstants.CATEGORY_NAME_UPDATED, CategoryServiceConstants.IS_DELETED_TRUE);
        Category result = null;
        try {
            result = service.update(category);
        } catch (CategoryNotFoundException ex){
            assertThat(result).isNull();
            verify(repository, times(1)).findByIdAndDeletedFalse(any());
            verify(repository, never()).save(any());
        }
    }

    @Test
    void testDelete_Successful(){
        setUpTestDeleteSuccessful();
        service.delete(CategoryServiceConstants.CATEGORY_ID);
        verify(repository, times(1)).findByIdAndDeletedFalse(any());
        verify(repository, times(1)).save(any());

    }

    @Test
    void testDelete_Failure(){
        setUpTestsFailure();
        try {
            service.delete(CategoryServiceConstants.CATEGORY_ID);
        } catch(CategoryNotFoundException ex) {
            verify(repository, times(1)).findByIdAndDeletedFalse(any());
            verify(repository, never()).save(any());
        }

    }

    private void setUpTestsSuccessful(){
        when(repository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(CategoryServiceConstants.CATEGORY));
    }

    private void setUpTestsFailure(){
        when(repository.findByIdAndDeletedFalse(any())).thenThrow(new CategoryNotFoundException());
    }

    private void setUpTestUpdateSuccessful(){
        setUpTestsSuccessful();
        when(repository.save(any())).thenReturn(CategoryServiceConstants.UPDATED_CATEGORY);
    }

    private void setUpTestDeleteSuccessful(){
        setUpTestsSuccessful();
        when(repository.save(any())).thenReturn(CategoryServiceConstants.DELETED_CATEGORY);
    }

}
