package paixel;

import java.time.LocalDate;
import java.util.Collections;

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
			createUser(userRepository, passwordEncoder, "Juli", "Cueva", "juli@juli.com", "1234", "masculino",
					"12345678A", LocalDate.of(1990, 1, 1), "Madrid", Role.USER);
			createUser(userRepository, passwordEncoder, "May", "Lopez", "may@may.com", "1234", "femenino", "87654321B",
					LocalDate.of(1992, 2, 2), "Barcelona", Role.USER);

			// Nuevos usuarios
			createUser(userRepository, passwordEncoder, "Ana", "Martínez Morales", "ana.martinez@example.com",
					"securepassword", "femenino", "12345678C", LocalDate.of(1993, 3, 3), "Valencia", Role.USER);
			createUser(userRepository, passwordEncoder, "Carlos", "Jiménez Ruiz", "carlos.jimenez@example.com",
					"securepassword", "masculino", "23456789D", LocalDate.of(1994, 4, 4), "Sevilla", Role.USER);
			createUser(userRepository, passwordEncoder, "Luisa", "Pérez García", "luisa.perez@example.com",
					"securepassword", "femenino", "34567890E", LocalDate.of(1988, 5, 5), "Zaragoza", Role.USER);
			createUser(userRepository, passwordEncoder, "Miguel", "Hernández Fernández", "miguel.hernandez@example.com",
					"securepassword", "masculino", "45678901F", LocalDate.of(1987, 6, 6), "Málaga", Role.USER);
			createUser(userRepository, passwordEncoder, "Sofía", "Moreno Martínez", "sofia.moreno@example.com",
					"securepassword", "femenino", "56789012G", LocalDate.of(1992, 7, 7), "Murcia", Role.USER);
			createUser(userRepository, passwordEncoder, "Iker", "González López", "iker.gonzalez@example.com",
					"securepassword", "masculino", "67890123H", LocalDate.of(1989, 8, 8), "Palma de Mallorca",
					Role.USER);
			createUser(userRepository, passwordEncoder, "Lucía", "Navarro Jiménez", "lucia.navarro@example.com",
					"securepassword", "femenino", "78901234I", LocalDate.of(1990, 9, 9), "Las Palmas", Role.USER);
			createUser(userRepository, passwordEncoder, "Antonio", "Romero Martín", "antonio.romero@example.com",
					"securepassword", "masculino", "89012345J", LocalDate.of(1991, 10, 10), "Bilbao", Role.USER);
			createUser(userRepository, passwordEncoder, "Carmen", "Vázquez Ruiz", "carmen.vazquez@example.com",
					"securepassword", "femenino", "90123456K", LocalDate.of(1995, 11, 11), "Alicante", Role.USER);
			createUser(userRepository, passwordEncoder, "Francisco", "Castro Fernández", "francisco.castro@example.com",
					"securepassword", "masculino", "01234567L", LocalDate.of(1996, 12, 12), "Córdoba", Role.USER);
			createUser(userRepository, passwordEncoder, "Nora", "Ortiz García", "nora.ortiz@example.com",
					"securepassword", "femenino", "12345678M", LocalDate.of(1994, 1, 13), "Valladolid", Role.USER);
			createUser(userRepository, passwordEncoder, "David", "Gil Moreno", "david.gil@example.com",
					"securepassword", "masculino", "23456789N", LocalDate.of(1993, 2, 14), "Vitoria-Gasteiz",
					Role.USER);
			createUser(userRepository, passwordEncoder, "Elena", "Sanz Martín", "elena.sanz@example.com",
					"securepassword", "femenino", "34567890O", LocalDate.of(1985, 3, 15), "Gijón", Role.USER);
			createUser(userRepository, passwordEncoder, "Mária", "Navarro Jiménez", "maria.navarro@example.com",
					"securepassword", "femenino", "87654321P", LocalDate.of(1992, 4, 10), "Bilbao", Role.USER);
			createUser(userRepository, passwordEncoder, "Juan", "Hernández Ruiz", "juan.hernandez@example.com",
					"securepassword", "masculino", "98765432Q", LocalDate.of(1988, 5, 22), "Sevilla", Role.USER);

		};
	}

	private void createUser(UserRepository userRepository, PasswordEncoder passwordEncoder, String username,
			String apellidos, String email, String password, String genero, String dni, LocalDate fechaNacimiento,
			String localidad, Role role) {
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
		        // Verificar si ya existen los docentes
		        if (docenteRepository.count() == 0) { // Solo crea los docentes si la base está vacía
		            // Crear docente 1: Miguel Ángel Rodríguez
		            Docente docente1 = new Docente();
		            docente1.setUsername("Mario Rodriguez Lino");
		            docente1.setEspecialidad("Animación y 3D");
		            docente1.setDescripcion(
		                    "Ingeniero en Diseño y Producción de Medios Audiovisuales. Formador en animación digital, modelado 3D y efectos visuales con más de 10 años de experiencia en la industria. Especialista en la creación de contenido animado para películas, videojuegos y aplicaciones multimedia.");
		            docente1.setRecurso("http://127.0.0.1:5500/images/docentes/docenteBarba.webp");

		            // Crear docente 2: Sofía Martínez
		            Docente docente2 = new Docente();
		            docente2.setUsername("Sara Martinez Lopez");
		            docente2.setEspecialidad("Diseño Gráfico");
		            docente2.setDescripcion(
		                    "Diseñadora Gráfica con un máster en Branding y Comunicación Visual. Más de 8 años de experiencia en la industria del diseño, trabajando con marcas internacionales para crear identidades visuales impactantes. Especialista en diseño gráfico, branding y creación de ilustraciones.");
		            docente2.setRecurso("http://127.0.0.1:5500/images/docentes/docenteMujer.webp");

		            // Crear docente 3: Carlos Hernández
		            Docente docente3 = new Docente();
		            docente3.setUsername("Chema Hernandez Pastor");
		            docente3.setEspecialidad("UI/UX");
		            docente3.setDescripcion(
		                    "Ingeniero de Software con especialización en Diseño de Interfaces y Experiencia de Usuario. Más de 7 años de experiencia ayudando a startups y grandes empresas a mejorar la usabilidad y estética de sus productos digitales. Experto en herramientas de diseño como Figma y Adobe XD, así como en técnicas de composición visual y efectos.");
		            docente3.setRecurso("http://127.0.0.1:5500/images/docentes/docente.webp");

		            // Guardar los docentes
		            docenteRepository.save(docente1);
		            docenteRepository.save(docente2);
		            docenteRepository.save(docente3);
		        }
		    };
	}

	@Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Bean
    CommandLineRunner initModuloData() {
        return args -> {
            Curso cursoDG = cursoRepository.findById(3).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoPS = cursoRepository.findById(4).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoDW = cursoRepository.findById(5).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoUIUX = cursoRepository.findById(6).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoLB = cursoRepository.findById(7).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoIL = cursoRepository.findById(8).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso curso3DA = cursoRepository.findById(9).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoFigma = cursoRepository.findById(10).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            Curso cursoFX = cursoRepository.findById(11).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

            // Diseño Gráfico
            saveModuloIfNotExist(new Modulo("Fundamentos del Diseño Gráfico", "1H 5M", "Fundamentos del Diseño Gráfico.", "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoDG));
            saveModuloIfNotExist(new Modulo("Teoría del Color", "1H", "Teoría del Color en el diseño.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoDG));
            saveModuloIfNotExist(new Modulo("Tipografía en el Diseño", "1H 5M", "Tipografía en el Diseño.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoDG));
            saveModuloIfNotExist(new Modulo("Herramientas de Diseño Digital", "1H", "Herramientas de Diseño Digital.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoDG));
            saveModuloIfNotExist(new Modulo("Técnicas de Composición", "1H 15M", "Técnicas de Composición.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoDG));
            saveModuloIfNotExist(new Modulo("Proyectos de Diseño Práctico", "1H 20M", "Proyectos de Diseño Práctico.","http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoDG));

            // Photoshop
            saveModuloIfNotExist(new Modulo("Introducción a Photoshop", "1H 5M", "Introducción a Photoshop.",  "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoPS));
            saveModuloIfNotExist(new Modulo("Herramientas Básicas de Edición", "1H 10M", "Herramientas Básicas de Edición en Photoshop.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoPS));
            saveModuloIfNotExist(new Modulo("Capas y Máscaras", "1H 15M", "Uso de Capas y Máscaras en Photoshop.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoPS));
            saveModuloIfNotExist(new Modulo("Retoque Fotográfico", "1H 20M", "Técnicas de Retoque Fotográfico en Photoshop.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoPS));
            saveModuloIfNotExist(new Modulo("Efectos Especiales y Filtros", "1H 25M", "Efectos Especiales y Filtros en Photoshop.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoPS));
            saveModuloIfNotExist(new Modulo("Proyectos Avanzados en Photoshop", "1H 30M", "Proyectos Avanzados en Photoshop.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoPS));

            // Desarrollo Web
            saveModuloIfNotExist(new Modulo("Fundamentos del Desarrollo Web", "1H 5M", "Fundamentos del Desarrollo Web.",  "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoDW));
            saveModuloIfNotExist(new Modulo("HTML y CSS Básico", "1H 10M", "HTML y CSS Básico.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoDW));
            saveModuloIfNotExist(new Modulo("JavaScript para Principiantes", "1H 15M", "Introducción a JavaScript para Principiantes.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoDW));
            saveModuloIfNotExist(new Modulo("Desarrollo Frontend con Frameworks", "1H 20M", "Desarrollo Frontend con Frameworks.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoDW));
            saveModuloIfNotExist(new Modulo("Introducción al Backend", "1H 25M", "Introducción al Backend.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoDW));
            saveModuloIfNotExist(new Modulo("Proyectos Completos de Desarrollo Web", "1H 30M", "Proyectos Completos de Desarrollo Web.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoDW));

            // UI/UX
            saveModuloIfNotExist(new Modulo("Principios de Diseño UI/UX", "1H 10M", "Principios de Diseño UI/UX.",  "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoUIUX));
            saveModuloIfNotExist(new Modulo("Investigación de Usuarios", "1H 15M", "Investigación de Usuarios en Diseño UI/UX.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoUIUX));
            saveModuloIfNotExist(new Modulo("Diseño de Interfaz de Usuario", "1H 20M", "Diseño de Interfaz de Usuario.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoUIUX));
            saveModuloIfNotExist(new Modulo("Prototipado y Wireframing", "1H 25M", "Prototipado y Wireframing en Diseño UI/UX.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoUIUX));
            saveModuloIfNotExist(new Modulo("Pruebas de Usabilidad", "1H 30M", "Pruebas de Usabilidad en Diseño UI/UX.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoUIUX));
            saveModuloIfNotExist(new Modulo("Implementación y Feedback", "1H 35M", "Implementación y Feedback en Diseño UI/UX.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoUIUX));

            // Logotipos y Branding
            saveModuloIfNotExist(new Modulo("Introducción al Branding", "1H 5M", "Fundamentos del Branding.",  "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoLB));
            saveModuloIfNotExist(new Modulo("Creación de Logotipos", "1H 10M", "Creación de Logotipos Efectivos.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoLB));
            saveModuloIfNotExist(new Modulo("Psicología del Color en Branding", "1H 15M", "Psicología del Color en Branding.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoLB));
            saveModuloIfNotExist(new Modulo("Estrategia de Marca", "1H 20M", "Estrategia de Marca para el Éxito.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoLB));
            saveModuloIfNotExist(new Modulo("Aplicaciones del Logotipo", "1H 25M", "Aplicaciones Prácticas del Logotipo.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoLB));
            saveModuloIfNotExist(new Modulo("Proyectos de Branding", "1H 30M", "Proyectos Prácticos de Branding.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoLB));

            // Ilustración
            saveModuloIfNotExist(new Modulo("Fundamentos de la Ilustración", "1H 10M", "Fundamentos Básicos de la Ilustración.",  "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoIL));
            saveModuloIfNotExist(new Modulo("Técnicas de Dibujo", "1H 15M", "Técnicas Esenciales de Dibujo.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoIL));
            saveModuloIfNotExist(new Modulo("Uso del Color en Ilustración", "1H 20M", "Aplicación del Color en Ilustración.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoIL));
            saveModuloIfNotExist(new Modulo("Ilustración Digital", "1H 25M", "Técnicas de Ilustración Digital.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoIL));
            saveModuloIfNotExist(new Modulo("Creación de Personajes", "1H 30M", "Métodos para Crear Personajes.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoIL));
            saveModuloIfNotExist(new Modulo("Proyectos de Ilustración", "1H 35M", "Proyectos Prácticos de Ilustración.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoIL));

            // 3D y Animación
            saveModuloIfNotExist(new Modulo("Introducción al 3D y Animación", "1H 5M", "Fundamentos del 3D y la Animación.",  "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), curso3DA));
            saveModuloIfNotExist(new Modulo("Modelado 3D", "1H 10M", "Técnicas de Modelado 3D.", "http://127.0.0.1:5500/images/video/video2", 2, Collections.emptyList(), curso3DA));
            saveModuloIfNotExist(new Modulo("Texturización y Materiales", "1H 15M", "Métodos de Texturización y Materiales.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), curso3DA));
            saveModuloIfNotExist(new Modulo("Rigging y Animación", "1H 20M", "Técnicas de Rigging y Animación.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), curso3DA));
            saveModuloIfNotExist(new Modulo("Iluminación y Renderizado", "1H 25M", "Métodos de Iluminación y Renderizado.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), curso3DA));
            saveModuloIfNotExist(new Modulo("Proyectos de Animación 3D", "1H 30M", "Proyectos Prácticos de Animación 3D.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), curso3DA));

            // Introducción a Figma
            saveModuloIfNotExist(new Modulo("Fundamentos de Figma", "1H", "Fundamentos del Uso de Figma.", "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoFigma));
            saveModuloIfNotExist(new Modulo("Creación de Diseños en Figma", "1H 5M", "Creación de Diseños Efectivos en Figma.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoFigma));
            saveModuloIfNotExist(new Modulo("Colaboración en Figma", "1H 10M", "Métodos de Colaboración en Figma.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoFigma));
            saveModuloIfNotExist(new Modulo("Prototipado Interactivo", "1H 15M", "Técnicas de Prototipado Interactivo en Figma.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoFigma));
            saveModuloIfNotExist(new Modulo("Plugins y Extensiones", "1H 20M", "Uso de Plugins y Extensiones en Figma.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoFigma));
            saveModuloIfNotExist(new Modulo("Proyectos Prácticos en Figma", "1H 25M", "Proyectos Prácticos en Figma.", "http://127.0.0.1:5500/video/video2.mp4", 6, Collections.emptyList(), cursoFigma));

            // Composición y FX
            saveModuloIfNotExist(new Modulo("Introducción a la Composición", "1H 5M", "Fundamentos de la Composición.", "http://127.0.0.1:5500/video/video1.mp4", 1, Collections.emptyList(), cursoFX));
            saveModuloIfNotExist(new Modulo("Herramientas de Composición", "1H 10M", "Uso de Herramientas de Composición.", "http://127.0.0.1:5500/video/video2.mp4", 2, Collections.emptyList(), cursoFX));
            saveModuloIfNotExist(new Modulo("Efectos Visuales Básicos", "1H 15M", "Aplicación de Efectos Visuales Básicos.", "http://127.0.0.1:5500/video/video2.mp4", 3, Collections.emptyList(), cursoFX));
            saveModuloIfNotExist(new Modulo("Técnicas de Rotoscoping", "1H 20M", "Métodos de Rotoscoping.", "http://127.0.0.1:5500/video/video2.mp4", 4, Collections.emptyList(), cursoFX));
            saveModuloIfNotExist(new Modulo("Integración de Efectos 3D", "1H 25M", "Integración de Efectos 3D en Composición.", "http://127.0.0.1:5500/video/video2.mp4", 5, Collections.emptyList(), cursoFX));
        };
    }

    private void saveModuloIfNotExist(Modulo modulo) {
        if (moduloRepository.findByTitulo(modulo.getTitulo()).isEmpty()) {
            moduloRepository.save(modulo);
        }
    }
	
	    
	}	   
	
	    
}
