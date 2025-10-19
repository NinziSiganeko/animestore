package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductCategory;
import za.ac.cput.service.ProductService;
import za.ac.cput.service.ProductCategoryService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService; // ADD THIS

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam int stock,
            @RequestParam Long category_Id,
            @RequestParam(required = false) MultipartFile productImage) throws IOException {

        // 1. GET THE CATEGORY FROM DATABASE
        ProductCategory category = categoryService.getById(category_Id);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        // 2. CREATE PRODUCT WITH THE CATEGORY
        Product product = new Product.Builder()
                .setName(name)
                .setPrice(price)
                .setStock(stock)
                .setCategory(category) // SET THE CATEGORY HERE
                .build();

        if (productImage != null && !productImage.isEmpty()) {
            product.setProductImage(productImage.getBytes());
        }

        // 3. SAVE PRODUCT (Inventory will be auto-created in ProductService/InventoryService)
        Product savedProduct = productService.create(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        // For update, use builder to set the ID
        Product productToUpdate = new Product.Builder()
                .setProductId(id)
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setStock(product.getStock())
                .setCategory(product.getCategory())
                .build();

        Product updatedProduct = productService.update(productToUpdate);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}