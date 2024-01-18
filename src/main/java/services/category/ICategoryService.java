package services.category;

import exception.ValidationException;
import object.response.CategoryRO;

import java.util.List;

public interface ICategoryService {
    List<CategoryRO> getAllCategories();

    CategoryRO getCategoryById(Long id) throws ValidationException;
}
