/*OrderitemfactoryTest.java
Author : N Mshweshwe
date: 17 may 2025
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemFactoryTest {

    static OrderItem item1;
    static OrderItem item2;
    static OrderItem invalidItem;

    @BeforeAll
    static void setUp() {
        item1 = OrderItemFactory.createOrderItem(2, 199.99);
        item2 = OrderItemFactory.createOrderItem(5, 499.50);
        invalidItem = OrderItemFactory.createOrderItem(0, -1); // Invalid input
    }

    @Test
    @Order(1)
    void testCreateValidItem1() {
        assertNotNull(item1);
        System.out.println(item1);
    }

    @Test
    @Order(2)
    void testCreateValidItem2() {
        assertNotNull(item2);
        System.out.println(item2);
    }

    @Test
    @Order(3)
    void testCreateInvalidItem() {
        assertNull(invalidItem);
        System.out.println("Invalid item creation returned null as expected.");
    }
}