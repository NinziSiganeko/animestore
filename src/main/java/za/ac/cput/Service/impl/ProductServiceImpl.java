package za.ac.cput.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product read(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public Product update(Product product) {
        if (repository.existsById(product.getProductId())) {
            return repository.save(product);
        }
        return null;
    }

    @Override
    public boolean delete(String productId) {
        if (repository.existsById(productId)) {
            repository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
}
