package za.ac.cput.service;

import za.ac.cput.domain.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    ProductCategory save(ProductCategory category);
    ProductCategory getById(String id);
    List<ProductCategory> getAll();
    boolean delete(String id);
}
