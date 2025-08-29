package za.ac.cput.factory;

import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductStatus;
import za.ac.cput.util.Helper;

public class InventoryFactory {

    public static Inventory createInventory(Product product, ProductStatus status) {

        // Validate product
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        // Validate status
        if (status == null) {
            throw new IllegalArgumentException("Product status cannot be null");
        }

        return new Inventory.Builder()
                .setProduct(product)
                .setStatus(status)
                .build();
    }
}
