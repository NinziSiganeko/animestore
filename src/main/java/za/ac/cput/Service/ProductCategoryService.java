package za.ac.cput.Service;

import za.ac.cput.domain.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    ProductCategory create(ProductCategory category);
    ProductCategory read(String categoryId);
    ProductCategory update(ProductCategory category);
    boolean delete(String categoryId);
    List<ProductCategory> getAll();
}
