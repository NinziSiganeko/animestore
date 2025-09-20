package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private LocalDate orderDate;
    private String status;

    @OneToOne(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private Payment payment;


    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    protected CustomerOrder() {}

    public CustomerOrder(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
        this.items = builder.items != null ? builder.items : new ArrayList<>();
        this.items.forEach(item -> item.setCustomerOrder(this));
    }

    public int getOrderId() { return orderId; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public List<OrderItem> getItems() { return items; }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setCustomerOrder(this);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }

    public static class Builder {
        private int orderId;
        private LocalDate orderDate;
        private String status;
        private List<OrderItem> items;

        public Builder setOrderId(int orderId) { this.orderId = orderId; return this; }
        public Builder setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setItems(List<OrderItem> items) { this.items = items; return this; }

        public Builder copy(CustomerOrder customerOrder) {
            this.orderId = customerOrder.getOrderId();
            this.orderDate = customerOrder.getOrderDate();
            this.status = customerOrder.getStatus();
            this.items = customerOrder.getItems();
            return this;
        }

        public CustomerOrder build() {
            return new CustomerOrder(this);
        }
    }
}