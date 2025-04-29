package project.ecommerce.api.service.auth;

import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.user.LoginUserRequest;
import project.ecommerce.api.model.user.LoginUserResponse;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;

public interface AuthServiceI {

  public UserResponse register(RegisterUserRequest request);

  public LoginUserResponse login(LoginUserRequest request);
}
