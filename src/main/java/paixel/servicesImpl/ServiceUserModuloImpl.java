package paixel.servicesImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import paixel.modelo.Modulo;
import paixel.modelo.Modulo;
import paixel.modelo.User;
import paixel.modelo.UserModulo;
import paixel.repository.ModuloRepository;
import paixel.repository.UserModuloRepository;
import paixel.repository.UserRepository;
import paixel.services.ServiceUserModulo;
@Service
public class ServiceUserModuloImpl implements ServiceUserModulo {
	
	@Autowired
	private UserModuloRepository userModuloRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModuloRepository moduloRepository;
	

	@Override
	public <S extends UserModulo> S save(S entity) {
		return userModuloRepository.save(entity);
	}

	@Override
	public <S extends UserModulo> List<S> saveAll(Iterable<S> entities) {
		return userModuloRepository.saveAll(entities);
	}

	@Override
	public <S extends UserModulo> Optional<S> findOne(Example<S> example) {
		return userModuloRepository.findOne(example);
	}

	@Override
	public List<UserModulo> findAll(Sort sort) {
		return userModuloRepository.findAll(sort);
	}

	@Override
	public void flush() {
		userModuloRepository.flush();
	}

	@Override
	public Page<UserModulo> findAll(Pageable pageable) {
		return userModuloRepository.findAll(pageable);
	}

	@Override
	public <S extends UserModulo> S saveAndFlush(S entity) {
		return userModuloRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends UserModulo> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userModuloRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<UserModulo> findAll() {
		return userModuloRepository.findAll();
	}

	@Override
	public List<UserModulo> findAllById(Iterable<Integer> ids) {
		return userModuloRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<UserModulo> entities) {
		userModuloRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends UserModulo> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userModuloRepository.findAll(example, pageable);
	}

	@Override
	public Optional<UserModulo> findById(Integer id) {
		return userModuloRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<UserModulo> entities) {
		userModuloRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return userModuloRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userModuloRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends UserModulo> long count(Example<S> example) {
		return userModuloRepository.count(example);
	}

	@Override
	public <S extends UserModulo> boolean exists(Example<S> example) {
		return userModuloRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		userModuloRepository.deleteAllInBatch();
	}

	@Override
	public UserModulo getOne(Integer id) {
		return userModuloRepository.getOne(id);
	}

	@Override
	public <S extends UserModulo, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userModuloRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return userModuloRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userModuloRepository.deleteById(id);
	}

	@Override
	public UserModulo getById(Integer id) {
		return userModuloRepository.getById(id);
	}

	@Override
	public void delete(UserModulo entity) {
		userModuloRepository.delete(entity);
	}

	@Override
	public UserModulo getReferenceById(Integer id) {
		return userModuloRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userModuloRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends UserModulo> entities) {
		userModuloRepository.deleteAll(entities);
	}

	@Override
	public <S extends UserModulo> List<S> findAll(Example<S> example) {
		return userModuloRepository.findAll(example);
	}

	@Override
	public <S extends UserModulo> List<S> findAll(Example<S> example, Sort sort) {
		return userModuloRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		userModuloRepository.deleteAll();
	}

//	   public void marcarModuloComoVisto(Integer idusuario, Integer idmodulo) {
//	        User user = userRepository.findById(idusuario)
//	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//	        Modulo modulo = moduloRepository.findById(idmodulo)
//	                .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
//
//	        UserModulo usuarioModulo = userModuloRepository.findByUsuarioAndModulo(user, modulo);
//	        if (usuarioModulo == null) {
//	            usuarioModulo = new UserModulo();
//	            usuarioModulo.setUsuario(user);
//	            usuarioModulo.setModulo(modulo);
//	            usuarioModulo.setEstado(true);
//	            usuarioModulo.setFecha(LocalDate.now());
//	        } else {
//	            usuarioModulo.setEstado(true);
//	            usuarioModulo.setFecha(LocalDate.now());
//	        }
//	        userModuloRepository.save(usuarioModulo);
//	    }
//
//
//	    public boolean todosModulosVistos(Integer idusuario, Integer idmodulo) {
//	        List<UserModulo> modulos = userModuloRepository.findByIdusuarioAndIdcurso(idusuario, idmodulo);
//	        return modulos.stream().allMatch(UserModulo::isEstado);
//	    }
//	
	 

}
