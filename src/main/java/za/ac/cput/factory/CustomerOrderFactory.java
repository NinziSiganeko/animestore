package za.ac.cput.factory;
/*CustomerOrderfactory.java
Author : N Mshweshwe
date: 17 may 2025
*/

import za.ac.cput.domain.CustomerOrder;

import java.time.LocalDate;

public class CustomerOrderFactory {
    public static CustomerOrder createOrder(int orderId, LocalDate orderDate, String status) {
        if (orderDate == null || status == null || status.isEmpty()) {
            return null;
        }

        return new CustomerOrder.Builder()
                .setOrderId(orderId)
                .setOrderDate(orderDate)
                .setStatus(status)
                .build();
    }
}
