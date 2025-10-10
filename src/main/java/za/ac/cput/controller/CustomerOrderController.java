package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.service.CustomerOrderService;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService service;

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customer) {
        return service.create(customer);
    }

    @GetMapping("/read/{customerOrderId}")
    public CustomerOrder read(@PathVariable Long customerOrderId){
        return service.read(customerOrderId);
    }

    @PostMapping("/update")
    public CustomerOrder update(@RequestBody CustomerOrder customer) {
        return service.update(customer);
    }

    @DeleteMapping("/delete/{customerOrderId}")
    public void delete(@PathVariable Long customerOrderId){
        service.delete(customerOrderId);
    }

    @GetMapping("/getAll")
    public List<CustomerOrder> getAll(){
        return service.getAll();
    }
}