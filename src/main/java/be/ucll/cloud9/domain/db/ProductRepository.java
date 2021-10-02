package be.ucll.cloud9.domain.db;

import be.ucll.cloud9.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
