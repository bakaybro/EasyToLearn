package com.example.easytolearn.controller;

import com.example.easytolearn.model.CategoryModel;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseMessage<List<CategoryModel>> getAll() {
       return new ResponseMessage<List<CategoryModel>>()
                .prepareSuccessMessage(categoryService.getAllCategoryModel());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<CategoryModel> getById(@PathVariable Long id) {
        return new ResponseMessage<CategoryModel>()
                .prepareSuccessMessage(categoryService.getCategoryModelById(id));
    }
}