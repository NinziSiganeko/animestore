package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.Payment;
import za.ac.cput.factory.CustomerOrderFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerOrderServiceTest {

    @Autowired
    private ICustomerOrderService service;
    private static CustomerOrder customerOrder,customerOrder2;

    @BeforeEach
    void setUp() {
        customerOrder = CustomerOrderFactory.createOrder( LocalDateTime.now(), "pending");
        customerOrder2  = CustomerOrderFactory.createOrder( LocalDateTime.now(), "received");
    }

    @Test
    void create() {
        CustomerOrder created = service.create(customerOrder);
        assertNotNull(created);
        System.out.println(created);

        CustomerOrder created2 = service.create(customerOrder2);
        assertNotNull(created2);
        System.out.println(created2);
    }

    @Test
    void read() {

        CustomerOrder read = service.read(customerOrder.getCustomerOrderId());
        assertNotNull(read);
        assertEquals(customerOrder.getCustomerOrderId(), read.getCustomerOrderId());
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        CustomerOrder customerOrder1 = new CustomerOrder.Builder().copy(customerOrder).setStatus("Delivered")
                .build();
        CustomerOrder updated = service.update(customerOrder1);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void delete() {
        boolean deleted = service.delete(customerOrder.getCustomerOrderId());
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        List<CustomerOrder> all = service.getAll();
        System.out.println(all);
    }
}