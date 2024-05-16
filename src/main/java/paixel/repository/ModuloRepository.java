package paixel.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {

    List<Modulo> findByCurso_Idcurso(Integer idcurso);
    Optional<Modulo> findByTitulo(String titulo);


}
