package project.ecommerce.api;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class ProjectECommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectECommerceApiApplication.class, args);

	}

}
