package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ProductCategory;
import za.ac.cput.Service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000") // if using React frontend
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    @PostMapping
    public ProductCategory create(@RequestBody ProductCategory category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<ProductCategory> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ProductCategory getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
