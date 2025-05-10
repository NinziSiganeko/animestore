package za.ac.cput.domain;

import java.time.LocalDate;

public class Order {
    private int orderId ;
    private LocalDate orderDate;
    private String status;

    private Order(){

    }

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;

    }
    public int getOrderId() {return orderId;}

    public LocalDate getOrderDate() {return orderDate;}
    public String getStatus() {return status;}


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }


    public static class Builder {
        private int orderId;

        private LocalDate orderDate;

        private String status;

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }


    }
        public Order copy(Order order) {
            this.orderId = order.getOrderId ();
            this.orderDate = order.getOrderDate() ;
            this.status = order.getStatus();
            return this;
        }



      public Order build() {return new Order(this);}

}









