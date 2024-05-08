package paixel.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.User;
import paixel.modelo.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
	Optional<Workshop> findByUsuarioAndFecha(User usuario, LocalDateTime fecha);


}
