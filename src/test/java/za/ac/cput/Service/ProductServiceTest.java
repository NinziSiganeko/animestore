package za.ac.cput.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Service.impl.ProductServiceImpl;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    void testSaveProduct() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT001")
                .setCategoryName("Anime Figures")
                .build();

        Product product = new Product.Builder()
                .setProductId(Long.valueOf("PROD001"))
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
                .setProductId(Long.valueOf("PROD002"))
                .setName("Naruto Poster")
                .setPrice(99.99)
                .setCategory(category)
                .build();

        productService.save(product);
        List<Product> all = productService.getAll();

        assertFalse(all.isEmpty());
        assertEquals("Naruto Poster", all.get(0).getName());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product.Builder()
                .setProductId(Long.valueOf("PROD003"))
                .setName("Zoro Sword")
                .setPrice(399.99)
                .build();

        productService.save(product);
        assertFalse(productService.getAll().isEmpty());

        productService.delete(Integer.parseInt("PROD003"));
        assertTrue(productService.getAll().isEmpty());
    }
}
