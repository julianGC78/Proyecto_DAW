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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Modulo;
import paixel.servicesImpl.ServiceModuloImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/modulo")
public class ModuloWS {

	@Autowired
	ServiceModuloImpl serviceModuloImpl;

	Map<String, Object> response = new HashMap<String, Object>();

	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody Modulo modulo) {
		Modulo insertarModulo;

		try {
			insertarModulo = serviceModuloImpl.save(modulo);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Modulo>(insertarModulo, HttpStatus.OK);
	}

	 @GetMapping("/findById/{idmodulo}")
	    public ResponseEntity<?> findById(@PathVariable Integer idmodulo) {
	        Optional<Modulo> modulo = serviceModuloImpl.findById(idmodulo);
	        if (!modulo.isPresent()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(modulo.get(), HttpStatus.OK);
	    }

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Modulo> modulos;
		try {
			modulos = serviceModuloImpl.findAll();
		} catch (Exception e) {
			response.put("message", "Error al buscar los modulos: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Modulo>>(modulos, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		System.out.println("Intentando eliminar el modulo con ID: " + id);

		try {
			serviceModuloImpl.deleteById(id);
			response.put("message", "el modulo se borro");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/byCurso/{idcurso}")
    public ResponseEntity<?> findByCursoId(@PathVariable Integer idcurso) {
        List<Modulo> modulos;
        try {
            modulos = serviceModuloImpl.findByCursoId(idcurso);
            if (modulos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al buscar módulos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(modulos, HttpStatus.OK);
    }
}
