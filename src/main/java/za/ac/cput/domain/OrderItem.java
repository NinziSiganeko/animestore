package za.ac.cput.domain;

public class OrderItem {
    private int quantity;
    private double price;
    ;

    private OrderItem(){

    }

    public OrderItem(Builder builder) {
        this.quantity = builder.quantity;
        this.price = builder.price;


    }
    public int getQuantity() {return quantity;}

    public double getPrice() {return price;}



    @Override
    public String toString() {
        return "OrderItem{" +
                "quantity=" + quantity +
                ", price=" + price +

                '}';
    }


    public static class Builder {
        private int quantity;

        private double price;

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }
        public Builder copy(OrderItem item) {
            this.quantity = item.getQuantity();
            this.price = item.getPrice();
            return this;


        }
        public OrderItem build() {return new OrderItem(this);}

    }


}