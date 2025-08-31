package za.ac.cput.service;

import za.ac.cput.domain.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product getById(String id);
    List<Product> getAll();
    boolean delete(String id);
}
