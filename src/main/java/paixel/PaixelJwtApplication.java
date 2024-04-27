package paixel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.repository.UserRepository;

@SpringBootApplication
public class PaixelJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaixelJwtApplication.class, args);
	}

	
	 @Bean
	    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        return args -> {
	            if (userRepository.findByUsername("admin").isEmpty()) {
	                User admin = new User();
	                admin.setUsername("admin");
	                admin.setPassword(passwordEncoder.encode("admin123")); // Use una contraseña segura aquí
	                admin.setEmail("admin@example.com");
	                admin.setRole(Role.ADMIN);
	                userRepository.save(admin);
	            }
	        };
	    }
}
