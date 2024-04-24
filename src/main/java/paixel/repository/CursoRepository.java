package paixel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paixel.modelo.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
