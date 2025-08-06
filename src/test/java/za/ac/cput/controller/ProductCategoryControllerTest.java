package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Service.impl.ProductCategoryServiceImpl;
import za.ac.cput.domain.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryControllerTest {

    private ProductCategoryController categoryController;

    @BeforeEach
    void setUp() {
        categoryController = new ProductCategoryController(new ProductCategoryServiceImpl());
    }

    @Test
    void testCreateCategory() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT005")
                .setCategoryName("Anime Stickers")
                .build();

        categoryController.create(category);
        List<ProductCategory> all = categoryController.getAll();
        assertEquals(1, all.size());
        assertEquals("Anime Stickers", all.get(0).getCategoryName());
    }

    @Test
    void testGetById() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT006")
                .setCategoryName("Anime Plushies")
                .build();

        categoryController.create(category);
        ProductCategory found = categoryController.getById(Integer.parseInt("CAT006"));
        assertNotNull(found);
        assertEquals("Anime Plushies", found.getCategoryName());
    }

    @Test
    void testDeleteCategory() {
        ProductCategory category = new ProductCategory.Builder()
                .setCategoryId("CAT007")
                .setCategoryName("Anime Caps")
                .build();

        categoryController.create(category);
        assertFalse(categoryController.getAll().isEmpty());

        categoryController.delete(Integer.parseInt("CAT007"));
        assertTrue(categoryController.getAll().isEmpty());
    }
}
