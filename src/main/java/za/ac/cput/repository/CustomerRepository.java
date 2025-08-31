package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

import java.util.List;
@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {

        List<Customer> findAll();

        //  List<Customer> findAllByFirstName(String firstName);
        // List<Customer> findAllByLastName(String lastName);
        //  List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
        // Optional<Customer> findByEmail(String email);
    }
