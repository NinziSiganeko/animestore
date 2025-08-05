package za.ac.cput.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ProductCategory;
import za.ac.cput.Repository.ProductCategoryRepository;
import za.ac.cput.Service.ProductCategoryService;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory save(ProductCategory category) {
        return repository.save(category);
    }

    @Override
    public ProductCategory getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProductCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
