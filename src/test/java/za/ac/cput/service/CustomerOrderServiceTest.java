package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.factory.CustomerOrderFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerOrderServiceTest {

    @Autowired
    private CustomerOrderService customerOrderService;

    private CustomerOrder customerOrder;
    private CustomerOrder customerOrder2;

    @BeforeEach
    void setUp() {
        customerOrder = CustomerOrderFactory.createOrder(1, LocalDate.now(), "pending");
        customerOrder2  = CustomerOrderFactory.createOrder(2, LocalDate.now(), "received");

    }

    @Test
    void create() {
        CustomerOrder created = customerOrderService.create(customerOrder);
        assertNotNull(created);
        System.out.println(created);

        CustomerOrder created2 = customerOrderService.create(customerOrder2);
        assertNotNull(created2);
        System.out.println(created2);
    }

    @Test
    void read() {
        CustomerOrder read = customerOrderService.read(1);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        CustomerOrder customerOrder1 = new CustomerOrder.Builder().copy(customerOrder).setStatus("Delivered")
                .build();
        CustomerOrder updated = customerOrderService.update(customerOrder1);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void delete() {
        boolean deleted = customerOrderService.delete(1);
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        List<CustomerOrder> all = customerOrderService.getAll();
        System.out.println(all);
    }
}