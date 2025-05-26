package za.ac.cput.factory;
/*Orderfactory.java
Author : N Mshweshwe
date: 17 may 2025
*/

import za.ac.cput.domain.Order;

import java.time.LocalDate;

public class OrderFactory {
    public static Order createOrder(int orderId, LocalDate orderDate, String status) {
        if (orderDate == null || status == null || status.isEmpty()) {
            return null;
        }

        return new Order.Builder()
                .setOrderId(orderId)
                .setOrderDate(orderDate)
                .setStatus(status)
                .build();
    }
}