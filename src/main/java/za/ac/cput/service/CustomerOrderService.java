package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.CustomerOrderFactory;
import za.ac.cput.repository.CustomerOrderRepository;
import za.ac.cput.repository.ProductRepository;
import java.util.List;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public CustomerOrder create(CustomerOrder order) {
        // Save the order first
        CustomerOrder savedOrder = orderRepository.save(order);

        // Update stock for each item in the order
        for (OrderItem item : savedOrder.getOrderItems()) {
            Product product = item.getProduct();
//            int newStock = product.getStock() - item.getQuantity();
//            product.setStock(newStock);
            productRepository.save(product);
        }

        return savedOrder;
    }

    @Override
    public CustomerOrder read(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public CustomerOrder update(CustomerOrder order) {
        if (orderRepository.existsById(order.getOrderId())) {
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public boolean delete(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    @Override
    public List<CustomerOrder> getOrdersByCustomerId(Long userId) {
        return orderRepository.findByCustomerUserId(userId);
    }

    @Override
    public CustomerOrder getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }
    @Override
    public List<CustomerOrder> getAll() {
        return orderRepository.findAll();
    }
    // Add this method to CustomerOrderService
    @Transactional
    public CustomerOrder createOrderFromDetails(Customer customer, List<OrderItem> orderItems,
                                                String paymentMethod, String shippingAddress) {
        // Use the factory to create order with generated number
        CustomerOrder order = CustomerOrderFactory.createOrder(customer, orderItems, paymentMethod, shippingAddress);

        // Then save and process stock
        CustomerOrder savedOrder = orderRepository.save(order);

        // Update stock
        for (OrderItem item : savedOrder.getOrderItems()) {
            Product product = item.getProduct();
//            int newStock = product.getStock() - item.getQuantity();
//            product.setStock(newStock);
            productRepository.save(product);
        }

        return savedOrder;
    }
}