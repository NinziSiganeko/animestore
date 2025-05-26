package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.ProductCategory;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    Optional<ProductCategory> findByCategoryNameIgnoreCase(String categoryName);
}
