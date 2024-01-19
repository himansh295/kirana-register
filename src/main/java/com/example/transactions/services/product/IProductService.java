package com.example.transactions.services.product;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.response.ProductRO;

public interface IProductService {
    ProductRO getProductById(Long productId) throws ValidationException;
}
