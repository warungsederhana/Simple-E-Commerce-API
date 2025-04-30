package project.ecommerce.api.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

  private String id;

  private String productName;

  private String description;

  private Long price;

  private Long stock;
}
