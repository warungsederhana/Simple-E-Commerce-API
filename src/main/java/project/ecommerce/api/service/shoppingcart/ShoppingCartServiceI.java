package project.ecommerce.api.service.shoppingcart;

import project.ecommerce.api.entity.User;
import project.ecommerce.api.model.shoppingcart.CartResponse;
import project.ecommerce.api.model.shoppingcart.CreateCartRequest;
import project.ecommerce.api.model.shoppingcart.UpdateCartRequest;

import java.util.List;

public interface ShoppingCartServiceI {

  public CartResponse create(CreateCartRequest request, String userId);

  public List<CartResponse> getAll(String userId);

  public CartResponse getById(String id, String userId);

  public CartResponse update(UpdateCartRequest request, String id, String userId);

  public void delete(String id, String userId);
}
