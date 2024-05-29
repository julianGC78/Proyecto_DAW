package paixel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Curso;
import paixel.modelo.Docente;
import paixel.modelo.User;
import paixel.servicesImpl.ServiceCursoImpl;
import paixel.servicesImpl.ServiceDocenteImpl;
import paixel.servicesImpl.ServiceUserImpl;

/**
 * Controlador REST para la gestión de cursos.
 * <p>
 * Este controlador proporciona endpoints para la creación y recuperación de
 * cursos. Permite operaciones de tipo POST para agregar nuevos cursos y GET
 * para consultar todos los cursos existentes.
 * </p>
 *
 * @author CrossOrigin Permite solicitudes CORS desde el origen especificado.
 * @RestController Indica que esta clase es un controlador REST.
 * @RequestMapping Establece la ruta base para todos los endpoints definidos en
 *                 este controlador.
 */
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/curso")
public class CursoWS {

	/**
	 * Servicio para las operaciones de curso.
	 */
	@Autowired
	ServiceCursoImpl serviceCursoImpl;

	@Autowired
	ServiceUserImpl serviceUserImpl;

	@Autowired
	ServiceDocenteImpl serviceDocenteImpl;

	/**
	 * Mapa utilizado para respuestas de error.
	 */
	Map<String, Object> response = new HashMap<String, Object>();

	@PostMapping("/add")
	public ResponseEntity<?> addCurso(@RequestBody Map<String, Object> cursoData) {
		Map<String, Object> response = new HashMap<>();
		try {
			String titulo = (String) cursoData.get("titulo");
			String descripcion = (String) cursoData.get("descripcion");
			String recurso = (String) cursoData.get("recurso");
			Integer idUsuario = Integer.parseInt((String) cursoData.get("idusuario"));
			Integer idDocente = Integer.parseInt((String) cursoData.get("iddocente"));

			// Buscar el usuario y el docente por sus ids
			User user = serviceUserImpl.findById(idUsuario)
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario));
			Docente docente = serviceDocenteImpl.findById(idDocente)
					.orElseThrow(() -> new RuntimeException("Docente no encontrado: " + idDocente));

			// Crear y guardar el curso
			Curso curso = new Curso();
			curso.setTitulo(titulo);
			curso.setDescripcion(descripcion);
			curso.setRecurso(recurso);
			curso.setUser(user);
			curso.setDocente(docente);

			serviceCursoImpl.save(curso);
			return new ResponseEntity<>(curso, HttpStatus.OK);
		} catch (RuntimeException e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			response.put("message", "Error al añadir el curso");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Curso> usuarios;
		try {
			usuarios = serviceCursoImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buserviceCursoImplar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Curso>>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) {
		Optional<Curso> curso;
		try {
			curso = serviceCursoImpl.findById(id); // Asegúrate de pasar el id correctamente aquí
			if (!curso.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(curso.get(), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Map<String, Object> cursoData,
			@RequestHeader("Authorization") String token) {
		Map<String, Object> response = new HashMap<>();
		try {
			Curso cursoUpdates = new Curso();

			if (cursoData.get("titulo") != null) {
				cursoUpdates.setTitulo((String) cursoData.get("titulo"));
			}
			if (cursoData.get("descripcion") != null) {
				cursoUpdates.setDescripcion((String) cursoData.get("descripcion"));
			}
			if (cursoData.get("recurso") != null) {
				cursoUpdates.setRecurso((String) cursoData.get("recurso"));
			}
			if (cursoData.get("idusuario") != null) {
				Integer idUsuario = Integer.parseInt((String) cursoData.get("idusuario"));
				User user = new User();
				user.setIduser(idUsuario);
				cursoUpdates.setUser(user);
			}
			if (cursoData.get("iddocente") != null) {
				Integer idDocente = Integer.parseInt((String) cursoData.get("iddocente"));
				Docente docente = new Docente();
				docente.setIddocente(idDocente);
				cursoUpdates.setDocente(docente);
			}

			Curso updatedCurso = serviceCursoImpl.updateCurso(id, cursoUpdates);
			response.put("curso", updatedCurso);
			response.put("message", "Curso actualizado con éxito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			response.put("message", "No se encontró el curso con el ID: " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("message", "Error al actualizar el curso: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @GetMapping("/preguntas-por-curso")
	    public ResponseEntity<List<Map<String, Object>>> getPreguntasPorCurso() {
	        List<Map<String, Object>> data = serviceCursoImpl.getPreguntasPorCurso();
	        return new ResponseEntity<>(data, HttpStatus.OK);
	    }

}
