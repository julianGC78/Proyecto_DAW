package paixel.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {
	List<Pregunta> findByModulo_Idmodulo(Integer idmodulo);
    List<Pregunta> findByContenido(String contenido);


}
