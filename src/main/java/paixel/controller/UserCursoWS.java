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

import paixel.modelo.UserCurso;
import paixel.servicesImpl.ServiceCursoImpl;
import paixel.servicesImpl.ServiceUserCursoImpl;
import paixel.servicesImpl.ServiceUserImpl;


@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/usuarioCurso")
public class UserCursoWS {
	
	@Autowired
	ServiceUserCursoImpl serviceUserCursoImpl;
	
	@Autowired
	ServiceUserImpl serviceUserImpl;
	
	@Autowired
	ServiceCursoImpl serviceCursoImpl;

	Map<String, Object> response = new HashMap<String, Object>();
	
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody UserCurso usuarioCurso) {
		UserCurso insertarUsuarioCurso;
		
		try {			
			insertarUsuarioCurso = serviceUserCursoImpl.save(usuarioCurso);		
		} catch (Exception e) {		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UserCurso>(insertarUsuarioCurso, HttpStatus.OK);
		 
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) {  
		Optional<UserCurso> usuario;
		try {
			 usuario = serviceUserCursoImpl.findById(id); // Asegúrate de pasar el id correctamente aquí
		        if (!usuario.isPresent()) {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
	}

	
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<UserCurso> usuarioCursos;
		try {
			usuarioCursos = serviceUserCursoImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<UserCurso>>(usuarioCursos, HttpStatus.OK);
	}
	
	 @GetMapping("/findByUserIdAndCursoId/{userId}/{cursoId}")
	    public ResponseEntity<?> findByUserIdAndCursoId(@PathVariable Integer userId, @PathVariable Integer cursoId) {
	        List<UserCurso> usuarioCursos = serviceUserCursoImpl.findByUsuarioIdAndCursoId(userId, cursoId);
	        if (usuarioCursos.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(usuarioCursos, HttpStatus.OK);
	    }



	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		 System.out.println("Intentando eliminar el usuario con ID: " + id);

		try {
			serviceUserCursoImpl.deleteById(id);
			response.put("message", "El ususario se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
