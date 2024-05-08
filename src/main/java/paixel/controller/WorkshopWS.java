package paixel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import paixel.modelo.Workshop;
import paixel.servicesImpl.ServiceWorkshopImpl;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/workshop")
public class WorkshopWS {
	@Autowired
	private ServiceWorkshopImpl serviceWorkshopImpl;
	
	Map<String, Object> response = new HashMap<String, Object>();
	
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody Workshop workshop) {
		Workshop insertarWorkshop;

		try {
			insertarWorkshop = serviceWorkshopImpl.save(workshop);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Workshop>(insertarWorkshop, HttpStatus.OK);

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
}
