package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private int productId;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    protected OrderItem() {}

    public OrderItem(Builder builder) {
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder=customerOrder;
    }

    // getters/setters omitted for brevity

    public static class Builder {
        private int productId;
        private int quantity;
        private double price;

        public Builder setProductId(int productId) { this.productId = productId; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }
        public Builder setPrice(double price) { this.price = price; return this; }

        public OrderItem build() { return new OrderItem(this); }
    }
}