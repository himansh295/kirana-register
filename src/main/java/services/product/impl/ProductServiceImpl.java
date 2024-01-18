package services.product.impl;

import dao.IProductRepository;
import entity.Category;
import entity.Product;
import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.response.CategoryRO;
import object.response.ProductRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.product.IProductService;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductRO getProductById(Long productId) throws ValidationException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(!productOptional.isPresent()) {
            throw new ValidationException("Invalid Product Id");
        }

        Product product = productOptional.get();
        ProductRO productRO = new ProductRO();
        productRO.setId(productId);
        productRO.setName(product.getName());
        productRO.setStatus(product.getStatus());
        productRO.setPrice(product.getPrice());

        Category category = product.getCategory();
        CategoryRO categoryRO = new CategoryRO();
        categoryRO.setId(category.getId());
        categoryRO.setName(category.getName());
        categoryRO.setStatus(category.getStatus());

        productRO.setCategoryRO(categoryRO);

        return productRO;
    }
}
