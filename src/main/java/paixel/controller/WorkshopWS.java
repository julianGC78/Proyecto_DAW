package paixel.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<?> addWorkshop(
	    @RequestParam("contenido") String contenido,
	    @RequestParam("descripcion") String descripcion,
	    @RequestParam("fecha") String fecha,
	    @RequestParam("idusuario") Integer idUsuario) {

	    Map<String, Object> response = new HashMap<>();
	    try {
	        // Convertir la fecha
	        LocalDate parsedFecha = LocalDate.parse(fecha);

	        // Buscar el usuario por id
	        User user = serviceUserImpl.findById(idUsuario)
	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	        // Crear y guardar el workshop
	        Workshop workshop = new Workshop();
	        workshop.setContenido("/images/workshop/" + contenido); // Construir la ruta completa
	        workshop.setDescripcion(descripcion);
	        workshop.setFecha(parsedFecha);
	        workshop.setUsuario(user);

	        serviceWorkshopImpl.save(workshop);
	        return new ResponseEntity<>(workshop, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        response.put("message", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        response.put("message", "Error al añadir el workshop");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}



	
//	@PostMapping("/add")
//	public ResponseEntity<?> addWorkshop(
//	        @RequestParam("contenido") String contenido,
//	        @RequestParam("descripcion") String descripcion,
//	        @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
//	        @RequestParam("idusuario") Integer idUsuario,
//	        @RequestParam("imagen") MultipartFile imagen) {
//	    
//	    Map<String, Object> response = new HashMap<>();
//	    try {
//	        // Buscar el usuario por id
//	        User user = serviceUserImpl.findById(idUsuario)
//	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//	        // Crear y guardar el workshop
//	        Workshop workshop = new Workshop();
//	        workshop.setContenido(contenido);
//	        workshop.setDescripcion(descripcion);
//	        workshop.setFecha(fecha);
//	        workshop.setUsuario(user);
//
//	        // Guardar la imagen en el sistema de archivos
//	        String imagenPath = saveImage(imagen);
//	        // Aquí puedes añadir la lógica para guardar la ruta de la imagen si tienes un campo para ello en la entidad Workshop
//	        // Por ejemplo: workshop.setImagePath(imagenPath);
//
//	        Workshop nuevoWorkshop = serviceWorkshopImpl.save(workshop);
//	        return new ResponseEntity<>(nuevoWorkshop, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("message", "Error al añadir el workshop: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}

	private String saveImage(MultipartFile imagen) throws IOException {
	    String folder = "path/to/save/images/";
	    Path directory = (Path) Paths.get(folder);

	    // Crear el directorio si no existe
	    if (!Files.exists(directory)) {
	        Files.createDirectories(directory);
	    }

	    String filename = imagen.getOriginalFilename();
	    Path path = directory.resolve(filename);

	    // Asegurarse de que el archivo no exista para evitar sobrescribirlo
	    if (Files.exists(path)) {
	        throw new IOException("El archivo ya existe: " + filename);
	    }

	    byte[] bytes = imagen.getBytes();
	    Files.write(path, bytes);
	    return path.toString();
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
