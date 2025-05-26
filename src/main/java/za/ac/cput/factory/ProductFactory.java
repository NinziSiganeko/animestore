package za.ac.cput.factory;

import za.ac.cput.domain.Product;

public class ProductFactory {
    public static Product createProduct(String productId, String name, double price, int stock) {
        return new Product.Builder()
                .setProductId(productId)
                .setName(name)
                .setPrice(price)
                .setStock(stock)
                .build();
    }
}
