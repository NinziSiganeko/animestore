package za.ac.cput.service;

import za.ac.cput.domain.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product update(Product product);
    Product getById(Long id);
    List<Product> getAll();
    boolean delete(Long id);
}
