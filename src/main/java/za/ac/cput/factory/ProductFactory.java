package za.ac.cput.factory;

import za.ac.cput.domain.Product;

public class ProductFactory {
    public static Product createProduct(long productId, String name, double price, int stock) {
        return new Product.Builder()
                .setProductId(Long.valueOf(productId))
                .setName(name)
                .setPrice(price)
                .setStock(stock)
                .build();
    }
}
