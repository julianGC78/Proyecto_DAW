package paixel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import paixel.modelo.User;
import paixel.servicesImpl.ServiceUserImpl;

@RestController
@RequestMapping("/usuario")
public class UsuarioWS {
	
	@Autowired
	ServiceUserImpl serviceUserImpl;

	Map<String, Object> response = new HashMap<String, Object>();
	
	
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody User usuario) {
		User insertarUsuario;
		
		try {			
			insertarUsuario = serviceUserImpl.save(usuario);		
		} catch (Exception e) {		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(insertarUsuario, HttpStatus.OK);
		 
	}

	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(String email) {  
		User usuario;
		try {
		usuario = serviceUserImpl.findByEmail(email);
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(usuario, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<User> usuarios;
		try {
			usuarios = serviceUserImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<User>>(usuarios, HttpStatus.OK);
	}


	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		try {
			serviceUserImpl.deleteById(id);
			response.put("message", "El ususario se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("messge", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
