package paixel.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Matricula;
import paixel.servicesImpl.ServiceMatriculaImpl;
import paixel.servicesImpl.ServiceUserImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/matricula")
public class MatriculaWS {

	@Autowired
	ServiceMatriculaImpl serviceMatriculaImpl;

	@Autowired
	ServiceUserImpl serviceUserImpl;

	Map<String, Object> response = new HashMap<String, Object>();

	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody Matricula matricula) {
		Matricula insertarMatricula;

		try {
			insertarMatricula = serviceMatriculaImpl.save(matricula);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Matricula>(insertarMatricula, HttpStatus.OK);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) {
		Optional<Matricula> matricula;
		try {
			matricula = serviceMatriculaImpl.findById(id); // Asegúrate de pasar el id correctamente aquí
			if (!matricula.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("message", "Error al buscar matriculas: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(matricula.get(), HttpStatus.OK);

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Matricula> matriculas;
		try {
			matriculas = serviceMatriculaImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar las matriculas: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Matricula>>(matriculas, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		System.out.println("Intentando eliminar la matricula con ID: " + id);

		try {
			serviceMatriculaImpl.deleteById(id);
			response.put("message", "la matricula se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/estadoPago/{userId}")
	public ResponseEntity<?> obtenerEstadoDePago(@PathVariable Integer userId) {
		Optional<Matricula> matricula = serviceMatriculaImpl.findByUser_Iduser(userId);
		if (matricula.isPresent()) {
			return ResponseEntity.ok(matricula.get().isPagado());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/pagar/{userId}")
	public ResponseEntity<?> actualizarPago(@PathVariable Integer userId) {
		System.out.println("Actualizando pago para UserID: " + userId);
		Optional<Matricula> matriculaOpt = serviceMatriculaImpl.findByUser_Iduser(userId);
		if (matriculaOpt.isPresent()) {
			Matricula matricula = matriculaOpt.get();
			matricula.setPagado(true);
			matricula.setFechaPago(LocalDate.now());
			serviceMatriculaImpl.save(matricula);
			System.out.println("Pago actualizado para UserID: " + userId);
			return ResponseEntity.ok().build();
		} else {
			System.out.println("No se encontró matricula para UserID: " + userId);
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/countMatriculated")
	public ResponseEntity<?> countMatriculatedUsers() {
		Map<String, Object> response = new HashMap<>();
		try {
			long matriculatedUserCount = serviceMatriculaImpl.countMatriculatedUsers();
			response.put("matriculatedUserCount", matriculatedUserCount);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "Error al contar los usuarios matriculados: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
