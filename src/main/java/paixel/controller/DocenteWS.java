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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.jwt.JwtService;
import paixel.modelo.Docente;
import paixel.servicesImpl.ServiceDocenteImpl;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/docente")
public class DocenteWS {

	@Autowired
	ServiceDocenteImpl serviceDocenteImpl;
	
	Map<String, Object> response = new HashMap<String, Object>();
	
	
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody Docente usuario) {
		Docente insertarDocente;
		
		try {			
			insertarDocente = serviceDocenteImpl.save(usuario);		
		} catch (Exception e) {		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Docente>(insertarDocente, HttpStatus.OK);
		 
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) {  
		Optional<Docente> docente;
		try {
			docente = serviceDocenteImpl.findById(id); // Asegúrate de pasar el id correctamente aquí
		        if (!docente.isPresent()) {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(docente.get(), HttpStatus.OK);

	}

	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Docente> docentes;
		try {
			docentes = serviceDocenteImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Docente>>(docentes, HttpStatus.OK);
	}


	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		 System.out.println("Intentando eliminar el usuario con ID: " + id);

		try {
			serviceDocenteImpl.deleteById(id);
			response.put("message", "El ususario se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 @Autowired
	    private JwtService jwtService;

	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Docente docenteUpdates, @RequestHeader("Authorization") String token) {
	        Map<String, Object> response = new HashMap<>();
	        try {
	            Docente updatedDocente = serviceDocenteImpl.updateDocente(id, docenteUpdates);
	            
	            response.put("docente", updatedDocente);
	          
	            response.put("message", "Docente actualizado con éxito");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            response.put("message", "No se encontró el docente con el ID: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            response.put("message", "Error al actualizar el docente: " + e.getMessage());
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
