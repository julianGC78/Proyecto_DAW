package paixel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import paixel.modelo.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username); 
	User findByEmail(String email);
}
