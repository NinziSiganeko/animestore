package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.service.impl.ProductServiceImpl;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;


import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000") // React app address
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/read/{id}")
    public Product getById(@PathVariable String id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        productService.delete(id);
    }
}
