package za.ac.cput.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ProductCategory;
import za.ac.cput.repository.ProductCategoryRepository;
import za.ac.cput.service.ProductCategoryService;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository repository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductCategory create(ProductCategory category) {
        return repository.save(category);
    }

    @Override
    public ProductCategory read(String categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public ProductCategory update(ProductCategory category) {
        if (repository.existsById(category.getCategoryId())) {
            return repository.save(category);
        }
        return null;
    }

    @Override
    public boolean delete(String categoryId) {
        if (repository.existsById(categoryId)) {
            repository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    @Override
    public List<ProductCategory> getAll() {
        return repository.findAll();
    }
}
