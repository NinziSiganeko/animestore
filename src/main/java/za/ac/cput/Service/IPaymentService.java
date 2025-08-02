package za.ac.cput.Service;

import za.ac.cput.domain.Payment;

import java.util.List;

public interface IPaymentService  extends IService<Payment, String>{

    List<Payment> getAll();

}
