package project.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ecommerce.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
