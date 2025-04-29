package project.ecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import project.ecommerce.api.model.WebResponse;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    WebResponse<Object> errorResponse = WebResponse.builder()
        .error(true)
        .status(HttpServletResponse.SC_UNAUTHORIZED)
        .message("Unauthorized: " + authException.getMessage())
        .data(null)
        .build();

    String json = new ObjectMapper().writeValueAsString(errorResponse);
    response.getWriter().write(json);
  }
}
