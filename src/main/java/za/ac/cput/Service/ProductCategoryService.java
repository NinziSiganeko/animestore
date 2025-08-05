package za.ac.cput.Service;

import za.ac.cput.domain.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    ProductCategory save(ProductCategory category);
    ProductCategory getById(int id);
    List<ProductCategory> getAll();
    void delete(int id);
}
