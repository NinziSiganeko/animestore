package za.ac.cput.domain;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int stock;

    // Private constructor using Builder
    private Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.price = builder.price;
        this.stock = builder.stock;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // Static Builder class
    public static class Builder {
        private String productId;
        private String name;
        private double price;
        private int stock;

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
