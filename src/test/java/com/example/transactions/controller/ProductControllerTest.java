package com.example.transactions.controller;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.object.response.ProductRO;
import com.example.transactions.services.product.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductService productService;

    @Test
    public void testGetProductById() throws ValidationException {
        // Mocking data
        Long productId = 1L;
        ProductRO mockProduct = new ProductRO();
        mockProduct.setId(1L);
        mockProduct.setName("Sample Product");
        mockProduct.setPrice(10.99);
        when(productService.getProductById(productId)).thenReturn(mockProduct);
        ResponseEntity<ControllerBaseResponse> responseEntity = productController.getProductById(productId);
        assertEquals(200, responseEntity.getStatusCodeValue());

        ProductRO responseProduct = (ProductRO) responseEntity.getBody().getData();
        assertEquals(mockProduct, responseProduct);
    }

}
