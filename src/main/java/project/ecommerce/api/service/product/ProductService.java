package project.ecommerce.api.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import project.ecommerce.api.entity.Product;
import project.ecommerce.api.model.product.CreateProductRequest;
import project.ecommerce.api.model.product.ProductResponse;
import project.ecommerce.api.repository.ProductRepository;
import project.ecommerce.api.service.ValidationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements ProductServiceI{

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ValidationService validationService;


  @Override
  @Transactional
  public ProductResponse create(CreateProductRequest request) {
    validationService.validate(request);

    Product product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setName(request.getProductName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());

    productRepository.save(product);
    return toProductResponse(product);
  }

  @Override
  public List<ProductResponse> getAll() {
    return productRepository.findAll()
        .stream().map(this::toProductResponse).toList();
  }

  @Override
  public ProductResponse getById(String id) {
    Product response =  productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found."));

    return toProductResponse(response);
  }

  @Override
  @Transactional
  public ProductResponse update(CreateProductRequest request, String id) {
    validationService.validate(request);

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found."));

    product.setName(request.getProductName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());

    productRepository.save(product);

    return toProductResponse(product);
  }

  @Override
  @Transactional
  public void delete(String id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product data not found."));

    productRepository.delete(product);
  }


  private ProductResponse toProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .productName(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .build();
  }
}
