package com.example.transactions.controller;

import com.example.transactions.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.response.CategoryRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.transactions.services.category.ICategoryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<CategoryRO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("getCategory/{id}")
    public CategoryRO getAllCategories(@PathVariable Long id) throws ValidationException {
        return categoryService.getCategoryById(id);
    }
}
