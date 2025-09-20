package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductCategory;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.ProductCategoryRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173") // React frontend
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    // ================= GET =================
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // ================= CREATE (with image upload, using categoryId) =================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("category_Id") Long categoryId,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage
    ) throws IOException {
        // ✅ Make sure the categoryId exists
        ProductCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Product product = new Product.Builder()
                .setName(name)
                .setPrice(price)
                .setStock(stock)
                .setCategory(category)
                .build();

        if (productImage != null && !productImage.isEmpty()) {
            product.setProductImage(productImage.getBytes());
        }

        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) return null;

        Product updatedProduct = new Product.Builder()
                .copy(existingProduct)
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setStock(product.getStock())
                .setCategory(product.getCategory())
                .setProductImage(product.getProductImage())
                .build();

        return productRepository.save(updatedProduct);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    // ================= Upload Image separately (optional) =================
    @PostMapping("/{id}/upload-image")
    public Product uploadProductImage(
            @PathVariable Long id,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) return null;

        existingProduct.setProductImage(imageFile.getBytes());
        return productRepository.save(existingProduct);
    }
}
