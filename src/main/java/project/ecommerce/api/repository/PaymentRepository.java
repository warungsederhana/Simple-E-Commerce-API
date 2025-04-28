package project.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ecommerce.api.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
