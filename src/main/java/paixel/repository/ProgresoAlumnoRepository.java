package paixel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import paixel.modelo.ProgresoAlumno;

public interface ProgresoAlumnoRepository  extends JpaRepository<ProgresoAlumno, Integer>{
    
	 

}
