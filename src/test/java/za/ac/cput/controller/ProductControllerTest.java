package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Service.impl.ProductServiceImpl;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private ProductController productController;

    @BeforeEach
    void setUp() {
        productController = new ProductController(new ProductServiceImpl());
    }

    @Test
    void testCreateAndGetProduct() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT003")
                .setCategoryName("Anime T-Shirts")
                .build();

        Product product = new Product.Builder()
                .setProductId("PROD004")
                .setName("Itachi Uchiha T-Shirt")
                .setPrice(199.99)
                .setCategory(category)
                .build();

        productController.create(product);
        List<Product> products = productController.getAll();

        assertEquals(1, products.size());
        assertEquals("Itachi Uchiha T-Shirt", products.get(0).getName());
    }

    @Test
    void testGetById() {
        Product product = new Product.Builder()
                .setProductId("PROD005")
                .setName("Demon Slayer Mug")
                .setPrice(59.99)
                .build();

        productController.create(product);
        Product found = productController.getById(0); // hardcoded index
        assertNotNull(found);
        assertEquals("Demon Slayer Mug", found.getName());
    }

    @Test
    void testDelete() {
        Product product = new Product.Builder()
                .setProductId("PROD006")
                .setName("Jujutsu Kaisen Keychain")
                .setPrice(49.99)
                .build();

        productController.create(product);
        assertFalse(productController.getAll().isEmpty());

        productController.delete(0); // assuming index-based mock delete
        assertTrue(productController.getAll().isEmpty());
    }
}
