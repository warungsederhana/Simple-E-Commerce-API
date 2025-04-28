package project.ecommerce.api.service.user;

import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;

public interface UserServiceI {
  public UserResponse register(RegisterUserRequest request);
}
