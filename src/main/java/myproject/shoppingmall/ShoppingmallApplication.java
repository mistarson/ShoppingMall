package myproject.shoppingmall;

import myproject.shoppingmall.security.AccountContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		AccountContext accountContext = (AccountContext) authentication.getPrincipal();

		return () -> Optional.of(accountContext.getMember().getName());
	}

}
