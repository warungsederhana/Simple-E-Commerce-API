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
@Table(name = "payments")
public class Payment {

  @Id
  private String id;

  private Long amount;

  @Enumerated(EnumType.STRING)
  private Status status;

  @CreatedDate
  @Column(name = "created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Instant updatedAt;

  @ManyToOne
  @JoinColumn(name="user_id", referencedColumnName = "id")
  private User user;

  @OneToMany(mappedBy = "payment")
  private List<ShoppingCart> shoppingCarts;

  private enum Status{
    PENDING, COMPLETED, FAILED
  }
}
