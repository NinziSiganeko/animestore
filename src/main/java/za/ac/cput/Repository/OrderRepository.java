package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Order;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

     List<Order> findByStatus(String status);
}

