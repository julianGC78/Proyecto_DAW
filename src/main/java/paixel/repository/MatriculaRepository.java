package paixel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{

	 Optional<Matricula> findByUser_Iduser(Integer iduser);
}
