package controller;

import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.CategoryRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.category.ICategoryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/getAllCategories")
    public List<CategoryRO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "getCategory/{id}")
    public CategoryRO getAllCategories(@PathVariable Long id) throws ValidationException {
        return categoryService.getCategoryById(id);
    }
}
