//Inventory POJO class
//Author :S Mzaza(220030898)
//Date: 11 May 2025
package za.ac.cput.domain;

public class Inventory {
    private int inventoryID;
    private String location;

    public Inventory() {
    }

    public Inventory(Builder builder) {
        inventoryID = builder.inventoryID;
        this.location = builder.location;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "InventoryID=" + inventoryID +
                ", location='" + location + '\'' +
                '}';
    }
    public static class Builder {
        private  int inventoryID;
        private   String location;

        public Builder setInventoryID(int inventoryID) {
            inventoryID = inventoryID;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;

        }
        public Builder copy(Inventory inventory) {
            inventoryID = inventory.getInventoryID();
            location = inventory.getLocation();
            return this;
        }
        public Inventory build() {
            return new Inventory(this);
        }
    }
}
