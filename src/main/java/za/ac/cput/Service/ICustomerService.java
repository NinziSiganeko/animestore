package za.ac.cput.Service;


import za.ac.cput.domain.Customer;

import java.util.List;
import java.util.Optional;

    public interface ICustomerService extends IService<Customer, Long> {
        List<Customer> getAll();

        //Optional<Customer> findByEmail(String email);
       // List<Customer> findAllByFirstName(String firstName);
       // List<Customer> findAllByLastName(String lastName);
        //List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    }


