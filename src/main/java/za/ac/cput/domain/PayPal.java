package za.ac.cput.domain;

import java.time.LocalDate;

public class PayPal extends CreditCard {
    private String email;

    public PayPal(){

    }
    public PayPal(Builder builder){
        this.paymentId = builder.paymentId;
        this.paymentAmount = builder.paymentAmount;
        this.date = builder.date;
        this.paymentMethod = builder.paymentMethod;
        this.email = builder.email;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PayPal{" +
                "email='" + email + '\'' +
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
        private String email;

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

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder copy(PayPal payPal){
            this.paymentId = payPal.paymentId;
            this.paymentAmount = payPal.paymentAmount;
            this.date = payPal.date;
            this.paymentMethod = payPal.paymentMethod;
            this.email = payPal.email;
            return this;
        }
        public PayPal build(){
            return new PayPal(this);
        }


    }
}
