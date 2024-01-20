package com.example.transactions.controller;

import com.example.transactions.entity.Category;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.object.response.CategoryRO;
import com.example.transactions.services.category.ICategoryService;
import org.apache.tomcat.util.http.ResponseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private ICategoryService categoryService;

    @Test
    public void testGetAllCategories() {

        CategoryRO category = new CategoryRO();
        category.setId(1L);category.setName("Category1");
        CategoryRO category2 = new CategoryRO();
        category2.setId(2L);category2.setName("Category2");

        List<CategoryRO> mockCategories = Arrays.asList(category,category2);
        when(categoryService.getAllCategories()).thenReturn(mockCategories);
        ResponseEntity<ControllerBaseResponse> responseEntity = categoryController.getAllCategories();
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<Category> responseCategories = (List<Category>) responseEntity.getBody().getData();
        assertEquals(mockCategories.size(), responseCategories.size());
    }

    @Test
    public void testGetCategoryById() throws ValidationException {
        Long categoryId = 1L;
        CategoryRO mockCategory = new CategoryRO();
        mockCategory.setId(1L);mockCategory.setName("Category1");

        when(categoryService.getCategoryById(categoryId)).thenReturn(mockCategory);
        ResponseEntity<ControllerBaseResponse> responseEntity = categoryController.getAllCategories(categoryId);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Category responseCategory = (Category) responseEntity.getBody().getData();
        assertEquals(mockCategory, responseCategory);
    }

}
