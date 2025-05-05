package project.ecommerce.api.model.shoppingcart;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCartRequest {

  @NotNull
  @Min(0)
  private Integer quantity;

  @NotBlank
  private String productId;
}
