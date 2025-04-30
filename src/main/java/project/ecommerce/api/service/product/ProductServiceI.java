package project.ecommerce.api.service.product;

import project.ecommerce.api.model.product.CreateProductRequest;
import project.ecommerce.api.model.product.ProductResponse;

import java.util.List;

public interface ProductServiceI {

  public ProductResponse create(CreateProductRequest request);

  public List<ProductResponse> getAll();

  public ProductResponse getById(String id);

  public ProductResponse update(CreateProductRequest request, String id);

  public void delete(String id);
}
