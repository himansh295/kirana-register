package com.example.transactions.services.product.impl;

import com.example.transactions.dao.IProductRepository;
import com.example.transactions.entity.Category;
import com.example.transactions.entity.Product;
import com.example.transactions.enums.Currency;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.services.product.IProductService;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.response.CategoryRO;
import com.example.transactions.object.response.ProductRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Transactional
    @Override
    public ProductRO getProductById(Long productId) throws ValidationException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(!productOptional.isPresent()) {
            throw new ValidationException("Invalid Product Id","400");
        }

        Product product = productOptional.get();
        ProductRO productRO = new ProductRO();
        productRO.setId(productId);
        productRO.setName(product.getName());
        productRO.setStatus(product.getStatus());
        productRO.setPrice(product.getPrice());
        productRO.setCurrency(Currency.INR);

        Category category = product.getCategory();
        CategoryRO categoryRO = new CategoryRO();
        categoryRO.setId(category.getId());
        categoryRO.setName(category.getName());
        categoryRO.setStatus(category.getStatus());

        productRO.setCategoryRO(categoryRO);

        return productRO;
    }
}
