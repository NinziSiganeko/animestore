package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    private Admin admin;
    @BeforeEach
    void setUp() {
        admin = AdminFactory.createAdmin(
                "John_Doe", "John", "Doe", "veronicapuleng91@gmail.com", "123 Main St", "0672345678");

    }

    @Test
    void createAdmin() {
        assertNotNull(admin);
        System.out.println(admin);
    }
}
