package com.example.transactions.services.product;

import com.example.transactions.dao.IProductRepository;
import com.example.transactions.entity.Category;
import com.example.transactions.entity.Product;
import com.example.transactions.enums.Currency;
import com.example.transactions.enums.Status;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.response.CategoryRO;
import com.example.transactions.object.response.ProductRO;
import com.example.transactions.services.product.impl.ProductServiceImpl;
import jakarta.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class ProductServiceTest {

    @Inject
    private ProductServiceImpl productService;

    @Mock
    private IProductRepository productRepository;

    @Test
    void getProductByIdTest() throws ValidationException {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setStatus(Status.ACTIVE);
        product.setPrice(20.0);

        Category category = new Category();
        category.setId(1L);
        category.setName("TestCategory");
        category.setStatus(Status.ACTIVE);

        product.setCategory(category);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductRO result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getStatus(), result.getStatus());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(Currency.INR, result.getCurrency());

        CategoryRO categoryRO = result.getCategoryRO();
        assertNotNull(categoryRO);
        assertEquals(category.getId(), categoryRO.getId());
        assertEquals(category.getName(), categoryRO.getName());
        assertEquals(category.getStatus(), categoryRO.getStatus());
    }

    @Test(expected = ValidationException.class)
    void getProductByIdValidationExceptionTest() throws ValidationException {
        Long invalidProductId = 2L;

        when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());
        productService.getProductById(invalidProductId);
    }
}
