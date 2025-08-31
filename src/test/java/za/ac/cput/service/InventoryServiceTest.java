package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Delivery;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductStatus;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;
    private Inventory inventory1;
    private Inventory inventory2;

    @BeforeEach
    void setUp() {
        Product product = ProductFactory.createProduct("P009", "Manga Book", 199.99, 50);

        Product product2 = ProductFactory.createProduct("P0010", "Manga Book 10", 199.99, 50);

        inventory1 = InventoryFactory.createInventory(product, ProductStatus.Few_IN_STOCK);
        inventory2 = InventoryFactory.createInventory(product2, ProductStatus.Few_IN_STOCK);

    }

    @Test
    void create() {
        Inventory created = inventoryService.create(inventory1);
        assertNotNull(created);
        System.out.println(created);

        Inventory created2 = inventoryService.create(inventory2);
        assertNotNull(created2);
        System.out.println(created2);
    }

    @Test
    void read() {
        Inventory read = inventoryService.read(2L);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        Inventory inventory = new Inventory.Builder().copy(inventory1).setStatus(ProductStatus.DISCONTINUED)
                .build();
        Inventory updated = inventoryService.update(inventory);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void delete() {
        boolean deleted = inventoryService.delete(1L);
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        List<Inventory> all = inventoryService.getAll();
        System.out.println(all);
    }
}