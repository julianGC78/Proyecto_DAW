package paixel.servicesImpl;

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

import paixel.modelo.UsuarioModulo;
import paixel.repository.UserModuloRepository;
import paixel.services.ServiceUserModulo;
@Service
public class ServiceUserModuloImpl implements ServiceUserModulo {
	@Autowired
	private UserModuloRepository userModuloRepository;

	@Override
	public <S extends UsuarioModulo> S save(S entity) {
		return userModuloRepository.save(entity);
	}

	@Override
	public <S extends UsuarioModulo> List<S> saveAll(Iterable<S> entities) {
		return userModuloRepository.saveAll(entities);
	}

	@Override
	public <S extends UsuarioModulo> Optional<S> findOne(Example<S> example) {
		return userModuloRepository.findOne(example);
	}

	@Override
	public List<UsuarioModulo> findAll(Sort sort) {
		return userModuloRepository.findAll(sort);
	}

	@Override
	public void flush() {
		userModuloRepository.flush();
	}

	@Override
	public Page<UsuarioModulo> findAll(Pageable pageable) {
		return userModuloRepository.findAll(pageable);
	}

	@Override
	public <S extends UsuarioModulo> S saveAndFlush(S entity) {
		return userModuloRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends UsuarioModulo> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userModuloRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<UsuarioModulo> findAll() {
		return userModuloRepository.findAll();
	}

	@Override
	public List<UsuarioModulo> findAllById(Iterable<Integer> ids) {
		return userModuloRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<UsuarioModulo> entities) {
		userModuloRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends UsuarioModulo> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userModuloRepository.findAll(example, pageable);
	}

	@Override
	public Optional<UsuarioModulo> findById(Integer id) {
		return userModuloRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<UsuarioModulo> entities) {
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
	public <S extends UsuarioModulo> long count(Example<S> example) {
		return userModuloRepository.count(example);
	}

	@Override
	public <S extends UsuarioModulo> boolean exists(Example<S> example) {
		return userModuloRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		userModuloRepository.deleteAllInBatch();
	}

	@Override
	public UsuarioModulo getOne(Integer id) {
		return userModuloRepository.getOne(id);
	}

	@Override
	public <S extends UsuarioModulo, R> R findBy(Example<S> example,
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
	public UsuarioModulo getById(Integer id) {
		return userModuloRepository.getById(id);
	}

	@Override
	public void delete(UsuarioModulo entity) {
		userModuloRepository.delete(entity);
	}

	@Override
	public UsuarioModulo getReferenceById(Integer id) {
		return userModuloRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userModuloRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends UsuarioModulo> entities) {
		userModuloRepository.deleteAll(entities);
	}

	@Override
	public <S extends UsuarioModulo> List<S> findAll(Example<S> example) {
		return userModuloRepository.findAll(example);
	}

	@Override
	public <S extends UsuarioModulo> List<S> findAll(Example<S> example, Sort sort) {
		return userModuloRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		userModuloRepository.deleteAll();
	}
	
	

}
