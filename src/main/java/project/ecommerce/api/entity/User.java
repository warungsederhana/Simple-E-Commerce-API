package project.ecommerce.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  private String id;

  private String name;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  @Column
  private Role role;

  @CreatedDate
  @Column(name = "created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Instant updatedAt;

  @OneToMany(mappedBy = "user")
  private List<Payment> payments;

  @OneToMany(mappedBy = "user")
  private List<ShoppingCart> shoppingCarts;


  public enum Role {
    admin,
    user
  }
}
