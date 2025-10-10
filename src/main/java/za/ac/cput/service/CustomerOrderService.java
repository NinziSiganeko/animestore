package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.repository.CustomerOrderRepository;

import java.util.List;

@Service
public class CustomerOrderService implements ICustomerOrderService{

    @Autowired
    private CustomerOrderRepository repository;

    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        return repository.save(customerOrder);
    }

    @Override
    public CustomerOrder read(Long customerOrderId) {
        return repository.findById(customerOrderId).orElse(null);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        return repository.save(customerOrder);
    }

    @Override
    public boolean delete(Long customerOrderId) {
        repository.deleteById(customerOrderId);
        return true;
    }

    @Override
    public List<CustomerOrder> getAll() {
        return repository.findAll();
    }
    public CustomerOrder findByCustomerOrderId(Long customerOrderId) {
        return repository.findById(customerOrderId).orElse(null);
    }

}
