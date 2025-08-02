package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentFactoryTest {

    private static LocalDateTime date;
    private static Payment payment1 = PaymentFactory.createPayment(75469697L,"ORD-87546", "CUST-39485", 499.99, PaymentMethod.CREDIT_CARD, PaymentStatus.COMPLETED, "TXN-298374","1234567812345678", date);

    private static Payment payment2 = PaymentFactory.createPayment(75469697L,"ORD-82947", "CUST-30458", 1500.00, PaymentMethod.EFT,  PaymentStatus.PENDING, "TXN-204938","1689479530585312", date);

    private static Payment invalidPayment = PaymentFactory.createPayment(000L,"", "CUST-30458",-1200.00,  null, null,"468894","1223-3749-385",date);


    @Test
    @Order(1)
    public void testCreatePayment() {
        assertNotNull(payment1);
        System.out.println(payment1.toString());
    }
    @Test
    @Order(2)
    public void testCreatePaymentWithAllAttributes() {
        assertNotNull(payment2);
        System.out.println(payment2.toString());
    }

    @Test
    @Disabled
    @Order(3)
    public void testCreateInvalidPayment() {
        assertNotNull(invalidPayment);
        System.out.println(invalidPayment.toString());
    }
}