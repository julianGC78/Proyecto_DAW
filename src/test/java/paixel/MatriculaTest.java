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

import paixel.modelo.Curso;
import paixel.modelo.Matricula;
import paixel.modelo.User;
import paixel.servicesImpl.ServiceCursoImpl;
import paixel.servicesImpl.ServiceMatriculaImpl;
import paixel.servicesImpl.ServiceUserImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class MatriculaTest {

	@Autowired
	private ServiceMatriculaImpl sm;

	@Autowired
	private ServiceCursoImpl sc;

	@Autowired
	private ServiceUserImpl su;

	@Test
	void test01Insertar() {
		User usuario1 = su.findById(1).get();
		User usuario2 = su.findById(2).get();
		User usuario3 = su.findById(3).get();
		Curso curso1 = sc.findById(1).get();
		Curso curso2 = sc.findById(2).get();
		Curso curso3 = sc.findById(3).get();
		sm.save(new Matricula(usuario1,LocalDate.of(2022, 6, 2),true,false));
		sm.save(new Matricula(usuario2,LocalDate.of(2022, 8, 5),true,true));
		assertNotNull(sm.save(new Matricula(usuario3,LocalDate.now(),true,false)));
	}

	@Test
	public void test02findById() {
		assertEquals(LocalDate.of(2023, 3, 20), sm.findById(1).get().getFechaPago());
	}

	@Test
	public void test03findByAll() {
		assertEquals(4, sm.findAll().size());
	}

	@Test
	public void test04Modificar() {
		Matricula matricula = sm.findById(2).get();
		matricula.setPagado(false);
		assertEquals(LocalDate.of(2022, 6, 2), sm.save(matricula).getFechaPago());
	}

	@Test
	public void test05Eliminar() {
		Matricula matricula = sm.findById(4).get();
		sm.delete(matricula);
		assertTrue(sm.findById(4).isEmpty());
	}

}
