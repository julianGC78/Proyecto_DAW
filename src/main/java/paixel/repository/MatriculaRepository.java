package paixel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import paixel.modelo.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{

	 Optional<Matricula> findByUser_Iduser(Integer iduser);
	 long countByPagadoTrue();
	 
	 @Modifying
	    @Transactional
	    @Query("DELETE FROM Matricula m WHERE m.user.iduser = :userId")
	    void deleteByUserId(@Param("userId") Integer userId);
	 
//	 @Query("SELECT m FROM Matricula m WHERE m.user.iduser = :idUsuario AND m.curso.idcurso = :idCurso")
//	    Optional<Matricula> findByUserIdAndCursoId(@Param("idUsuario") Integer idUsuario, @Param("idCurso") Integer idCurso);
}
