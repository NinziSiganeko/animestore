package za.ac.cput.factory;
/*CustomerOrderfactory.java
Author : N Mshweshwe
date: 17 may 2025
*/

import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerOrderFactory {
    public static CustomerOrder createOrder( LocalDateTime orderDate, String status) {
        if (Helper.isNullOrEmpty(status)) {
            return null;
        }
        LocalDateTime date = Helper.getDate(orderDate);

        return new CustomerOrder.Builder()
                .setOrderDate(date)
                .setStatus(status)
                .build();
    }
}
