package paixel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Curso;
import paixel.servicesImpl.ServiceCursoImpl;

@RestController
@RequestMapping("/curso")
public class CursoWS {
	@Autowired
	ServiceCursoImpl serviceCursoImpl;

	Map<String, Object> response = new HashMap<String, Object>();

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

}
