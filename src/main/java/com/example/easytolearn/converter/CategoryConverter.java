package com.example.easytolearn.converter;

import com.example.easytolearn.entity.Category;
import com.example.easytolearn.model.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends BaseConverter<CategoryModel, Category> {

    public CategoryConverter() {
        super(CategoryConverter::convertToEntity, CategoryConverter::convertToModel);
    }

    private static CategoryModel convertToModel(Category entityToConvert) {
        if (entityToConvert == null) return null;

        return CategoryModel.builder()
                .id(entityToConvert.getId())
                .categoryName(entityToConvert.getCategoryName())
                .build();
    }

    private static Category convertToEntity(CategoryModel modelToConvert) {
        if (modelToConvert == null) return null;

        Category category = new Category();
        category.setId(modelToConvert.getId());
        category.setCategoryName(modelToConvert.getCategoryName());

        return category;
    }
}