package paixel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.modelo.Workshop;
import paixel.repository.UserRepository;
import paixel.repository.WorkshopRepository;
import paixel.servicesImpl.ServiceWorkshopImpl;

@SpringBootApplication
public class PaixelJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaixelJwtApplication.class, args);
	}
	
	@Autowired
	private ServiceWorkshopImpl serviceWorkshopImpl;

	
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
	    @Bean
	    CommandLineRunner initWorkshops(UserRepository userRepository, WorkshopRepository workshopRepository) {
	        return args -> {
	            LocalDateTime workshopDate = LocalDateTime.now(); // Ajusta la fecha según sea necesario
	            String imageUrl = "http://127.0.0.1:5500/images/workshop/The_chameleon.webp"; // Asegúrate de que esta URL sea accesible

	            userRepository.findByUsername("Juli").ifPresent(user -> {
	                createWorkshop(workshopRepository, user, imageUrl, "Un taller intensivo sobre Spring Boot. Ver más detalles en ", workshopDate);
	            });
	        };
	    }

	    private void createWorkshop(WorkshopRepository workshopRepository, User user, String imageUrl, String descripcion, LocalDateTime fecha) {
	        if (workshopRepository.findByUsuarioAndFecha(user, fecha).isEmpty()) {
	            Workshop newWorkshop = new Workshop(imageUrl, descripcion, fecha, user);
	            workshopRepository.save(newWorkshop);
	        }
	    }

		
		
}
