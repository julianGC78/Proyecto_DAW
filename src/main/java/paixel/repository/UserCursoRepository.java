package paixel.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.Curso;
import paixel.modelo.User;
import paixel.modelo.UserCurso;

public interface UserCursoRepository extends JpaRepository<UserCurso, Integer> {
	
    List<UserCurso> findByUsuarioIduserAndCursoIdcurso(Integer iduser, Integer idcurso);
//    UserCurso findByUsuarioAndCurso(User usuario, Curso curso);



}
