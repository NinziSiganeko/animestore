package za.ac.cput.domain;

import java.time.LocalDate;
/*
 *
 * Author S Ninzi(222522569)
 *
 * */

public class CreditCard extends Payment {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCard(){

    }
    public CreditCard(Builder builder){
        this.paymentId = builder.paymentId;
        this.paymentAmount = builder.paymentAmount;
        this.date = builder.date;
        this.paymentMethod = builder.paymentMethod;
        this.cardNumber = builder.cardNumber;
        this.expiryDate = builder.expiryDate;
        this.cvv = builder.cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", paymentId=" + paymentId +
                ", paymentAmount=" + paymentAmount +
                ", date=" + date +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

    public static class Builder{
        private int paymentId;
        private double paymentAmount;
        private LocalDate date;
        private String paymentMethod;
        private String cardNumber;
        private String expiryDate;
        private String cvv;

        public Builder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setPaymentAmount(double paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder setCvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder copy(CreditCard creditCard){
            this.paymentId = creditCard.paymentId;
            this.paymentAmount = creditCard.paymentAmount;
            this.date = creditCard.date;
            this.paymentMethod = creditCard.paymentMethod;
            this.cardNumber = creditCard.cardNumber;
            this.expiryDate = creditCard.expiryDate;
            this.cvv = creditCard.cvv;
            return this;
        }
        public CreditCard build(){
            return new CreditCard(this);
        }

    }

}
