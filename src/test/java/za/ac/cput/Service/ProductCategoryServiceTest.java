package za.ac.cput.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Service.impl.ProductCategoryServiceImpl;
import za.ac.cput.domain.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryServiceTest {

    private ProductCategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new ProductCategoryServiceImpl();
    }

    @Test
    void testSaveCategory() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT001")
                .setCategoryName("Anime Figures")
                .build();

        ProductCategory saved = categoryService.save(category);
        assertNotNull(saved);
        assertEquals("Anime Figures", saved.getCategoryName());
    }

    @Test
    void testGetAllCategories() {
        ProductCategory category1 = new ProductCategory.Builder()
                .setCategoryId("CAT002")
                .setCategoryName("Anime Posters")
                .build();

        ProductCategory category2 = new ProductCategory.Builder()
                .setCategoryId("CAT003")
                .setCategoryName("Anime Hoodies")
                .build();

        categoryService.save(category1);
        categoryService.save(category2);

        List<ProductCategory> all = categoryService.getAll();
        assertEquals(2, all.size());
    }

    @Test
    void testDeleteCategory() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT004")
                .setCategoryName("Anime Keychains")
                .build();

        categoryService.save(category);
        assertFalse(categoryService.getAll().isEmpty());

        categoryService.delete(Integer.parseInt("CAT004"));
        assertTrue(categoryService.getAll().isEmpty());
    }
}
