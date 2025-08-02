package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class PaymentFactory {
    public static Payment createPayment(Long paymentId, String orderId, String customerId, double amount, PaymentMethod method, PaymentStatus status,String transactionReference, String cardNumber, LocalDateTime paymentDate){
        if (Helper.isValidId(paymentId) || Helper.isNullOrEmpty(orderId)
                || Helper.isNullOrEmpty(customerId)
                || !Helper.isValidAmount(amount) || method == null || status == null
                || Helper.isNullOrEmpty(transactionReference))
            return null;
        if (method == PaymentMethod.CREDIT_CARD || method == PaymentMethod.DEBIT_CARD) {
            if (!Helper.isValidCard(cardNumber)) {
                System.out.println("Invalid card number.");
                return null;
            }
        }

        LocalDateTime date = Helper.getPaymentDate(paymentDate);

        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setOrderId(orderId)
                .setCustomerId(customerId)
                .setAmount(amount)
                .setMethod(method)
                .setStatus(status)
                .setTransactionReference(transactionReference)
                .setPaymentDate(date)
                .build();
    }
}
