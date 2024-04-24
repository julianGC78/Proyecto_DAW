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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import paixel.modelo.Role;
import paixel.servicesImpl.ServiceCursoImpl;
import paixel.servicesImpl.ServiceRoleImpl;

@RestController
@RequestMapping("/rol")
public class RolWS {
	@Autowired
	ServiceRoleImpl serviceRoleImpl;

	Map<String, Object> response = new HashMap<String, Object>();

	@GetMapping("/findAll")
	public ResponseEntity<?> buscarTodos() {
		List<Role> roles;
		try {
			roles = serviceRoleImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar roles: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody Role rol) {
		Role insertarRol;

		try {
			insertarRol = serviceRoleImpl.save(rol);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Role>(insertarRol, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		try {
			serviceRoleImpl.deleteById(id);
			response.put("message", "El rol esta borrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//
//	@PutMapping("/modificar")
//	public ResponseEntity<?> modificar(@RequestBody Role rol, @PathVariable Integer id) {
//		Role rol1 = serviceRoleImpl.findById(id).get();
//		Role rolActualizado = null;
//		Map<String, Object> response = new HashMap<>();
//
//		if (rol1 == null) {
//			response.put("message", "Error al modificar rol: No se encontro el rol buscado");
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		try {
//			rol1.setNombre(rol.getNombre());
//		} catch (Exception e) {
//			response.put("message", "Error al modificar rol: " + e.getMessage());
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return new ResponseEntity<Role>(rolActualizado, HttpStatus.OK);
//	}

}
