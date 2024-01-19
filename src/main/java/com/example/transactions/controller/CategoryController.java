package com.example.transactions.controller;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.util.ControllerResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.transactions.services.category.ICategoryService;

@Slf4j
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/getAllCategories")
    public ResponseEntity<ControllerBaseResponse> getAllCategories() {
        log.info("Api: /v1/category/getAllCategories");
        return ResponseEntity.ok(ControllerResponseUtil.getSuccessResponse(categoryService.getAllCategories()));
    }

    @GetMapping("getCategory/{categoryId}")
    public ResponseEntity<ControllerBaseResponse> getAllCategories(@PathVariable Long categoryId) throws ValidationException {
        log.info("Api: /v1/category/getCategory , categoryId : {}",categoryId);
        return ResponseEntity.ok(ControllerResponseUtil.getSuccessResponse(categoryService.getCategoryById(categoryId)));
    }
}
