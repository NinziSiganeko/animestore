package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Inventory;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {
    public static Inventory inventory1 = InventoryFactory.createInventory(1, "Cape Town");
    public static Inventory inventory2 = InventoryFactory.createInventory(2, "Durban");
    public static Inventory inventory3 = InventoryFactory.createInventory(0, ""); // Invalid test

    @Test
    @Order(1)
    public void testCreateInventory() {
        assertNotNull(inventory1);
        System.out.println(inventory1.toString());
    }

    @Test
    @Order(2)
    public void testCreateInventoryWithAllAttributes() {
        assertNotNull(inventory2);
        System.out.println(inventory2.toString());
    }

    @Test
    @Order(3)
    public void testCreateInventoryThatFails() {
        assertNull(inventory3);
        System.out.println(inventory3);
    }

    @Test
    @Disabled
    @Order(4)
    public void testNotImplemented() {
    }
}
