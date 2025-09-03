package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.service.CustomerService;
import za.ac.cput.domain.Customer;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:5173") // React app URL
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        try {
            return customerService.create(customer);
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/read/{customerId}")
    public Customer read(@PathVariable Long customerId) {
        return customerService.read(customerId);
    }

    @PostMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public void delete(@PathVariable Long customerId) {
        customerService.delete(customerId);
    }

    @GetMapping("/getAll")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping("/signin")
    public Map<String, String> signIn(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email").trim();
        String password = credentials.get("password").trim();

        // Find user by email (case-insensitive)
        Customer customer = customerService.findByEmail(email);

        if (customer != null && customer.getPassword().trim().equals(password)) {
            // Login successful → return token
            return Map.of(
                    "token", String.valueOf(customer.getCustomerId()),
                    "message", "Login successful"
            );
        }

        // Login failed → React will handle error
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }



}





