package za.ac.cput.Service;

import za.ac.cput.domain.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product getById(int id);
    List<Product> getAll();
    void delete(int id);
}
