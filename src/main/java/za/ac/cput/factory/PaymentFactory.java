package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class PaymentFactory {
    public static Payment createPayment(String paymentId, String orderId, String customerId, double amount, PaymentMethod method, LocalDateTime paymentDate, PaymentStatus status,String transactionReference){
        if(Helper.isNullorEmpty(paymentId) ||
                Helper.isNullorEmpty(orderId) ||
                Helper.isNullorEmpty(customerId) ||
                Helper.isNullorEmpty(transactionReference))
            return null;

        return new Payment.Builder().setPaymentId(paymentId)
                .setOrderId(orderId).setCustomerId(customerId)
                .setTransactionReference(transactionReference).build();
    }
}
