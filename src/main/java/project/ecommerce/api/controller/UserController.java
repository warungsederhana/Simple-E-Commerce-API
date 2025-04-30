package project.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;
import project.ecommerce.api.service.user.UserServiceI;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserServiceI userService;

  @GetMapping(
      path = "/current",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<UserResponse> getUser() {
    UserResponse response = userService.getUser();

    return WebResponse.<UserResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .data(response)
        .message( "User data retrieved successfully")
        .build();
  }

}
