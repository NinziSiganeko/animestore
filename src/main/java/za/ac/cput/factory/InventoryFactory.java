package za.ac.cput.factory;
//InventoryFactory class
//Author: S Mzaza (220030898)
//Date: 18 May 2025

import za.ac.cput.domain.Inventory;
import za.ac.cput.util.Helper;
public class InventoryFactory {

        public static Inventory createInventory(int inventoryID, String location) {
            if (inventoryID <= 0 || Helper.isNullOrEmpty(location)) {
                return null;
            }

            return new Inventory.Builder()
                    .setInventoryID(inventoryID)
                    .setLocation(location)
                    .build();
        }
    }




