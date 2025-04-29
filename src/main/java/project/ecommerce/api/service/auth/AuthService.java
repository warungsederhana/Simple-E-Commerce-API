package project.ecommerce.api.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.ecommerce.api.entity.User;
import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.user.LoginUserRequest;
import project.ecommerce.api.model.user.LoginUserResponse;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;
import project.ecommerce.api.repository.UserRepository;
import project.ecommerce.api.service.ValidationService;
import project.ecommerce.api.util.JwtUtil;

import java.util.UUID;

@Service
@Slf4j
public class AuthService implements AuthServiceI {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  ValidationService validationService;

  @Override
  public UserResponse register(RegisterUserRequest request) {
    validationService.validate(request);

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already registered");
    }

    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setEmail(request.getEmail());
    user.setPassword(encoder.encode(request.getPassword()));
    //user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    user.setName(request.getName());
    user.setRole(User.Role.user);

    userRepository.save(user);

    return toUserResponse(user);
  }

  @Override
  public LoginUserResponse login(LoginUserRequest request) {
    validationService.validate(request);
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

    log.info(user.getEmail());
    log.info(user.getPassword());

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    if (userDetails == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
    String token = jwtUtil.generateToken(userDetails.getUsername());
    Long expiredAt = jwtUtil.getExpiredAt(token);

    log.info("token: {} : expiredAt: {}", token, expiredAt);
    return LoginUserResponse.builder()
        .token(token)
        .expiredAt(expiredAt)
        .build();
  }

  private UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .build();
  }
}
