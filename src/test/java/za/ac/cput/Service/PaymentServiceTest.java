package za.ac.cput.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.factory.PaymentFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentServiceTest {
    @Autowired
    private IPaymentService service;

    private static Payment payment = PaymentFactory.createPayment(75469697L, "ORD-101", "CUST-9001",
                                    1299.99, PaymentMethod.CREDIT_CARD, PaymentStatus.COMPLETED, "TXN-0001",
                                    "1234567812345678", LocalDateTime.now());

    @Test
    void a_create() {
        Payment created = service.create(payment);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Payment read = service.read(payment.getPaymentId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Payment updatedPayment = new Payment.Builder()
                .copy(payment)
                .setAmount(1399.99)
                .build();
        Payment updated = service.update(updatedPayment);
        assertNotNull(updated);
        assertEquals(1399.99, updated.getAmount());
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        boolean deleted = service.delete(payment.getPaymentId());
        assertTrue(deleted);
        System.out.println("Deleted: " + true);
    }

    @Test
    void e_getAll() {
        System.out.println("All Payments: " + service.getAll());
    }
}