package paixel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Curso;
import paixel.modelo.Workshop;
import paixel.servicesImpl.ServiceCursoImpl;

/**
 * Controlador REST para la gestión de cursos.
 * <p>
 * Este controlador proporciona endpoints para la creación y recuperación de cursos.
 * Permite operaciones de tipo POST para agregar nuevos cursos y GET para consultar todos los cursos existentes.
 * </p>
 *
 * @author CrossOrigin Permite solicitudes CORS desde el origen especificado.
 * @RestController Indica que esta clase es un controlador REST.
 * @RequestMapping Establece la ruta base para todos los endpoints definidos en este controlador.
 */
@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/curso")
public class CursoWS {
	
	  /**
     * Servicio para las operaciones de curso.
     */
	@Autowired
	ServiceCursoImpl serviceCursoImpl;

	/**
     * Mapa utilizado para respuestas de error.
     */	
	Map<String, Object> response = new HashMap<String, Object>();

	/**
     * Inserta un nuevo curso en la base de datos.
     * <p>
     * Este método recibe un objeto {@link Curso} en el cuerpo de la solicitud y, si es posible,
     * lo inserta en la base de datos mediante el servicio asociado.
     * </p>
     *
     * @param curso Objeto {@link Curso} que se desea insertar.
     * @return ResponseEntity con el curso insertado o un mensaje de error si ocurre uno.
     */
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody Curso curso) {
		Curso insertarCurso;

		try {
			insertarCurso = serviceCursoImpl.save(curso);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Curso>(insertarCurso, HttpStatus.OK);

	}

	/**
     * Recupera todos los cursos disponibles en la base de datos.
     * <p>
     * Este método consulta al servicio asociado para recuperar una lista de todos los cursos
     * almacenados en la base de datos. Maneja cualquier excepción que pueda ocurrir durante
     * la recuperación.
     * </p>
     *
     * @return ResponseEntity con la lista de cursos o un mensaje de error si ocurre uno.
     */
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

}
