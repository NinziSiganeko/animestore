package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Service.PaymentService;
import za.ac.cput.domain.Payment;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Payment create(@RequestBody Payment payment) {
        return service.create(payment);
    }

    @GetMapping("/read/{paymentId}")
    public Payment read(@PathVariable String paymentId) {
        return this.service.read(paymentId);
    }

    @PutMapping("/update")
    public Payment update(@RequestBody Payment payment) {
        return this.service.update(payment);
    }

    @DeleteMapping("/delete/{paymentId}")
    public boolean delete(@PathVariable String paymentId) {
        return service.delete(paymentId);
    }

    @GetMapping("/getAll")
    public List<Payment> getAll() {
        return this.service.getAll();
    }
}
