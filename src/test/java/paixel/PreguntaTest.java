package paixel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import paixel.modelo.Modulo;
import paixel.modelo.Pregunta;
import paixel.modelo.User;
import paixel.servicesImpl.ServiceModuloImpl;
import paixel.servicesImpl.ServicePreguntaImpl;
import paixel.servicesImpl.ServiceUserImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PreguntaTest {

	@Autowired
	private ServicePreguntaImpl sp;

	@Autowired
	private ServiceUserImpl su;

	@Autowired
	private ServiceModuloImpl sm;

//	@Test
//	void test01Insertar() {
//		User usuario1 = su.findById(1).get();
//		User usuario2 = su.findById(2).get();
//		User usuario3 = su.findById(3).get();
//		Modulo modulo1 = sm.findById(1).get();
//		Modulo modulo2 = sm.findById(2).get();
//		Modulo modulo3 = sm.findById(3).get();
//		sp.save(new Pregunta("Como se llama...", LocalDateTime.of(2023, 5, 15), usuario1, modulo1));
//		sp.save(new Pregunta("No entiendo...", LocalDateTime.of(2023, 10, 2), usuario2, modulo2));
//		assertNotNull(sp.save(new Pregunta("Por que...", LocalDateTime.of(2023, 3, 28), usuario1, modulo3)));
//		sp.save(new Pregunta("No me funciona...", LocalDateTime.of(2023, 2, 10), usuario2, modulo3));
//	}

	@Test
	public void test02findById() {
		assertEquals(LocalDate.of(2023, 5, 15), sp.findById(1).get().getFecha());
	}

	@Test
	public void test03findByAll() {
		assertEquals(4, sp.findAll().size());
	}

	@Test
	public void test04Modificar() {
		Pregunta pregunta = sp.findById(2).get();
		pregunta.setContenido("No entiendo la pre...");
		;
		;
		assertEquals(LocalDate.of(2023, 10, 2), sp.save(pregunta).getFecha());
	}

	@Test
	public void test05Eliminar() {
		Pregunta pregunta = sp.findById(4).get();
		sp.delete(pregunta);
		assertTrue(sp.findById(4).isEmpty());
	}

}
