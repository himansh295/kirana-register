package services.product;

import exception.ValidationException;
import object.response.ProductRO;

public interface IProductService {
    ProductRO getProductById(Long productId) throws ValidationException;
}
