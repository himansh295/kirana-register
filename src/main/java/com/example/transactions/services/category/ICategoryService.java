package com.example.transactions.services.category;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.response.CategoryRO;

import java.util.List;

public interface ICategoryService {
    List<CategoryRO> getAllCategories();

    CategoryRO getCategoryById(Long id) throws ValidationException;
}
