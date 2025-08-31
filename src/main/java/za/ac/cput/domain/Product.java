package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    private String productId;
    private String name;
    private double price;
    private int stock;

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key
    private ProductCategory category;

    protected Product() {}

    public Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.price = builder.price;
        this.stock = builder.stock;
        this.category = builder.category;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public ProductCategory getCategory() { return category; }

    public static class Builder {
        private String productId;
        private String name;
        private double price;
        private int stock;
        private ProductCategory category;

        public Builder setProductId(String productId) {
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
