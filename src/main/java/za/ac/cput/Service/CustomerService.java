package za.ac.cput.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;

import java.util.List;
import za.ac.cput.repository.CustomerRepository;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CustomerService implements ICustomerService {
    @Autowired

    private  CustomerRepository repository;

    @Override
    public Customer create(Customer customer) {

        return this.repository.save(customer);
    }

    @Override
    public Customer read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {

        return this.repository.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<Customer> getAll() {
        return this.repository.findAll();
    }



}

