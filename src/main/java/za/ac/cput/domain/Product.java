package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    private Long productId;
    private String name;
    private double price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key
    private ProductCategory category;

    public Product() {}

    public Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.price = builder.price;
        this.stock = builder.stock;
        this.category = builder.category;
    }

    public Long getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public ProductCategory getCategory() { return category; }

    public static class Builder {
        private Long productId;
        private String name;
        private double price;
        private int stock;
        private ProductCategory category;

        public Builder setProductId(Long productId) {
            this.productId = productId; return this;
        }

        public Builder setName(String name) {
            this.name = name; return this;
        }

        public Builder setPrice(double price) {
            this.price = price; return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock; return this;
        }

        public Builder setCategory(ProductCategory category) {
            this.category = category; return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
