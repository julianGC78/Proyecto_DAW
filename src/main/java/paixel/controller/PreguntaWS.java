package paixel.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Modulo;
import paixel.modelo.Pregunta;
import paixel.modelo.User;
import paixel.repository.ModuloRepository;
import paixel.repository.UserRepository;
import paixel.servicesImpl.ServiceModuloImpl;
import paixel.servicesImpl.ServicePreguntaImpl;
import paixel.servicesImpl.ServiceUserImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/pregunta")
public class PreguntaWS {

	@Autowired
	private ServicePreguntaImpl servicePreguntaImpl;

	@Autowired
	private ServiceUserImpl userServiceImpl;

	@Autowired
	private ServiceModuloImpl moduloServiceImpl;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModuloRepository moduloRepository;

	private Map<String, Object> response = new HashMap<>();

	 @PostMapping("/add")
	    public ResponseEntity<?> insert(@RequestBody Map<String, Object> preguntaData) {
	        Map<String, Object> response = new HashMap<>();

	        try {
	            String contenido = (String) preguntaData.get("contenido");
	            Integer idUsuario = (Integer) preguntaData.get("idusuario");
	            Integer idModulo = (Integer) preguntaData.get("idmodulo");
	            String fechaStr = (String) preguntaData.get("fecha");

	            if (contenido == null || idUsuario == null || idModulo == null || fechaStr == null) {
	                response.put("message", "Campos requeridos faltantes");
	                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	            }

	            User usuario = userServiceImpl.findById(idUsuario)
	                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario));
	            Modulo modulo = moduloServiceImpl.findById(idModulo)
	                    .orElseThrow(() -> new RuntimeException("Módulo no encontrado: " + idModulo));

	            LocalDateTime fecha;
	            try {
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	                fecha = LocalDateTime.parse(fechaStr, formatter);
	            } catch (DateTimeParseException e) {
	                response.put("message", "Formato de fecha inválido");
	                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	            }

	            Pregunta pregunta = new Pregunta();
	            pregunta.setContenido(contenido);
	            pregunta.setFecha(fecha); // Usar la fecha proporcionada
	            pregunta.setUsuario(usuario);
	            pregunta.setModulo(modulo);

	            Pregunta nuevaPregunta = servicePreguntaImpl.save(pregunta);
	            return new ResponseEntity<>(nuevaPregunta, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            response.put("message", e.getMessage());
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            response.put("message", "Error al insertar la pregunta");
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Optional<Pregunta> pregunta;

		try {
			pregunta = servicePreguntaImpl.findById(id);
			if (!pregunta.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("message", "Error al buscar la pregunta: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(pregunta.get(), HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Pregunta> preguntas;

		try {
			preguntas = servicePreguntaImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar las preguntas: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(preguntas, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			servicePreguntaImpl.deleteById(id);
			response.put("message", "La pregunta se borró correctamente.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "Error al borrar la pregunta: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/byModulo/{idmodulo}")
	public ResponseEntity<?> getPreguntasByModulo(@PathVariable Integer idmodulo) {
		try {
			List<Pregunta> preguntas = servicePreguntaImpl.findByModuloId(idmodulo);
			return new ResponseEntity<>(preguntas, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "Error al obtener las preguntas");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePregunta(@PathVariable Integer id, @RequestBody Map<String, Object> preguntaData,
			@RequestHeader("Authorization") String token) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pregunta preguntaUpdates = new Pregunta();

			if (preguntaData.get("contenido") != null) {
				preguntaUpdates.setContenido((String) preguntaData.get("contenido"));
			}
			if (preguntaData.get("fecha") != null) {
				preguntaUpdates.setFecha(LocalDateTime.parse((String) preguntaData.get("fecha")));
			}
			if (preguntaData.get("idusuario") != null) {
				Integer idUsuario = Integer.parseInt((String) preguntaData.get("idusuario"));
				User user = userRepository.findById(idUsuario)
						.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
				preguntaUpdates.setUsuario(user);
			}
			if (preguntaData.get("idmodulo") != null) {
				Integer idModulo = Integer.parseInt((String) preguntaData.get("idmodulo"));
				Modulo modulo = moduloRepository.findById(idModulo)
						.orElseThrow(() -> new NoSuchElementException("Módulo no encontrado"));
				preguntaUpdates.setModulo(modulo);
			}

			Pregunta updatedPregunta = servicePreguntaImpl.updatePregunta(id, preguntaUpdates);
			response.put("pregunta", updatedPregunta);
			response.put("message", "Pregunta actualizada con éxito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			response.put("message", "No se encontró la pregunta con el ID: " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("message", "Error al actualizar la pregunta: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	   @GetMapping("/total-preguntas")
	    public ResponseEntity<Integer> getTotalPreguntas() {
	        int totalPreguntas = servicePreguntaImpl.getTotalPreguntas();
	        return new ResponseEntity<>(totalPreguntas, HttpStatus.OK);
	    }
}
