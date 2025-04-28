package project.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ecommerce.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Boolean existsByEmail(String email);
}
