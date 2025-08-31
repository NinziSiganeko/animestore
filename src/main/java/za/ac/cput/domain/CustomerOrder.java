package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class CustomerOrder {

    @Id
    private int orderId;
    private LocalDate orderDate;
    private String status;

    protected CustomerOrder() {

    }

    public CustomerOrder(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;

    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "CustomerOrder{" +
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


        public Builder copy(CustomerOrder customerOrder) {
            this.orderId = customerOrder.getOrderId();
            this.orderDate = customerOrder.getOrderDate();
            this.status = customerOrder.getStatus();
            return this;
        }


        public CustomerOrder build() {
            return new CustomerOrder(this);
        }

    }
}
