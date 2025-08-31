package za.ac.cput.service;

import za.ac.cput.domain.CustomerOrder;

import java.util.List;

public interface ICustomerOrderService extends IService<CustomerOrder, Integer> {
    List<CustomerOrder> getAll();
}
