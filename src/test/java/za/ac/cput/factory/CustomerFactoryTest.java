package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;
class CustomerFactoryTest {
    public static Customer customer1 = CustomerFactory.createCustomer(1L, "John", "Doe", "veronicapuleng91@gmail.com", "123 Main St", "0672345678");
    public static Customer customer2 = CustomerFactory.createCustomer(2L, "Jane", "Doe", "222914556@mycput.ac.za", "456 Elm St", "0672345678");
    public static Customer customer3 = CustomerFactory.createCustomer(3L, "Invalid", "Customer", "invalidemail.com", "789 Oak St", "1234567890");



    @Test
    @Order(1)
    public void testCreateCustomer() {
        assertNotNull(customer1);
        System.out.println(customer1.toString());
    }

    @Test
    @Order(2)
    public void testCreateCustomerWithAllAttributes() {
        assertNotNull(customer2);
        System.out.println(customer2.toString());
    }

    @Test
    @Order(3)
    public void testCreateCustomerThatFails() {
        assertNull(customer3);
        System.out.println(customer3);
    }

    @Test
    @Disabled
    @Order(4)
    public void testNotImplemented() {
    }
}
  
