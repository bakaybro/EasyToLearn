package com.example.easytolearn.service;

import com.example.easytolearn.entity.Category;
import com.example.easytolearn.model.CategoryModel;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    CategoryModel createCategory(String categoryName);

    CategoryModel updateCategory(CategoryModel categoryModel);

    CategoryModel getCategoryModelById(Long id);

    CategoryModel getByCategoryName(String categoryName);

    List<CategoryModel> getAllCategoryModel();
}