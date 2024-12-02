package com.timesheet.app.data;

import com.timesheet.app.model.Category;

public class CategoryServiceConstants {
    public static final Long CATEGORY_ID = 1L;
    public static final String CATEGORY_NAME = "Test Category";
    public static final boolean IS_DELETED_FALSE = false;

    public static final String CATEGORY_NAME_UPDATED = "Test Category Updated";
    public static final boolean IS_DELETED_TRUE = true;

    public static final Category CATEGORY = new Category(CATEGORY_ID, CATEGORY_NAME, IS_DELETED_FALSE);

    public static final Category UPDATED_CATEGORY = new Category(CategoryServiceConstants.CATEGORY_ID, CategoryServiceConstants.CATEGORY_NAME_UPDATED, CategoryServiceConstants.IS_DELETED_FALSE);

    public static final Category DELETED_CATEGORY = new Category(CategoryServiceConstants.CATEGORY_ID, CategoryServiceConstants.CATEGORY_NAME, CategoryServiceConstants.IS_DELETED_TRUE);
}
