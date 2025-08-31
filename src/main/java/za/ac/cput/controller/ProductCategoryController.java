package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.service.impl.ProductCategoryServiceImpl;
import za.ac.cput.domain.ProductCategory;
import za.ac.cput.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000") // if using React frontend
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    @PostMapping("/save")
    public ProductCategory create(@RequestBody ProductCategory category) {
        return categoryService.save(category);
    }

    @GetMapping("/getAll")
    public List<ProductCategory> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/read/{id}")
    public ProductCategory getById(@PathVariable String id) {
        return categoryService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        categoryService.delete(id);
    }
}
