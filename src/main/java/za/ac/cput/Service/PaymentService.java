package za.ac.cput.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Repository.PaymentRepository;
import za.ac.cput.domain.Payment;
import java.util.List;



@Service
public class PaymentService implements IPaymentService{

    @Autowired
    private PaymentRepository repository;

    @Override
    public Payment create(Payment payment) {
        return this.repository.save(payment);
    }

    @Override
    public Payment read(String paymentId) {
        return this.repository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment update(Payment payment) {
        return this.repository.save(payment);
    }

    @Override
    public boolean delete(String paymentId) {
        this.repository.deleteById(paymentId);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return this.repository.findAll();
    }

}
