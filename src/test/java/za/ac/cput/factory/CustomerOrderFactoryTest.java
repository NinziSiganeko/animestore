package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CustomerOrder;;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderFactoryTest {

    private CustomerOrder customerOrder;
    @BeforeEach
    void setUp() {
        customerOrder = CustomerOrderFactory.createOrder( LocalDateTime.now(), "pending");
    }

    @Test
    void createOrder() {
        assertNotNull(customerOrder);
        System.out.println(customerOrder);
    }
}