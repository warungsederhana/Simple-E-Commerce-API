package project.ecommerce.api.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;

public interface UserServiceI extends UserDetailsService {

  public UserResponse register(RegisterUserRequest request);
}
