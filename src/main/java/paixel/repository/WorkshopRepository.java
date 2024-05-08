package paixel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import paixel.modelo.User;
import paixel.modelo.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
	Optional<Workshop> findByUsuarioAndFecha(User usuario, LocalDate fecha);
	
	@Query("SELECT w FROM Workshop w JOIN FETCH w.usuario u")
	List<Workshop> findAllWithUsers();


}
