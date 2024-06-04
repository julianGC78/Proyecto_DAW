package paixel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import paixel.modelo.Curso;
import paixel.modelo.Modulo;
import paixel.servicesImpl.ServiceCursoImpl;
import paixel.servicesImpl.ServiceModuloImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ModuloTest {

	@Autowired
	private ServiceModuloImpl sm;
	@Autowired
	private ServiceCursoImpl sc;

	@Test
	void test01Insertar() {
		Curso curso1 = sc.findById(1).get();
		Curso curso2 = sc.findById(2).get();
		Curso curso3 = sc.findById(3).get();
		sm.save(new Modulo("Tema 1","1H", "historia photoshop", "url1", 1, curso1));
		sm.save(new Modulo("Tema 2","1H", "historia adobe", "url2", 1, curso2));
		assertNotNull(sm.save(
				new Modulo("Tema 3","30M", "Crea tu primer diseño", "url3", 2, curso3)));
		sm.save(new Modulo("Tema 4","45", "Vistas photoshop", "url4", 4, curso2));

	}

	@Test
	public void test02findById() {
		assertEquals("Tema 1", sm.findById(1).get().getTitulo());
	}

	@Test
	public void test03findByAll() {
		assertEquals(4, sm.findAll().size());
	}

	@Test
	public void test04Modificar() {
		Modulo modulo = sm.findById(2).get();
		modulo.setOrden(2);
		assertEquals("url2", sm.save(modulo).getRecurso());
	}

	@Test
	public void test05Eliminar() {
		Modulo modulo = sm.findById(4).get();
		sm.delete(modulo);
		assertTrue(sm.findById(4).isEmpty());
	}
}
