//Inventory POJO class
//Author :S Mzaza(220030898)
//Date: 11 May 2025
package za.ac.cput.domain;

public class Inventory {
    private int InventoryID;
    private String location;

    public Inventory() {
    }

    public Inventory(Builder builder) {
        InventoryID = Builder.InventoryID;
        this.location = Builder.location;
    }

    public int getInventoryID() {
        return InventoryID;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "InventoryID=" + InventoryID +
                ", location='" + location + '\'' +
                '}';
    }
    public static class Builder {
        private static int InventoryID;
        private static String location;

        public Builder setInventoryID(int inventoryID) {
            InventoryID = inventoryID;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;

        }
        public Builder copy(Inventory inventory) {
            InventoryID = inventory.getInventoryID();
            location = inventory.getLocation();
            return this;
        }
        public Inventory build() {
            return new Inventory(this);
        }
    }
}
