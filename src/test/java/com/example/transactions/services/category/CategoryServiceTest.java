package com.example.transactions.services.category;

import com.example.transactions.dao.ICategoryRepository;
import com.example.transactions.entity.Category;
import com.example.transactions.enums.Status;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.response.CategoryRO;
import com.example.transactions.services.category.impl.CategoryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private ICategoryRepository categoryRepository;

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        category1.setId(1L);category1.setName("Category1");category1.setStatus(Status.ACTIVE);
        Category category2 = new Category();
        category2.setId(2L);category2.setName("Category2");category2.setStatus(Status.ACTIVE);

        List<Category> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(category1);
        mockCategoryList.add(category2);

        when(categoryRepository.findAll()).thenReturn(mockCategoryList);

        List<CategoryRO> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        assertEquals(category1.getId(), result.get(0).getId());
        assertEquals(category1.getName(), result.get(0).getName());
        assertEquals(category1.getStatus(), result.get(0).getStatus());

        assertEquals(category2.getId(), result.get(1).getId());
        assertEquals(category2.getName(), result.get(1).getName());
        assertEquals(category2.getStatus(), result.get(1).getStatus());
    }

    @Test
    public void testGetCategoryById() throws ValidationException {
        long categoryId = 1L;
        Category category = new Category();
        category.setId(1L);category.setName("Category1");category.setStatus(Status.ACTIVE);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        CategoryRO result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getStatus(), result.getStatus());
    }

    @Test(expected = ValidationException.class)
    public void testGetCategoryByIdCategoryNotFoundException() throws ValidationException {
        long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        categoryService.getCategoryById(categoryId);
    }
}
