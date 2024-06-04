package paixel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import paixel.modelo.Curso;
import paixel.modelo.Docente;
import paixel.modelo.User;
import paixel.servicesImpl.ServiceCursoImpl;
import paixel.servicesImpl.ServiceDocenteImpl;
import paixel.servicesImpl.ServiceUserImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CursoTest {

	@Autowired
	private ServiceCursoImpl serviceCursoImpl;
	@Autowired
	private ServiceUserImpl serviceUserImpl;
	@Autowired
	private ServiceDocenteImpl serviceDocenteImpl;

	@Test
	public void test01Insertar() {
		User usuario1 = serviceUserImpl.findById(1).get();
		User usuario2 = serviceUserImpl.findById(2).get();
		User usuario3 = serviceUserImpl.findById(3).get();
		Docente docente1 = serviceDocenteImpl.findById(1).get();
		Docente docente2 = serviceDocenteImpl.findById(2).get();

		serviceCursoImpl.save(new Curso("Photoshop", "completo", usuario1, docente1));
		assertNotNull(new Curso("Photoshop", "completo", usuario1, docente1)); 
		serviceCursoImpl.save(new Curso("Photoshop", "completo", usuario1, docente1));
		

	}

	@Test
	public void test02findById() {
		assertEquals("Photoshop", serviceCursoImpl.findById(1).get().getTitulo());
	}

	@Test
	public void test03findByAll() {
		assertEquals(4, serviceCursoImpl.findAll().size());
	}

	@Test
	public void test04Modificar() {
		Curso curso = serviceCursoImpl.findById(2).get();
		curso.setDescripcion("Incompleto");
		assertEquals("After Effects", serviceCursoImpl.save(curso).getTitulo());
	}

	@Test
	public void test05Eliminar() {
		Curso curso = serviceCursoImpl.findById(4).get();
		serviceCursoImpl.delete(curso);
		assertTrue(serviceCursoImpl.findById(4).isEmpty());
	}

}
