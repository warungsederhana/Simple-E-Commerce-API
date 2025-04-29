package project.ecommerce.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.ecommerce.api.entity.User;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;
import project.ecommerce.api.repository.UserRepository;
import project.ecommerce.api.service.ValidationService;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserServiceI{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  @Override
  public UserResponse register(RegisterUserRequest request) {
    validationService.validate(request);

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already registered");
    }

    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setEmail(request.getEmail());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    user.setName(request.getName());
    user.setRole(User.Role.user);

    userRepository.save(user);

    return toUserResponse(user);
  }

  private UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .build();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found with email: " + email);
    }

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        Collections.emptyList()
    );
  }
}
