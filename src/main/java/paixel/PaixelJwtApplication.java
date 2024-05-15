package paixel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import paixel.modelo.Curso;
import paixel.modelo.Docente;
import paixel.modelo.Modulo;
import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.repository.CursoRepository;
import paixel.repository.DocenteRepository;
import paixel.repository.ModuloRepository;
import paixel.repository.UserRepository;
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
	  
	            
	            // Nuevos usuarios
	            createUser(userRepository, passwordEncoder, "Ana", "Martínez Morales", "ana.martinez@example.com", "securepassword", "femenino", "12345678C", LocalDate.of(1993, 3, 3), "Valencia", Role.USER);
	            createUser(userRepository, passwordEncoder, "Carlos", "Jiménez Ruiz", "carlos.jimenez@example.com", "securepassword", "masculino", "23456789D", LocalDate.of(1994, 4, 4), "Sevilla", Role.USER);
	            createUser(userRepository, passwordEncoder, "Luisa", "Pérez García", "luisa.perez@example.com", "securepassword", "femenino", "34567890E", LocalDate.of(1988, 5, 5), "Zaragoza", Role.USER);
	            createUser(userRepository, passwordEncoder, "Miguel", "Hernández Fernández", "miguel.hernandez@example.com", "securepassword", "masculino", "45678901F", LocalDate.of(1987, 6, 6), "Málaga", Role.USER);
	            createUser(userRepository, passwordEncoder, "Sofía", "Moreno Martínez", "sofia.moreno@example.com", "securepassword", "femenino", "56789012G", LocalDate.of(1992, 7, 7), "Murcia", Role.USER);
	            createUser(userRepository, passwordEncoder, "Iker", "González López", "iker.gonzalez@example.com", "securepassword", "masculino", "67890123H", LocalDate.of(1989, 8, 8), "Palma de Mallorca", Role.USER);
	            createUser(userRepository, passwordEncoder, "Lucía", "Navarro Jiménez", "lucia.navarro@example.com", "securepassword", "femenino", "78901234I", LocalDate.of(1990, 9, 9), "Las Palmas", Role.USER);
	            createUser(userRepository, passwordEncoder, "Antonio", "Romero Martín", "antonio.romero@example.com", "securepassword", "masculino", "89012345J", LocalDate.of(1991, 10, 10), "Bilbao", Role.USER);
	            createUser(userRepository, passwordEncoder, "Carmen", "Vázquez Ruiz", "carmen.vazquez@example.com", "securepassword", "femenino", "90123456K", LocalDate.of(1995, 11, 11), "Alicante", Role.USER);
	            createUser(userRepository, passwordEncoder, "Francisco", "Castro Fernández", "francisco.castro@example.com", "securepassword", "masculino", "01234567L", LocalDate.of(1996, 12, 12), "Córdoba", Role.USER);
	            createUser(userRepository, passwordEncoder, "Nora", "Ortiz García", "nora.ortiz@example.com", "securepassword", "femenino", "12345678M", LocalDate.of(1994, 1, 13), "Valladolid", Role.USER);
	            createUser(userRepository, passwordEncoder, "David", "Gil Moreno", "david.gil@example.com", "securepassword", "masculino", "23456789N", LocalDate.of(1993, 2, 14), "Vitoria-Gasteiz", Role.USER);
	            createUser(userRepository, passwordEncoder, "Elena", "Sanz Martín", "elena.sanz@example.com", "securepassword", "femenino", "34567890O", LocalDate.of(1985, 3, 15), "Gijón", Role.USER);	            
	            createUser(userRepository, passwordEncoder, "Mária", "Navarro Jiménez", "maria.navarro@example.com", "securepassword", "femenino", "87654321P", LocalDate.of(1992, 4, 10), "Bilbao", Role.USER);
	            createUser(userRepository, passwordEncoder, "Juan", "Hernández Ruiz", "juan.hernandez@example.com", "securepassword", "masculino", "98765432Q", LocalDate.of(1988, 5, 22), "Sevilla", Role.USER);

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
//	    @Bean
//	    CommandLineRunner initWorkshops(UserRepository userRepository, WorkshopRepository workshopRepository) {
//	        return args -> {
//	            LocalDate workshopDate = LocalDate.now(); // Ajusta la fecha según sea necesario
//	            String imageUrl = "http://127.0.0.1:5500/images/workshop/The_chameleon.webp"; // Asegúrate de que esta URL sea accesible
//
//	            userRepository.findByUsername("Juli").ifPresent(user -> {
//	                createWorkshop(workshopRepository, user, imageUrl, "Un taller intensivo sobre Spring Boot", workshopDate);
//	            });
//	        };
//	    }
//
//	    private void createWorkshop(WorkshopRepository workshopRepository, User user, String imageUrl, String descripcion, LocalDate fecha) {
//	        if (workshopRepository.findByUsuarioAndFecha(user, fecha).isEmpty()) {
//	            Workshop newWorkshop = new Workshop(imageUrl, descripcion, fecha, user);
//	            workshopRepository.save(newWorkshop);
//	        }
//	    }

	    public class DataInitializer {

	        @Bean
	        public CommandLineRunner initData(DocenteRepository docenteRepository) {
	            return args -> {
	                // Crear docente 1: Miguel Ángel Rodríguez
	                Docente docente1 = new Docente();
	                docente1.setUsername("marodriguez");
	                docente1.setEspecialidad("Animación y 3D");
	                docente1.setDescripcion("Ingeniero en Diseño y Producción de Medios Audiovisuales. Formador en animación digital, modelado 3D y efectos visuales con más de 10 años de experiencia en la industria. Especialista en la creación de contenido animado para películas, videojuegos y aplicaciones multimedia.");
	                docente1.setRecurso("http://127.0.0.1:5500/images/docentes/docenteBarba.webp");


	                // Crear docente 2: Sofía Martínez
	                Docente docente2 = new Docente();
	                docente2.setUsername("smartinez");
	                docente2.setEspecialidad("Diseño Gráfico");
	                docente2.setDescripcion("Diseñadora Gráfica con un máster en Branding y Comunicación Visual. Más de 8 años de experiencia en la industria del diseño, trabajando con marcas internacionales para crear identidades visuales impactantes. Especialista en diseño gráfico, branding y creación de ilustraciones.");
	                docente2.setRecurso("http://127.0.0.1:5500/images/docentes/docenteMujer.webp");

	               
	                // Crear docente 3: Carlos Hernández
	                Docente docente3 = new Docente();
	                docente3.setUsername("chernandez");
	                docente3.setEspecialidad("UI/UX");
	                docente3.setDescripcion("Ingeniero de Software con especialización en Diseño de Interfaces y Experiencia de Usuario. Más de 7 años de experiencia ayudando a startups y grandes empresas a mejorar la usabilidad y estética de sus productos digitales. Experto en herramientas de diseño como Figma y Adobe XD, así como en técnicas de composición visual y efectos.");
	                docente3.setRecurso("http://127.0.0.1:5500/images/docentes/docente.webp");

	               
	                // Guardar los docentes (esto también guardará los cursos gracias a la cascada)
	                docenteRepository.save(docente1);
	                docenteRepository.save(docente2);
	                docenteRepository.save(docente3);
	            };
	        }
	    }
	   
	
		
}
