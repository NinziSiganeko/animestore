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
    public CustomerOrder read(Integer integer) {
        return repository.findById(integer).orElse(null);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        return repository.save(customerOrder);
    }

    @Override
    public boolean delete(Integer integer) {
        repository.deleteById(integer);
        return !repository.existsById(integer);
    }

    @Override
    public List<CustomerOrder> getAll() {
        return repository.findAll();
    }
}
