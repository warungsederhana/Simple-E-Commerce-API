package project.ecommerce.api.model.product;


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
public class CreateProductRequest {

  @NotBlank
  private String productName;

  @NotBlank
  private String description;

  @NotNull
  private Long price;

  @NotNull
  private Long stock;
}
