package za.ac.cput.Service;

import za.ac.cput.domain.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product read(String productId);
    Product update(Product product);
    boolean delete(String productId);
    List<Product> getAll();
}
