package paixel;

import java.time.LocalDate;

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
	  @Bean
	    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        return args -> {
	            createUser(userRepository, passwordEncoder, "Juli", "Cueva", "juli@juli.com", "1234", "masculino", "12345678A", LocalDate.of(1990, 1, 1), "Madrid", Role.USER);
	            createUser(userRepository, passwordEncoder, "May", "Lopez", "may@may.com", "1234", "femenino", "87654321B", LocalDate.of(1992, 2, 2), "Barcelona", Role.USER);
	        };
	    }

	    private void createUser(UserRepository userRepository, PasswordEncoder passwordEncoder, String username, String apellidos, String email, String password, String genero, String dni, LocalDate fechaNacimiento, String localidad, Role role) {
	        if (userRepository.findByUsername(username).isEmpty()) {
	            User newUser = new User();
	            newUser.setUsername(username);
	            newUser.setApellidos(apellidos);
	            newUser.setPassword(passwordEncoder.encode(password));
	            newUser.setEmail(email);
	            newUser.setGenero(genero);
	            newUser.setDni(dni);
	            newUser.setFechaNacimiento(fechaNacimiento);
	            newUser.setLocalidad(localidad);
	            newUser.setRole(role);
	            userRepository.save(newUser);
	        }
	    }
}
