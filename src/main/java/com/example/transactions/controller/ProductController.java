package com.example.transactions.controller;


import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.services.product.IProductService;
import com.example.transactions.util.ControllerResponseUtil;
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
    public ResponseEntity<ControllerBaseResponse> getProductById(@PathVariable Long productId) throws ValidationException {
        log.info("Api: /v1/product/id/ , productId : {}",productId);
        return ResponseEntity.ok(ControllerResponseUtil.getSuccessResponse(productService.getProductById(productId)));
    }
}
