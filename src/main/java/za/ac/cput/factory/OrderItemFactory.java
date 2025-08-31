package za.ac.cput.factory;
/*OrderItemfactory
Author : N Mshweshwe
date: 17 may 2025
*/
import za.ac.cput.domain.OrderItem;

public class OrderItemFactory {

    public static OrderItem createOrderItem(int quantity, double price) {
        if (quantity <= 0 || price < 0) {
            return null;
        }

        return new OrderItem.Builder()
                .setQuantity(quantity)
                .setPrice(price)
                .build();
    }
}
