package paixel.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import paixel.modelo.Modulo;
import paixel.modelo.User;
import paixel.modelo.UserModulo;

public interface UserModuloRepository extends JpaRepository<UserModulo, Integer> {
//
//	   UserModulo findByUsuarioAndModulo(User usuario, Modulo modulo);
//
//	    @Query("SELECT um FROM UserModulo um WHERE um.usuario.id = :idusuario AND um.modulo.curso.id = (SELECT m.curso.id FROM Modulo m WHERE m.id = :idmodulo)")
//	    List<UserModulo> findByIdusuarioAndIdcurso(@Param("idusuario") Integer idusuario, @Param("idmodulo") Integer idmodulo);
}
