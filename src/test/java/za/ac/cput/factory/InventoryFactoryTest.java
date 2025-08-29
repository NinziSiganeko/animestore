package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductStatus;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryFactoryTest {

    private Product createSampleProduct() {
        return new Product.Builder()
                .setProductId(1L)
                .setName("One Piece T-Shirt")
                .setPrice(299.99)
                .setStock(50)
                .setCategory(null) // Assuming category not needed here
                .build();
    }

    @Test
    @Order(1)
    void createInventorySuccess() {
        Product product = createSampleProduct();

        Inventory inventory = InventoryFactory.createInventory(product, ProductStatus.OUT_OF_STOCK);

        assertNotNull(inventory);
        assertEquals(product, inventory.getProduct());
        assertEquals(ProductStatus.OUT_OF_STOCK, inventory.getStatus());

        System.out.println("✅ Inventory created successfully: " + inventory);
    }

    @Test
    @Order(2)
    void createInventoryFailNullProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InventoryFactory.createInventory(null, ProductStatus.DISCONTINUED);
        });

        System.out.println(" Expected error: " + exception.getMessage());
    }

    @Test
    @Order(3)
    void createInventoryFailNullStatus() {
        Product product = createSampleProduct();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InventoryFactory.createInventory(product, null);
        });

        System.out.println(" Expected error: " + exception.getMessage());
    }
}
