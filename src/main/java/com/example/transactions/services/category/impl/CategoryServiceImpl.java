package com.example.transactions.services.category.impl;

import com.example.transactions.dao.ICategoryRepository;
import com.example.transactions.entity.Category;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.services.category.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.response.CategoryRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryRO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryRO> categoryROList = new ArrayList<>();
        for(Category category : categoryList) {
            CategoryRO categoryRO = new CategoryRO();
            categoryRO.setId(category.getId());
            categoryRO.setName(category.getName());
            categoryRO.setStatus(category.getStatus());
            categoryROList.add(categoryRO);
        }

        return categoryROList;
    }

    @Transactional
    @Override
    public CategoryRO getCategoryById(Long id) throws ValidationException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent()) {
            throw new ValidationException("Category Not present","400");
        }

        Category category = categoryOptional.get();
        CategoryRO categoryRO = new CategoryRO();
        categoryRO.setId(category.getId());
        categoryRO.setName(category.getName());
        categoryRO.setStatus(category.getStatus());

        return categoryRO;
    }
}
