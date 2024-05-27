package paixel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paixel.modelo.Curso;
import paixel.modelo.Modulo;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	  Optional<Curso> findByTitulo(String titulo);
}
