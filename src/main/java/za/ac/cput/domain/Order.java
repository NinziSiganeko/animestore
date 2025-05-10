package za.ac.cput.domain;

import java.util.Date;

public class Order {
    private int orderId ;
    private Date orderDate;
    private String status;

    public Order(Order builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;

    }
    public int getOrderId() {
        return orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public String getStatus() {
        return status;
    }

    public static class Builder {
        private int orderId;
        private Date orderDate;
        private String status;


        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }


    }
        public Order copy(Order order) {
            this.orderId = order.getOrderId();
            this.orderDate = order.getOrderDate();
            this.status = order.getStatus();
            return this;
        }



    public Order build() {
        return new Order(this);
    }








}

