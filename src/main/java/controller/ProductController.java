package controller;


import lombok.extern.slf4j.Slf4j;
import object.response.ProductRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @GetMapping
    public List<ProductRO> getAllProducts() {
        return new ArrayList<>();
    }
}
