package project.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.product.CreateProductRequest;
import project.ecommerce.api.model.product.ProductResponse;
import project.ecommerce.api.service.product.ProductServiceI;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  ProductServiceI productService;

  @PostMapping(
      path = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<ProductResponse> create(@RequestBody CreateProductRequest request) {
    ProductResponse response = productService.create(request);

    return WebResponse.<ProductResponse>builder()
        .status(HttpStatus.CREATED.value())
        .error(false)
        .message("Product created successfully")
        .data(response)
        .build();
  }

  @GetMapping(
      path = "",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<List<ProductResponse>> getAll() {
    List<ProductResponse> responses = productService.getAll();

    return WebResponse.<List<ProductResponse>>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Products data retrieved successfully")
        .data(responses)
        .build();
  }

  @GetMapping(
      path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<ProductResponse> getById(@PathVariable("id") String id) {
    ProductResponse response = productService.getById(id);

    return WebResponse.<ProductResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Product data retrieved successfully")
        .data(response)
        .build();
  }

  @PutMapping(
      path = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<ProductResponse> update(@RequestBody CreateProductRequest request, @PathVariable("id") String id) {
    ProductResponse response = productService.update(request, id);

    return WebResponse.<ProductResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Product data retrieved successfully")
        .data(response)
        .build();
  }

  @DeleteMapping(
      path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<String> delete(@PathVariable("id") String id) {
    productService.delete(id);

    return WebResponse.<String>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Product deleted successfully")
        .data(null)
        .build();
  }

}
