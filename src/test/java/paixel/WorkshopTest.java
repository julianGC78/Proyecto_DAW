package paixel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import paixel.modelo.User;
import paixel.modelo.Workshop;
import paixel.servicesImpl.ServiceUserImpl;
import paixel.servicesImpl.ServiceWorkshopImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class WorkshopTest {

	@Autowired
	private ServiceWorkshopImpl sw;

	@Autowired
	private ServiceUserImpl su;

	@Test
	void test01Insertar() {
		User usuario1 = su.findById(1).get();
		User usuario2 = su.findById(2).get();
		User usuario3 = su.findById(3).get();
		sw.save(new Workshop("url1", "Foto realista", LocalDate.now(), usuario1));
		sw.save(new Workshop("url2", "Portafolio", LocalDate.now(), usuario2));
		assertNotNull(
				sw.save(new Workshop("url3", "Diseño página", LocalDate.now(),
						usuario3)));
		sw.save(new Workshop("url4", "Trabajo4", LocalDate.now(),usuario3));
	}

	@Test
	public void test02findById() {
		assertEquals("url3", sw.findById(3).get().getContenido());
	}

	@Test
	public void test03findByAll() {
		assertEquals(4, sw.findAll().size());
	}

	@Test
	public void test04Modificar() {
		Workshop workshop = sw.findById(3).get();
		workshop.setDescripcion("Diseño página2");
		assertEquals("url3", sw.save(workshop).getContenido());
	}

	@Test
	public void test05Eliminar() {
		Workshop workshop = sw.findById(4).get();
		sw.delete(workshop);
		assertTrue(sw.findById(4).isEmpty());
	}
	
}
