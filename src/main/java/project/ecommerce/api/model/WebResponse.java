package project.ecommerce.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse <T>{
  private Integer status;

  private boolean error;

  private String message;

  private T data;

  private PagingResponse paging;
}
