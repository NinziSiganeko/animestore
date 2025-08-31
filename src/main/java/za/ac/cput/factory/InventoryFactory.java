package za.ac.cput.factory;
//InventoryFactory class
//Author: S Mzaza (220030898)
//Date: 18 May 2025

import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductStatus;
import za.ac.cput.util.Helper;
public class InventoryFactory {


    public static Inventory createInventory(Product product, ProductStatus status) {
        if (product == null || status == null) {
            return null;
        }

        return new Inventory.Builder()
                .setProduct(product)
                .setStatus(status)
                .build();
    }
}





