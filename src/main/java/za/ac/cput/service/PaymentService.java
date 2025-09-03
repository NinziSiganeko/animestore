package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository repository;

    @Override
    public Payment create(Payment payment) {
        Payment newPayment = PaymentFactory.createPayment(
                null,
                null,
                null,
                payment.getAmount(),
                payment.getMethod(),
                PaymentStatus.COMPLETED,
                payment.getTransactionReference(),
                payment.getTransactionReference(),
                LocalDateTime.now()
        );

        if (newPayment != null) {
            return this.repository.save(newPayment);
        } else {
            return null;
        }
    }


    @Override
    public Payment read(Long paymentId) {
        return this.repository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment update(Payment payment) {
        return this.repository.save(payment);
    }

    @Override
    public boolean delete(Long paymentId) {
        this.repository.deleteById(paymentId);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return this.repository.findAll();
    }
}
