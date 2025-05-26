package za.ac.cput.domain;

//import org.killbill.billing.payment.api.PaymentMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
/*
*
* Author S Ninzi(222522569)
*
* */

public class Payment {

    private String paymentId;
    private String orderId;
    private String customerId;
    private double amount;
    private PaymentMethod method;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
    private String transactionReference;

    public Payment() {
    }

 public Payment(Builder builder){
        this.paymentId = builder.paymentId;
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.amount = builder.amount;
        this.method = builder.method;
        this.paymentDate = builder.paymentDate;
        this.status = builder.status;
        this.transactionReference = builder.transactionReference;


 }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", method=" + method +
                ", paymentDate=" + paymentDate +
                ", status=" + status +
                ", transactionReference='" + transactionReference + '\'' +
                '}';
    }

    public static class Builder {
        private String paymentId;
        private String orderId;
        private String customerId;
        private double amount;
        private PaymentMethod method;
        private LocalDateTime paymentDate;
        private PaymentStatus status;
        private String transactionReference;

        public Builder setPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setMethod(PaymentMethod method) {
            this.method = method;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setStatus(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder setTransactionReference(String transactionReference) {
            this.transactionReference = transactionReference;
            return this;
        }

        public Builder copy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.orderId = payment.orderId;
            this.customerId = payment.customerId;
            this.amount = payment.amount;
            this.method = payment.method;
            this.paymentDate = payment.paymentDate;
            this.status = payment.status;
            this.transactionReference = payment.transactionReference;
            return this;
        }
        public Payment build() {
            return new Payment(this);

        }
    }
}
