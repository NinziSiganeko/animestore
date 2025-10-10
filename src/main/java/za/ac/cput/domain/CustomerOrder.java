package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerOrderId;
    private LocalDateTime orderDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Customer customer;

    @OneToOne(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private Payment payment;


    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    protected CustomerOrder() {}

    public CustomerOrder(Builder builder) {
        this.customerOrderId = builder.customerOrderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
        this.items = builder.items != null ? builder.items : new ArrayList<>();
        this.items.forEach(item -> item.setCustomerOrder(this));
    }

    public Long getCustomerOrderId() { return customerOrderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public List<OrderItem> getItems() { return items; }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setCustomerOrder(this);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "customerOrderId=" + customerOrderId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }

    public static class Builder {
        private Long customerOrderId;
        private LocalDateTime orderDate;
        private String status;
        private List<OrderItem> items;

        public Builder setCustomerOrderId(Long customerOrderId) { this.customerOrderId = customerOrderId; return this; }
        public Builder setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setItems(List<OrderItem> items) { this.items = items; return this; }

        public Builder copy(CustomerOrder customerOrder) {
            this.customerOrderId = customerOrder.getCustomerOrderId();
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