package paixel.controller;

import java.time.LocalDate;
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

import paixel.modelo.User;
import paixel.modelo.Workshop;
import paixel.servicesImpl.ServiceUserImpl;
import paixel.servicesImpl.ServiceWorkshopImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/workshop")
public class WorkshopWS {
	@Autowired
	private ServiceWorkshopImpl serviceWorkshopImpl;

	@Autowired
	private ServiceUserImpl serviceUserImpl;

	Map<String, Object> response = new HashMap<String, Object>();

	@PostMapping("/add")
	public ResponseEntity<?> addWorkshop(@RequestBody Map<String, Object> workshopData) {
		String contenido = (String) workshopData.get("contenido");
		String descripcion = (String) workshopData.get("descripcion");
		LocalDate fecha = LocalDate.parse((String) workshopData.get("fecha"));
		Integer idUsuario = Integer.parseInt((String) workshopData.get("idusuario"));

		// Buscar el usuario por id
		User user = serviceUserImpl.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		// Crear y guardar el workshop
		Workshop workshop = new Workshop();
		workshop.setContenido(contenido);
		workshop.setDescripcion(descripcion);
		workshop.setFecha(fecha);
		workshop.setUsuario(user);

		serviceWorkshopImpl.save(workshop);
		return new ResponseEntity<>(workshop, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Workshop> workshops;
		try {
			workshops = serviceWorkshopImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buserviceCursoImplar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Workshop>>(workshops, HttpStatus.OK);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) {
		Optional<Workshop> workshop;
		try {
			workshop = serviceWorkshopImpl.findById(id); // Asegúrate de pasar el id correctamente aquí
			if (!workshop.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(workshop.get(), HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		System.out.println("Intentando eliminar el workshop con ID: " + id);

		try {
			serviceWorkshopImpl.deleteById(id);
			response.put("message", "El workshop se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Map<String, Object> workshopData,
			@RequestHeader("Authorization") String token) {
		Map<String, Object> response = new HashMap<>();
		try {
			Workshop workshopUpdates = new Workshop();

			if (workshopData.get("contenido") != null) {
				workshopUpdates.setContenido((String) workshopData.get("contenido"));
			}
			if (workshopData.get("descripcion") != null) {
				workshopUpdates.setDescripcion((String) workshopData.get("descripcion"));
			}
			if (workshopData.get("fecha") != null) {
				workshopUpdates.setFecha(LocalDate.parse((String) workshopData.get("fecha")));
			}
			if (workshopData.get("idusuario") != null) {
				Integer idUsuario = Integer.parseInt((String) workshopData.get("idusuario"));
				User user = new User();
				user.setIduser(idUsuario);
				workshopUpdates.setUsuario(user);
			}

			Workshop updatedWorkshop = serviceWorkshopImpl.updateWorkshop(id, workshopUpdates);
			response.put("workshop", updatedWorkshop);
			response.put("message", "Workshop actualizado con éxito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			response.put("message", "No se encontró el workshop con el ID: " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("message", "Error al actualizar el workshop: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
