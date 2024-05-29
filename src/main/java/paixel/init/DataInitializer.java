package paixel.init;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import paixel.modelo.Curso;
import paixel.modelo.Docente;
import paixel.modelo.Matricula;
import paixel.modelo.Modulo;
import paixel.modelo.Pregunta;
import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.modelo.Workshop;
import paixel.repository.CursoRepository;
import paixel.repository.DocenteRepository;
import paixel.repository.MatriculaRepository;
import paixel.repository.ModuloRepository;
import paixel.repository.PreguntaRepository;
import paixel.repository.UserRepository;
import paixel.repository.WorkshopRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final CursoRepository cursoRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final WorkshopRepository workshopRepository;
	private final DocenteRepository docenteRepository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	private final PreguntaRepository preguntaRepository;

	public DataInitializer(CursoRepository cursoRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder, WorkshopRepository workshopRepository, DocenteRepository docenteRepository,
			MatriculaRepository matriculaRepository, ModuloRepository moduloRepository,
			PreguntaRepository preguntaRepository) {
		this.cursoRepository = cursoRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.workshopRepository = workshopRepository;
		this.docenteRepository = docenteRepository;
		this.matriculaRepository = matriculaRepository;
		this.moduloRepository = moduloRepository;
		this.preguntaRepository = preguntaRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		initAdmin();
		initUsers();
		initWorkshops();
		initMatriculas();
		initDocentes();
		initCursos();
		initModuloData();
		initPreguntasData();

	}

	private void initAdmin() {

		if (userRepository.findByUsername("admin").isEmpty()) {
			User admin = User.builder().username("admin").password(passwordEncoder.encode("admin123"))
					.email("admin@example.com").role(Role.ADMIN).matricula(true).build();
			userRepository.save(admin);
		}
	}

	private void initUsers() {
		createUser("Roberto", "Silva", "roberto.silva@example.com", "securepassword", "masculino", "A1234567B",
				LocalDate.of(1985, 8, 15), "Madrid", Role.USER, true);
		createUser("Patricia", "Luna", "patricia.luna@example.com", "securepassword", "femenino", "B2345678C",
				LocalDate.of(1990, 2, 20), "Barcelona", Role.USER, true);
		createUser("Oscar", "Muñoz", "oscar.munoz@example.com", "securepassword", "masculino", "C3456789D",
				LocalDate.of(1992, 11, 11), "Valencia", Role.USER, true);
		createUser("Sara", "Gómez", "sara.gomez@example.com", "securepassword", "femenino", "D4567890E",
				LocalDate.of(1989, 5, 5), "Sevilla", Role.USER, true);
		createUser("Ricardo", "Nieto", "ricardo.nieto@example.com", "securepassword", "masculino", "E5678901F",
				LocalDate.of(1988, 12, 22), "Zaragoza", Role.USER, true);
		createUser("Laura", "Castillo", "laura.castillo@example.com", "securepassword", "femenino", "F6789012G",
				LocalDate.of(1991, 4, 17), "Málaga", Role.USER, true);
		createUser("Pablo", "Vega", "pablo.vega@example.com", "securepassword", "masculino", "G7890123H",
				LocalDate.of(1986, 9, 9), "Murcia", Role.USER, true);
		createUser("Natalia", "Morales", "natalia.morales@example.com", "securepassword", "femenino", "H8901234I",
				LocalDate.of(1993, 6, 6), "Palma de Mallorca", Role.USER, true);
		createUser("Fernando", "Ortiz", "fernando.ortiz@example.com", "securepassword", "masculino", "I9012345J",
				LocalDate.of(1995, 1, 1), "Las Palmas", Role.USER, true);
		createUser("Isabel", "Medina", "isabel.medina@example.com", "securepassword", "femenino", "J0123456K",
				LocalDate.of(1990, 10, 10), "Bilbao", Role.USER, true);
		createUser("Diego", "Moreno", "diego.moreno@example.com", "securepassword", "masculino", "K1234567L",
				LocalDate.of(1987, 3, 3), "Alicante", Role.USER, true);
		createUser("Clara", "Ramírez", "clara.ramirez@example.com", "securepassword", "femenino", "L2345678M",
				LocalDate.of(1996, 7, 7), "Córdoba", Role.USER, true);
		createUser("Ana", "Martínez Morales", "ana.martinez@example.com", "securepassword", "femenino", "12345678C",
				LocalDate.of(1993, 3, 3), "Valencia", Role.USER, true);
		createUser("Carlos", "Jiménez Ruiz", "carlos.jimenez@example.com", "securepassword", "masculino", "23456789D",
				LocalDate.of(1994, 4, 4), "Sevilla", Role.USER, true);
		createUser("Juan", "Hernández Ruiz", "juan.hernandez@example.com", "securepassword", "masculino", "98765432Q",
				LocalDate.of(1988, 5, 22), "Sevilla", Role.USER, true);

		// Los últimos dos usuarios con matrícula false
		createUser("Juli", "Cueva", "juli@juli.com", "1234", "masculino", "12345678A", LocalDate.of(1990, 1, 1),
				"Madrid", Role.USER, false);
		createUser("May", "Lopez", "may@may.com", "1234", "femenino", "87654321B", LocalDate.of(1992, 2, 2),
				"Barcelona", Role.USER, false);
	}

	private void createUser(String username, String apellidos, String email, String password, String genero, String dni,
			LocalDate fechaNacimiento, String localidad, Role role, boolean matricula) {
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
			newUser.setMatricula(matricula);
			userRepository.save(newUser);
		}
	}

	private void initWorkshops() {
		LocalDate workshopDate = LocalDate.now();

		createWorkshop(1, workshopDate, "http://127.0.0.1:5500/images/workshop/Gorila.webp",
				"Taller de Fotografía de Animales");
		createWorkshop(2, workshopDate, "http://127.0.0.1:5500/images/workshop/Libros1.webp",
				"Taller de Literatura Moderna");
		createWorkshop(3, workshopDate, "http://127.0.0.1:5500/images/workshop/MujerAzul.webp",
				"Taller de Pintura y Color");
		createWorkshop(4, workshopDate, "http://127.0.0.1:5500/images/workshop/OgroVerde.webp",
				"Taller de Escultura en Arcilla");
		createWorkshop(5, workshopDate, "http://127.0.0.1:5500/images/workshop/OjoGafa.webp",
				"Curso de Diseño de Accesorios");
		createWorkshop(6, workshopDate, "http://127.0.0.1:5500/images/workshop/Perro.webp",
				"Seminario sobre Cuidado Animal");
		createWorkshop(7, workshopDate, "http://127.0.0.1:5500/images/workshop/Rdd2.webp",
				"Taller de Robótica Avanzada");
		createWorkshop(8, workshopDate, "http://127.0.0.1:5500/images/workshop/The_chameleon.webp",
				"Taller de Camuflaje en la Naturaleza");
		createWorkshop(9, workshopDate, "http://127.0.0.1:5500/images/workshop/CaraViolet.webp",
				"Curso sobre Maquillaje Artístico");
		createWorkshop(10, workshopDate, "http://127.0.0.1:5500/images/workshop/CasasRopa.webp",
				"Seminario de Moda y Arquitectura");
		createWorkshop(11, workshopDate, "http://127.0.0.1:5500/images/workshop/Cementerio.webp",
				"Curso sobre Historia y Cultura Gótica");
		createWorkshop(12, workshopDate, "http://127.0.0.1:5500/images/workshop/colageHombre.webp",
				"Taller de Collage Creativo");
		createWorkshop(13, workshopDate, "http://127.0.0.1:5500/images/workshop/ColageMujer.webp",
				"Seminario de Arte Femenino Contemporáneo");
		createWorkshop(14, workshopDate, "http://127.0.0.1:5500/images/workshop/Comida.webp",
				"Curso de Cocina Internacional");
		createWorkshop(15, workshopDate, "http://127.0.0.1:5500/images/workshop/Halloween.webp",
				"Taller de Decoración de Halloween");
	}

	private void createWorkshop(int userId, LocalDate fecha, String contenido, String descripcion) {
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (workshopRepository.findByUsuarioAndFecha(user, fecha).isEmpty()) {
				Workshop newWorkshop = new Workshop(contenido, descripcion, fecha, user);
				workshopRepository.save(newWorkshop);
			}
		}
	}

	private void initDocentes() {
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
	}

	private void initMatriculas() {
		for (int userId = 1; userId <= 16; userId++) {
			Optional<User> userOpt = userRepository.findById(userId);
			if (userOpt.isPresent()) {
				User user = userOpt.get();
				if (matriculaRepository.findByUser_Iduser(userId).isEmpty()) {
					Matricula newMatricula = new Matricula();
					newMatricula.setUser(user);
					newMatricula.setFechaPago(LocalDate.now());
					newMatricula.setPagado(true);
					matriculaRepository.save(newMatricula);
				}
			}
		}
	}

	private void initCursos() {
		Docente docente1 = getDocenteById(1);
		Docente docente2 = getDocenteById(2);
		Docente docente3 = getDocenteById(3);// Obtén el docente por su ID
		User usuario = getUserById(1); // Obtén el usuario por su ID

		createCurso("Diseño Gráfico", "En este curso avanzado, dominarás técnicas avanzadas de Adobe Photoshop, Illustrator e InDesign para crear gráficos vectoriales, editar imágenes y diseñar publicaciones profesionales. Aprenderás principios avanzados de teoría del color, tipografía y composición, y aplicarás estas habilidades en proyectos prácticos de identidad visual y campañas publicitarias.", docente1,
				"http://127.0.0.1:5500/images/cursos/Diseño_grafico.webp", usuario);
		createCurso("Photoshop",
				"Domina Adobe Photoshop, desde conceptos básicos hasta técnicas avanzadas. Este curso te equipa con las habilidades necesarias para editar, manipular y transformar fotografías a un nivel profesional. Aprende a crear composiciones impresionantes, utilizar herramientas de retoque y explorar la amplia gama de funciones de Photoshop para proyectos creativos y comerciales.",
				docente2, "http://127.0.0.1:5500/images/cursos/Photoshop.webp", usuario);
		createCurso("Desarrollo Web",
				"Explora el mundo del desarrollo web con este curso intensivo en HTML, CSS, y JavaScript. Aprenderás a construir sitios web responsivos y optimizados desde cero, mientras desarrollas habilidades prácticas para enfrentarte a desafíos reales y crear interfaces atractivas y funcionales.",
				docente3, "http://127.0.0.1:5500/images/cursos/Diseño_Web.webp", usuario);
		createCurso("UI/UX",
				"Adéntrate en el diseño UI/UX para crear experiencias de usuario cautivadoras. Este curso te enseñará a analizar necesidades del usuario, diseñar interfaces intuitivas y realizar pruebas de usabilidad. Prepárate para convertirte en un diseñador UI/UX que crea soluciones que no solo se ven bien, sino que funcionan de manera óptima.",
				docente1, "http://127.0.0.1:5500/images/cursos/Diseño_UX_UI.webp", usuario);
		createCurso("Logotipos y Branding",
				"Transforma ideas creativas en marcas memorables con nuestro curso de Logotipos y Branding. Aprenderás técnicas de diseño gráfico y estrategias de branding que te permitirán crear identidades visuales que resuenen con el público. Este curso es esencial para diseñadores que aspiren a influir en la percepción de marca y la lealtad del cliente.",
				docente2, "http://127.0.0.1:5500/images/cursos/Logo_Branding.webp", usuario);
		createCurso("Ilustración",
				"Aprende a dar vida a tus visiones artísticas utilizando herramientas digitales líderes. Este curso cubre desde los fundamentos del dibujo digital hasta técnicas avanzadas de color y luz, perfecto para artistas que buscan perfeccionar su estilo y técnica.",
				docente3, "http://127.0.0.1:5500/images/cursos/Ilustracion.webp", usuario);
		createCurso("3D y Animación",
				"Sumérgete en el mundo del 3D y la animación con este curso práctico. Aprenderás desde modelado básico hasta técnicas avanzadas de animación, preparándote para dar vida a tus propias creaciones. Ideal para entusiastas del cine y videojuegos que desean transformar su pasión en una carrera profesional.",
				docente1, "http://127.0.0.1:5500/images/cursos/Animacion_3D.webp", usuario);
		createCurso("Introducción a Figma",
				"Domina Figma para diseñar interfaces de usuario impactantes. Este curso te guiará a través de herramientas y técnicas esenciales, preparándote para crear prototipos funcionales y colaborativos. Perfecto para diseñadores que buscan eficiencia y colaboración en proyectos de UI/UX.",
				docente2, "http://127.0.0.1:5500/images/cursos/Figma.webp", usuario);
		createCurso("Composición y FX",
				"Explora técnicas avanzadas de composición y efectos visuales en este curso integral, ideal para artistas y cineastas que buscan dominar la composición digital y mejorar sus proyectos con animaciones impactantes.",
				docente3, "http://127.0.0.1:5500/images/cursos/Composicion_FX.webp", usuario);
	}

	private void createCurso(String titulo, String descripcion, Docente docente, String recurso, User usuario) {
		Optional<Curso> existingCurso = cursoRepository.findByTitulo(titulo);
		if (existingCurso.isEmpty()) {
			Curso curso = new Curso();
			curso.setTitulo(titulo);
			curso.setDescripcion(descripcion);
			curso.setDocente(docente);
			curso.setRecurso(recurso);
			curso.setUser(usuario);
			;
			cursoRepository.save(curso);
		} else {
			System.out.println("El curso con título '" + titulo + "' ya existe.");
		}
	}

	private Docente getDocenteById(int id) {
		return docenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
	}

	private User getUserById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	public void initModuloData() {
		Curso cursoDG = cursoRepository.findById(1).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoPS = cursoRepository.findById(2).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoDW = cursoRepository.findById(3).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoUIUX = cursoRepository.findById(4).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoLB = cursoRepository.findById(5).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoIL = cursoRepository.findById(6).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso curso3DA = cursoRepository.findById(7).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoFigma = cursoRepository.findById(8).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		Curso cursoFX = cursoRepository.findById(9).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

		// Diseño Gráfico
		saveModuloIfNotExist(new Modulo("Fundamentos del Diseño Gráfico", "1H 5M", "Fundamentos del Diseño Gráfico.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoDG));
		saveModuloIfNotExist(new Modulo("Teoría del Color", "1H", "Teoría del Color en el diseño.",
				"http://127.0.0.1:5500/video/video2.mp4", 2, cursoDG));
		saveModuloIfNotExist(new Modulo("Tipografía en el Diseño", "1H 5M", "Tipografía en el Diseño.",
				"http://127.0.0.1:5500/video/video3.mp4", 3, cursoDG));
		saveModuloIfNotExist(new Modulo("Herramientas de Diseño Digital", "1H", "Herramientas de Diseño Digital.",
				"http://127.0.0.1:5500/video/video4.mp4", 4, cursoDG));
		saveModuloIfNotExist(new Modulo("Técnicas de Composición", "1H 15M", "Técnicas de Composición.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, cursoDG));
		saveModuloIfNotExist(new Modulo("Proyectos de Diseño Práctico", "1H 20M", "Proyectos de Diseño Práctico.",
				"http://127.0.0.1:5500/video/video6.mp4", 6, cursoDG));

		// Photoshop
		saveModuloIfNotExist(new Modulo("Introducción a Photoshop", "1H 5M", "Introducción a Photoshop.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoPS));
		saveModuloIfNotExist(new Modulo("Herramientas Básicas de Edición", "1H 10M",
				"Herramientas Básicas de Edición en Photoshop.", "http://127.0.0.1:5500/video/video2.mp4", 2, cursoPS));
		saveModuloIfNotExist(new Modulo("Capas y Máscaras", "1H 15M", "Uso de Capas y Máscaras en Photoshop.",
				"http://127.0.0.1:5500/video/video3.mp4", 3, cursoPS));
		saveModuloIfNotExist(new Modulo("Retoque Fotográfico", "1H 20M",
				"Técnicas de Retoque Fotográfico en Photoshop.", "http://127.0.0.1:5500/video/video4.mp4", 4, cursoPS));
		saveModuloIfNotExist(new Modulo("Efectos Especiales y Filtros", "1H 25M",
				"Efectos Especiales y Filtros en Photoshop.", "http://127.0.0.1:5500/video/video5.mp4", 5, cursoPS));
		saveModuloIfNotExist(new Modulo("Proyectos Avanzados en Photoshop", "1H 30M",
				"Proyectos Avanzados en Photoshop.", "http://127.0.0.1:5500/video/video6.mp4", 6, cursoPS));

		// Desarrollo Web
		saveModuloIfNotExist(new Modulo("Fundamentos del Desarrollo Web", "1H 5M", "Fundamentos del Desarrollo Web.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoDW));
		saveModuloIfNotExist(new Modulo("HTML y CSS Básico", "1H 10M", "HTML y CSS Básico.",
				"http://127.0.0.1:5500/video/video2.mp4", 2, cursoDW));
		saveModuloIfNotExist(new Modulo("JavaScript para Principiantes", "1H 15M",
				"Introducción a JavaScript para Principiantes.", "http://127.0.0.1:5500/video/video3.mp4", 3, cursoDW));
		saveModuloIfNotExist(new Modulo("Desarrollo Frontend con Frameworks", "1H 20M",
				"Desarrollo Frontend con Frameworks.", "http://127.0.0.1:5500/video/video4.mp4", 4, cursoDW));
		saveModuloIfNotExist(new Modulo("Introducción al Backend", "1H 25M", "Introducción al Backend.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, cursoDW));
		saveModuloIfNotExist(new Modulo("Proyectos Completos de Desarrollo Web", "1H 30M",
				"Proyectos Completos de Desarrollo Web.", "http://127.0.0.1:5500/video/video6.mp4", 6, cursoDW));

		// UI/UX
		saveModuloIfNotExist(new Modulo("Principios de Diseño UI/UX", "1H 10M", "Principios de Diseño UI/UX.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoUIUX));
		saveModuloIfNotExist(new Modulo("Investigación de Usuarios", "1H 15M",
				"Investigación de Usuarios en Diseño UI/UX.", "http://127.0.0.1:5500/video/video2.mp4", 2, cursoUIUX));
		saveModuloIfNotExist(new Modulo("Diseño de Interfaz de Usuario", "1H 20M", "Diseño de Interfaz de Usuario.",
				"http://127.0.0.1:5500/video/video3.mp4", 3, cursoUIUX));
		saveModuloIfNotExist(new Modulo("Prototipado y Wireframing", "1H 25M",
				"Prototipado y Wireframing en Diseño UI/UX.", "http://127.0.0.1:5500/video/video4.mp4", 4, cursoUIUX));
		saveModuloIfNotExist(new Modulo("Pruebas de Usabilidad", "1H 30M", "Pruebas de Usabilidad en Diseño UI/UX.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, cursoUIUX));
		saveModuloIfNotExist(new Modulo("Implementación y Feedback", "1H 35M",
				"Implementación y Feedback en Diseño UI/UX.", "http://127.0.0.1:5500/video/video6.mp4", 6, cursoUIUX));

		// Logotipos y Branding
		saveModuloIfNotExist(new Modulo("Introducción al Branding", "1H 5M", "Fundamentos del Branding.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoLB));
		saveModuloIfNotExist(new Modulo("Creación de Logotipos", "1H 10M", "Creación de Logotipos Efectivos.",
				"http://127.0.0.1:5500/video/video2.mp4", 2, cursoLB));
		saveModuloIfNotExist(new Modulo("Psicología del Color en Branding", "1H 15M",
				"Psicología del Color en Branding.", "http://127.0.0.1:5500/video/video3.mp4", 3, cursoLB));
		saveModuloIfNotExist(new Modulo("Estrategia de Marca", "1H 20M", "Estrategia de Marca para el Éxito.",
				"http://127.0.0.1:5500/video/video4.mp4", 4, cursoLB));
		saveModuloIfNotExist(new Modulo("Aplicaciones del Logotipo", "1H 25M", "Aplicaciones Prácticas del Logotipo.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, cursoLB));
		saveModuloIfNotExist(new Modulo("Proyectos de Branding", "1H 30M", "Proyectos Prácticos de Branding.",
				"http://127.0.0.1:5500/video/video6.mp4", 6, cursoLB));

		// Ilustración
		saveModuloIfNotExist(new Modulo("Fundamentos de la Ilustración", "1H 10M",
				"Fundamentos Básicos de la Ilustración.", "http://127.0.0.1:5500/video/video1.mp4", 1, cursoIL));
		saveModuloIfNotExist(new Modulo("Técnicas de Dibujo", "1H 15M", "Técnicas Esenciales de Dibujo.",
				"http://127.0.0.1:5500/video/video2.mp4", 2, cursoIL));
		saveModuloIfNotExist(new Modulo("Uso del Color en Ilustración", "1H 20M",
				"Aplicación del Color en Ilustración.", "http://127.0.0.1:5500/video/video3.mp4", 3, cursoIL));
		saveModuloIfNotExist(new Modulo("Ilustración Digital", "1H 25M", "Técnicas de Ilustración Digital.",
				"http://127.0.0.1:5500/video/video4.mp4", 4, cursoIL));
		saveModuloIfNotExist(new Modulo("Creación de Personajes", "1H 30M", "Métodos para Crear Personajes.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, cursoIL));
		saveModuloIfNotExist(new Modulo("Proyectos de Ilustración", "1H 35M", "Proyectos Prácticos de Ilustración.",
				"http://127.0.0.1:5500/video/video6.mp4", 6, cursoIL));

		// 3D y Animación
		saveModuloIfNotExist(new Modulo("Introducción al 3D y Animación", "1H 5M", "Fundamentos del 3D y la Animación.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, curso3DA));
		saveModuloIfNotExist(new Modulo("Modelado 3D", "1H 10M", "Técnicas de Modelado 3D.",
				"http://127.0.0.1:5500/images/video/video2", 2, curso3DA));
		saveModuloIfNotExist(new Modulo("Texturización y Materiales", "1H 15M",
				"Métodos de Texturización y Materiales.", "http://127.0.0.1:5500/video/video3.mp4", 3, curso3DA));
		saveModuloIfNotExist(new Modulo("Rigging y Animación", "1H 20M", "Técnicas de Rigging y Animación.",
				"http://127.0.0.1:5500/video/video4.mp4", 4, curso3DA));
		saveModuloIfNotExist(new Modulo("Iluminación y Renderizado", "1H 25M", "Métodos de Iluminación y Renderizado.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, curso3DA));
		saveModuloIfNotExist(new Modulo("Proyectos de Animación 3D", "1H 30M", "Proyectos Prácticos de Animación 3D.",
				"http://127.0.0.1:5500/video/video6.mp4", 6, curso3DA));

		// Introducción a Figma
		saveModuloIfNotExist(new Modulo("Fundamentos de Figma", "1H", "Fundamentos del Uso de Figma.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoFigma));
		saveModuloIfNotExist(new Modulo("Creación de Diseños en Figma", "1H 5M",
				"Creación de Diseños Efectivos en Figma.", "http://127.0.0.1:5500/video/video2.mp4", 2, cursoFigma));
		saveModuloIfNotExist(new Modulo("Colaboración en Figma", "1H 10M", "Métodos de Colaboración en Figma.",
				"http://127.0.0.1:5500/video/video3.mp4", 3, cursoFigma));
		saveModuloIfNotExist(
				new Modulo("Prototipado Interactivo", "1H 15M", "Técnicas de Prototipado Interactivo en Figma.",
						"http://127.0.0.1:5500/video/video4.mp4", 4, cursoFigma));
		saveModuloIfNotExist(new Modulo("Plugins y Extensiones", "1H 20M", "Uso de Plugins y Extensiones en Figma.",
				"http://127.0.0.1:5500/video/video5.mp4", 5, cursoFigma));
		saveModuloIfNotExist(new Modulo("Proyectos Prácticos en Figma", "1H 25M", "Proyectos Prácticos en Figma.",
				"http://127.0.0.1:5500/video/video6.mp4", 6, cursoFigma));

		// Composición y FX
		saveModuloIfNotExist(new Modulo("Introducción a la Composición", "1H 5M", "Fundamentos de la Composición.",
				"http://127.0.0.1:5500/video/video1.mp4", 1, cursoFX));
		saveModuloIfNotExist(new Modulo("Herramientas de Composición", "1H 10M", "Uso de Herramientas de Composición.",
				"http://127.0.0.1:5500/video/video2.mp4", 2, cursoFX));
		saveModuloIfNotExist(new Modulo("Efectos Visuales Básicos", "1H 15M", "Aplicación de Efectos Visuales Básicos.",
				"http://127.0.0.1:5500/video/video3.mp4", 3, cursoFX));
		saveModuloIfNotExist(new Modulo("Técnicas de Rotoscoping", "1H 20M", "Métodos de Rotoscoping.",
				"http://127.0.0.1:5500/video/video4.mp4", 4, cursoFX));
		saveModuloIfNotExist(new Modulo("Integración de Efectos 3D", "1H 25M",
				"Integración de Efectos 3D en Composición.", "http://127.0.0.1:5500/video/video5.mp4", 5, cursoFX));
	};

	private void saveModuloIfNotExist(Modulo modulo) {
		if (moduloRepository.findByTitulo(modulo.getTitulo()).isEmpty()) {
			moduloRepository.save(modulo);
		}
	}

	public void initPreguntasData() {
		// Obtener los módulos de los cursos
		Modulo moduloDG1 = moduloRepository.findById(1).orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
		Modulo moduloPS1 = moduloRepository.findById(7).orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
		Modulo moduloDW1 = moduloRepository.findById(13)
				.orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
		Modulo moduloUIUX1 = moduloRepository.findById(19)
				.orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
		Modulo moduloLB1 = moduloRepository.findById(25)
				.orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
		Modulo moduloIL1 = moduloRepository.findById(31)
				.orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
		Modulo modulo3DA1 = moduloRepository.findById(37)
				.orElseThrow(() -> new RuntimeException("Módulo no encontrado"));

		User usuario1 = userRepository.findById(1).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		// Diseño Gráfico
		savePreguntaIfNotExist(new Pregunta("¿Qué es el diseño gráfico?", LocalDate.now(), usuario1, moduloDG1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cuáles son los principios básicos del diseño?", LocalDate.now(), usuario1, moduloDG1));
		savePreguntaIfNotExist(new Pregunta("¿Cómo influye la teoría del color en el diseño gráfico?", LocalDate.now(),
				usuario1, moduloDG1));
		savePreguntaIfNotExist(new Pregunta("¿Qué papel juega la tipografía en el diseño gráfico?", LocalDate.now(),
				usuario1, moduloDG1));

		// Photoshop
		savePreguntaIfNotExist(new Pregunta("¿Cómo uso las capas en Photoshop?", LocalDate.now(), usuario1, moduloPS1));
		savePreguntaIfNotExist(
				new Pregunta("¿Qué son las máscaras en Photoshop?", LocalDate.now(), usuario1, moduloPS1));

		// Desarrollo Web
		savePreguntaIfNotExist(new Pregunta("¿Qué es HTML?", LocalDate.now(), usuario1, moduloDW1));
		savePreguntaIfNotExist(new Pregunta("¿Para qué sirve CSS?", LocalDate.now(), usuario1, moduloDW1));
		savePreguntaIfNotExist(
				new Pregunta("¿Qué es JavaScript y cómo se utiliza?", LocalDate.now(), usuario1, moduloDW1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cómo puedo hacer una página web responsive?", LocalDate.now(), usuario1, moduloDW1));
		savePreguntaIfNotExist(new Pregunta("¿Qué es un framework y cuál debo usar para desarrollo web?",
				LocalDate.now(), usuario1, moduloDW1));
		savePreguntaIfNotExist(new Pregunta("¿Cómo se realiza la integración de APIs en un sitio web?", LocalDate.now(),
				usuario1, moduloDW1));
		savePreguntaIfNotExist(new Pregunta("¿Qué es el DOM y cómo se manipula con JavaScript?", LocalDate.now(),
				usuario1, moduloDW1));
		savePreguntaIfNotExist(new Pregunta("¿Cuáles son las mejores prácticas para el SEO en desarrollo web?",
				LocalDate.now(), usuario1, moduloDW1));
		savePreguntaIfNotExist(new Pregunta("¿Qué es Git y cómo se utiliza en proyectos de desarrollo web?",
				LocalDate.now(), usuario1, moduloDW1));

		// UI/UX
		savePreguntaIfNotExist(
				new Pregunta("¿Qué es la experiencia de usuario?", LocalDate.now(), usuario1, moduloUIUX1));
		savePreguntaIfNotExist(new Pregunta("¿Cuáles son las mejores prácticas en diseño UI?", LocalDate.now(),
				usuario1, moduloUIUX1));
		savePreguntaIfNotExist(new Pregunta("¿Cómo se realiza una investigación de usuarios efectiva?", LocalDate.now(),
				usuario1, moduloUIUX1));
		savePreguntaIfNotExist(new Pregunta("¿Qué herramientas se utilizan para el prototipado y wireframing?",
				LocalDate.now(), usuario1, moduloUIUX1));

		// Logotipos y Branding
		savePreguntaIfNotExist(new Pregunta("¿Qué es un logotipo efectivo?", LocalDate.now(), usuario1, moduloLB1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cómo se crea una estrategia de marca?", LocalDate.now(), usuario1, moduloLB1));
		savePreguntaIfNotExist(new Pregunta("¿Cuál es la diferencia entre un logotipo y una marca?", LocalDate.now(),
				usuario1, moduloLB1));
		savePreguntaIfNotExist(new Pregunta("¿Qué características debe tener un buen logotipo?", LocalDate.now(),
				usuario1, moduloLB1));
		savePreguntaIfNotExist(new Pregunta("¿Cómo influye el color en el diseño de un logotipo?", LocalDate.now(),
				usuario1, moduloLB1));
		savePreguntaIfNotExist(new Pregunta("¿Qué importancia tiene la tipografía en un logotipo?", LocalDate.now(),
				usuario1, moduloLB1));
		savePreguntaIfNotExist(new Pregunta("¿Cómo se puede modernizar un logotipo sin perder su esencia?",
				LocalDate.now(), usuario1, moduloLB1));

		// Ilustración
		savePreguntaIfNotExist(
				new Pregunta("¿Qué herramientas necesito para ilustrar?", LocalDate.now(), usuario1, moduloIL1));

		// 3D y Animación
		savePreguntaIfNotExist(
				new Pregunta("¿Qué software es mejor para animación 3D?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cómo se hace el rigging de un modelo 3D?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(new Pregunta("¿Qué es la texturización en 3D y por qué es importante?", LocalDate.now(),
				usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cómo se crean materiales realistas en 3D?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Qué es el renderizado y cuáles son sus tipos?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cómo se puede optimizar el renderizado en 3D?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(new Pregunta("¿Qué es la animación de personajes y cuáles son sus principios básicos?",
				LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Cómo se usa el motion capture en animación 3D?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Qué es la iluminación global en 3D?", LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(new Pregunta("¿Cuáles son los diferentes tipos de cámaras usadas en 3D y animación?",
				LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(new Pregunta("¿Cómo se crea un storyboard para un proyecto de animación?",
				LocalDate.now(), usuario1, modulo3DA1));
		savePreguntaIfNotExist(
				new Pregunta("¿Qué es la cinemática inversa en animación 3D?", LocalDate.now(), usuario1, modulo3DA1));

	}

	private void savePreguntaIfNotExist(Pregunta pregunta) {
		if (preguntaRepository.findByContenido(pregunta.getContenido()).isEmpty()) {
			preguntaRepository.save(pregunta);
		}
	}
}
