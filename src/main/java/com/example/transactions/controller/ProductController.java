package com.example.transactions.controller;


import com.example.transactions.exception.ValidationException;
import com.example.transactions.services.product.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/id/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId) throws ValidationException {
        try{
            return ResponseEntity.ok(productService.getProductById(productId));
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
