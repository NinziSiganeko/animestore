/* Product.java
   Product POJO class based on UML diagram
   Author: onthatile 221230793
   Date: 11 May 2025
*/

package za.ac.cput.domain;

public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stock;

    private Product() {}

    public Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Optional: method to update stock (from UML)
    public void updateStock() {
        // Placeholder for logic, e.g. reduce by 1 or based on OrderItem
        if (stock > 0) {
            stock--;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public static class Builder {
        private int productId;
        private String name;
        private String description;
        private double price;
        private int stock;

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
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

        public Builder copy(Product product) {
            this.productId = product.productId;
            this.name = product.name;
            this.description = product.description;
            this.price = product.price;
            this.stock = product.stock;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
