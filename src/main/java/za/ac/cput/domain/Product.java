package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long productId;

    private String name;
    private double price;
    private int stock;

    @Lob
    @Column(length = 10485760) // 10 MB limit
    private byte[] productImage;  // store actual image in DB

    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key to ProductCategory
    private ProductCategory category;

    protected Product() {} // JPA requires a no-arg constructor

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.price = builder.price;
        this.stock = builder.stock;
        this.productImage = builder.productImage;
        this.category = builder.category;
    }

    // ================= Getters =================
    public Long getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public byte[] getProductImage() { return productImage; }
    public ProductCategory getCategory() { return category; }

    // ================= Builder =================
    public static class Builder {
        private Long productId;
        private String name;
        private double price;
        private int stock;
        private byte[] productImage;
        private ProductCategory category;

        public Builder setProductId(Long productId) { this.productId = productId; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setPrice(double price) { this.price = price; return this; }
        public Builder setStock(int stock) { this.stock = stock; return this; }
        public Builder setProductImage(byte[] productImage) { this.productImage = productImage; return this; }
        public Builder setCategory(ProductCategory category) { this.category = category; return this; }

        public Builder copy(Product product) {
            this.productId = product.productId;
            this.name = product.name;
            this.price = product.price;
            this.stock = product.stock;
            this.productImage = product.productImage;
            this.category = product.category;
            return this;
        }

        public Product build() { return new Product(this); }
    }

    // ================= Optional Setter =================
    // Only keep setter for image if needed
    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", productImage=" + (productImage != null ? "[image data]" : "null") +
                ", category=" + (category != null ? category.getCategoryName() : "null") +
                '}';
    }
}
