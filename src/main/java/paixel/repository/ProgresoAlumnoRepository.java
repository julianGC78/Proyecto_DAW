package paixel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.ProgresoAlumno;

public interface ProgresoAlumnoRepository  extends JpaRepository<ProgresoAlumno, Integer>{
    
//    Optional<ProgresoAlumno> findByModuloIdmoduloAndUsuarioIduser(Integer idModulo, Integer idUsuario);


}
