package paixel.servicesImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import paixel.modelo.User;
import paixel.repository.MatriculaRepository;
import paixel.repository.UserRepository;
import paixel.services.ServiceUser;
@Service
public class ServiceUserImpl implements ServiceUser {
	@Autowired
	private UserRepository userRepository;
	
	  @Autowired
	    private PasswordEncoder passwordEncoder;
	  
	  @Autowired
	    private MatriculaRepository matriculaRepository;

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public <S extends User> List<S> saveAll(Iterable<S> entities) {
		return userRepository.saveAll(entities);
	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		return userRepository.findOne(example);
	}

	@Override
	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}

	@Override
	public void flush() {
		userRepository.flush();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public <S extends User> S saveAndFlush(S entity) {
		return userRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllById(Iterable<Integer> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<User> entities) {
		userRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userRepository.findAll(example, pageable);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<User> entities) {
		userRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		return userRepository.count(example);
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		return userRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		userRepository.deleteAllInBatch();
	}

	@Override
	public User getOne(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	
	@Override
	public User getById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public User getReferenceById(Integer id) {
		return userRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		return userRepository.findAll(example);
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		return userRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	
	public User updateUser(Integer id, User userUpdates) {
	    Optional<User> userOptional = userRepository.findById(id);
	    if (!userOptional.isPresent()) {
	        throw new NoSuchElementException("No se encontró el usuario con el ID: " + id);
	    }
	    User existingUser = userOptional.get();
	    
	    System.out.println("Fecha de nacimiento recibida: " + userUpdates.getFechaNacimiento()); // Agregar esto para depurar
	    
	    if (userUpdates.getUsername() != null) {
	        existingUser.setUsername(userUpdates.getUsername());
	    }
	   
	    if (userUpdates.getEmail() != null) {
	        existingUser.setEmail(userUpdates.getEmail());
	    }

	    if (userUpdates.getApellidos() != null) {
	        existingUser.setApellidos(userUpdates.getApellidos());
	    }
	    if (userUpdates.getDni() != null) {
	        existingUser.setDni(userUpdates.getDni());
	    }
	    if (userUpdates.getGenero() != null) {
	        existingUser.setGenero(userUpdates.getGenero());
	    }
	    if (userUpdates.getFechaNacimiento() != null) {
	        existingUser.setFechaNacimiento(userUpdates.getFechaNacimiento());
	    }
	    if (userUpdates.getLocalidad() != null) {
	    	existingUser.setLocalidad(userUpdates.getLocalidad());
	    }
	    existingUser.setMatricula(userUpdates.isMatricula());
	    if (userUpdates.getRole() != null) {
	        existingUser.setRole(userUpdates.getRole());
	    }

	    return userRepository.save(existingUser);
	}

	   @Transactional
	    public void deleteById(Integer id) {
	        // Primero, eliminar las matriculas asociadas al usuario.
	        matriculaRepository.deleteByUserId(id);
	        
	        // Luego, eliminar el usuario.
	        userRepository.deleteById(id);
	    }
	   
	   public long countUsers() {
	        return userRepository.count();
	    }
	
	   public long countMatriculatedUsers() {
	        return userRepository.countByMatriculaTrue();
	    }

}
