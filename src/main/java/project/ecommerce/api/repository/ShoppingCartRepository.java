package project.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ecommerce.api.entity.ShoppingCart;
import project.ecommerce.api.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

  List<ShoppingCart> findAllByUser(User user);

  Optional<ShoppingCart> findFirstByUserAndId(User user, String id);
}
