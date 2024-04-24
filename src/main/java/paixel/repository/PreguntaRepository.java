package paixel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

}
