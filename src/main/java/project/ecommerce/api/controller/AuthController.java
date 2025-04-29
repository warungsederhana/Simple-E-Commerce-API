package project.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ecommerce.api.model.WebResponse;
import project.ecommerce.api.model.user.LoginUserRequest;
import project.ecommerce.api.model.user.LoginUserResponse;
import project.ecommerce.api.model.user.RegisterUserRequest;
import project.ecommerce.api.model.user.UserResponse;
import project.ecommerce.api.repository.UserRepository;
import project.ecommerce.api.service.auth.AuthServiceI;
import project.ecommerce.api.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AuthServiceI authService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping(
      path = "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
    LoginUserResponse response = authService.login(request);
    return WebResponse.<LoginUserResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("User logged in successfully")
        .data(response)
        .build();
  }

  @PostMapping(
      path= "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<UserResponse> register(@RequestBody RegisterUserRequest request) {
    UserResponse response = authService.register(request);
    return WebResponse.<UserResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("User registered successfully")
        .data(response)
        .build();
  }
}
