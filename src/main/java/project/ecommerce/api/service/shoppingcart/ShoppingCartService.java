package project.ecommerce.api.service.shoppingcart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import project.ecommerce.api.entity.Product;
import project.ecommerce.api.entity.ShoppingCart;
import project.ecommerce.api.entity.User;
import project.ecommerce.api.model.shoppingcart.CartResponse;
import project.ecommerce.api.model.shoppingcart.CreateCartRequest;
import project.ecommerce.api.model.shoppingcart.UpdateCartRequest;
import project.ecommerce.api.repository.ProductRepository;
import project.ecommerce.api.repository.ShoppingCartRepository;
import project.ecommerce.api.repository.UserRepository;
import project.ecommerce.api.service.ValidationService;
import project.ecommerce.api.util.JwtUtil;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartService implements ShoppingCartServiceI {

  @Autowired
  private ShoppingCartRepository cartRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ValidationService validationService;

  @Override
  @Transactional
  public CartResponse create(CreateCartRequest request, String userId) {
    validationService.validate(request);

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

    Product product = productRepository.findById(request.getProductId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setId(UUID.randomUUID().toString());
    shoppingCart.setQuantity(request.getQuantity());
    shoppingCart.setUser(user);
    shoppingCart.setProduct(product);

    cartRepository.save(shoppingCart);

    return toCartResponse(shoppingCart);
  }

  @Override
  public List<CartResponse> getAll(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

    return cartRepository.findAllByUser(user)
        .stream().map(this::toCartResponse).toList();
  }

  @Override
  public CartResponse getById(String id, String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

    ShoppingCart cart = cartRepository.findFirstByUserAndId(user, id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found."));

    return toCartResponse(cart);
  }

  @Override
  @Transactional
  public CartResponse update(UpdateCartRequest request, String id, String userId) {
    if (request.getQuantity() < 1) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity cannot zero or negative");
    }

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

    ShoppingCart cart = cartRepository.findFirstByUserAndId(user, id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found."));
    cart.setQuantity(request.getQuantity());
    cartRepository.save(cart);

    return toCartResponse(cart);
  }

  @Override
  @Transactional
  public void delete(String id, String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

    ShoppingCart cart = cartRepository.findFirstByUserAndId(user, id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping cart not found."));

    cartRepository.delete(cart);
  }

  private CartResponse toCartResponse(ShoppingCart cart) {
    return CartResponse.builder()
        .id(cart.getId())
        .quantity(cart.getQuantity())
        .productId(cart.getProduct().getId())
        .build();
  }
}
