package project.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.shoppingcart.CartResponse;
import project.ecommerce.api.model.shoppingcart.CreateCartRequest;
import project.ecommerce.api.model.shoppingcart.UpdateCartRequest;
import project.ecommerce.api.service.shoppingcart.ShoppingCartServiceI;
import project.ecommerce.api.util.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

  @Autowired
  ShoppingCartServiceI cartService;

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping(
      path = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<CartResponse> create(@RequestBody CreateCartRequest request, HttpServletRequest servletRequest) {
    String userId = jwtUtil.getIdFromRequest(servletRequest);
    CartResponse response = cartService.create(request, userId);

    return WebResponse.<CartResponse>builder()
        .status(HttpStatus.CREATED.value())
        .error(false)
        .message("Cart created successfully")
        .data(response)
        .build();
  }

  @GetMapping(
      path = "",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<List<CartResponse>> getAll(HttpServletRequest servletRequest) {
    String userId = jwtUtil.getIdFromRequest(servletRequest);
    List<CartResponse> carts = cartService.getAll(userId);

    return WebResponse.<List<CartResponse>>builder()
        .status(HttpStatus.CREATED.value())
        .error(false)
        .message("Carts fetched successfully")
        .data(carts)
        .build();
  }

  @GetMapping(
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<CartResponse> getById(@PathVariable("id") String id, HttpServletRequest servletRequest) {
    String userId = jwtUtil.getIdFromRequest(servletRequest);
    CartResponse cart = cartService.getById(id, userId);

    return WebResponse.<CartResponse>builder()
        .status(HttpStatus.CREATED.value())
        .error(false)
        .message("Cart fetched successfully")
        .data(cart)
        .build();
  }

  @PatchMapping(
      path = "{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<CartResponse> update(@PathVariable("id") String id, @RequestBody UpdateCartRequest request, HttpServletRequest servletRequest) {
    String userId = jwtUtil.getIdFromRequest(servletRequest);
    CartResponse cart = cartService.update(request, id, userId);

    return WebResponse.<CartResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Cart updated successfully")
        .data(cart)
        .build();
  }

  @DeleteMapping(
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<String> delete(@PathVariable("id") String id, HttpServletRequest servletRequest) {
    String userId = jwtUtil.getIdFromRequest(servletRequest);
    cartService.delete(id, userId);

    return WebResponse.<String>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Cart deleted successfully")
        .data(null)
        .build();
  }
}
