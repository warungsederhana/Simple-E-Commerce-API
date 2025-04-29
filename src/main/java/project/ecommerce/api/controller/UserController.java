package project.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;
import project.ecommerce.api.service.user.UserServiceI;

@RestController
public class UserController {

  @Autowired
  private UserServiceI userService;

  @PostMapping(
      path = "/api/user",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<UserResponse> register(@RequestBody RegisterUserRequest request) {
    UserResponse response = userService.register(request);
    return toWebResponse(response, "User registered successfully");
  }

  private WebResponse<UserResponse> toWebResponse(UserResponse data, String message) {
    return WebResponse.<UserResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message(message)
        .data(data)
        .build();
  }
}
