package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.service.CustomerOrderService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService service;

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder) {
        return service.create(customerOrder);
    }

    @GetMapping("/read/{orderId}")
    public CustomerOrder read(@PathVariable int orderId) {
        return service.read(orderId);
    }

    @PostMapping("/update")
    public CustomerOrder update(@RequestBody CustomerOrder customerOrder) {
        return service.update(customerOrder);
    }

    @DeleteMapping("/delete/{orderId}")
    public void delete(@PathVariable int orderId) {
        service.delete(orderId);
    }

    @GetMapping("/getAll")
    public List<CustomerOrder> getAll() {
        return service.getAll();
    }
}