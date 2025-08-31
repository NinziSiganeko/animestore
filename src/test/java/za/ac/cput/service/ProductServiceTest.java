package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.service.impl.ProductServiceImpl;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void testSaveProduct() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT001")
                .setCategoryName("Anime Figures")
                .build();

        Product product = new Product.Builder()
                .setProductId("PROD001")
                .setName("Luffy Action Figure")
                .setPrice(299.99)
                .setCategory(category)
                .build();

        Product saved = productService.save(product);
        assertNotNull(saved);
        assertEquals("PROD001", saved.getProductId());
    }

    @Test
    void testGetAllProducts() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT002")
                .setCategoryName("Anime Posters")
                .build();

        Product product = new Product.Builder()
                .setProductId("PROD002")
                .setName("Naruto Poster")
                .setPrice(99.99)
                .setCategory(category)
                .build();

        productService.save(product);
        List<Product> all = productService.getAll();

        assertFalse(all.isEmpty());
        System.out.println("All: " + all);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product.Builder()
                .setProductId("PROD003")
                .setName("Zoro Sword")
                .setPrice(399.99)
                .build();

        productService.save(product);

        boolean isDeleted = productService.delete("PROD003");
        assertTrue(isDeleted);
    }
}
