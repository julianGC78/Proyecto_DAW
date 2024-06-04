package paixel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.servicesImpl.ServiceUserImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserTest {

	@Autowired
	private ServiceUserImpl su;
	

	@Test
	void test01Insertar() {
		
	    User julian = new User("Julián", "Grijuela", "1234", "julian@hotmail.com",
	            "masculino", "hjfdkjgh77", LocalDate.now(), "Oviedo", true, Role.USER);

	    User luisa = new User("Luisa", "Sanchez", "1234", "sanchez@hotmail.com",
	            "femenino", "abc123", LocalDate.of(1990, 5, 15), "Gijón", false, Role.USER);

		User pedro = new User("Pedro", "Martínez", "abcd123", "pedro@email.com",
		        "masculino", "XYZ987", LocalDate.of(1985, 8, 20), "Gijón", true, Role.USER);
		su.save(pedro);

		// Asegúrate de que la instancia 'pedro' no sea nula
		assertNotNull(pedro, "La instancia 'pedro' no debería ser nula");

		User ana = new User("Ana", "García", "password123", "ana@example.com",
		        "femenino", "PQR456", LocalDate.of(1992, 3, 10), "Oviedo", false, Role.USER);

	}

	@Test
	public void test02findById() {
		assertEquals("Julián", su.findById(1).get().getUsername());
	}

	@Test
	public void test03findByAll() {
		assertEquals(4, su.findAll().size());
	}

	@Test
	public void test04Modificar() {
		User usuario = su.findById(3).get();
		usuario.setPassword("4321");
		assertEquals("Pepe", su.save(usuario).getUsername());
	}

	@Test
	public void test05Eliminar() {
		User usuario = su.findById(4).get();
		su.delete(usuario);
		assertTrue(su.findById(4).isEmpty());
	}

}
