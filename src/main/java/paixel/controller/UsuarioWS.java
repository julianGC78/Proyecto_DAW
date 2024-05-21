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
import paixel.modelo.User;
import paixel.servicesImpl.ServiceUserImpl;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/usuario")
public class UsuarioWS {
	
	@Autowired
	ServiceUserImpl serviceUserImpl;
	@Autowired
	JwtService jwtService;

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
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) {  
		Optional<User> usuario;
		try {
			 usuario = serviceUserImpl.findById(id); // Asegúrate de pasar el id correctamente aquí
		        if (!usuario.isPresent()) {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
	}

	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(String email) {  
		Optional<User> usuario;
		try {
		usuario = serviceUserImpl.findByEmail(email);
		} catch (Exception e) {
			response.put("message", "Error al buscar usuarios: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(HttpStatus.OK);
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
		 System.out.println("Intentando eliminar el usuario con ID: " + id);

		try {
			serviceUserImpl.deleteById(id);
			response.put("message", "El ususario se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RestController
	@RequestMapping("/usuario")
	public class UserController {

	    @Autowired
	    private JwtService jwtService;

	    @Autowired
	    private ServiceUserImpl serviceUserImpl;

	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User userUpdates, @RequestHeader("Authorization") String token) {
	        Map<String, Object> response = new HashMap<>();
	        try {
	            // Obtener el nombre de usuario del token
	            String username = jwtService.extractUsername(token.substring(7));
	            User authenticatedUser = serviceUserImpl.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

	            // Log para depuración
	            System.out.println("Usuario autenticado: " + authenticatedUser.getUsername() + ", Rol: " + authenticatedUser.getRole());
	            System.out.println("Intentando actualizar el usuario con ID: " + id);

	            // Verificar si el usuario es ADMIN o si está actualizando su propio perfil
	            if (!authenticatedUser.getRole().equals("ADMIN") && !authenticatedUser.getIduser().equals(id)) {
	                response.put("message", "No tienes permiso para actualizar este perfil");
	                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	            }

	            User updatedUser = serviceUserImpl.updateUser(id, userUpdates);
	            String newToken = jwtService.getToken(updatedUser);
	            response.put("user", updatedUser);
	            response.put("newToken", newToken);
	            response.put("message", "Usuario actualizado con éxito");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            response.put("message", "No se encontró el usuario con el ID: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            response.put("message", "Error al actualizar el usuario: " + e.getMessage());
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	}






}
