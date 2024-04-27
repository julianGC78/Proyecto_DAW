package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import paixel.modelo.UsuarioModulo;

public interface ServiceUserModulo {

	<S extends UsuarioModulo> S save(S entity);

	<S extends UsuarioModulo> List<S> saveAll(Iterable<S> entities);

	<S extends UsuarioModulo> Optional<S> findOne(Example<S> example);

	List<UsuarioModulo> findAll(Sort sort);

	void flush();

	Page<UsuarioModulo> findAll(Pageable pageable);

	<S extends UsuarioModulo> S saveAndFlush(S entity);

	<S extends UsuarioModulo> List<S> saveAllAndFlush(Iterable<S> entities);

	List<UsuarioModulo> findAll();

	List<UsuarioModulo> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<UsuarioModulo> entities);

	<S extends UsuarioModulo> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<UsuarioModulo> findById(Integer id);

	void deleteAllInBatch(Iterable<UsuarioModulo> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends UsuarioModulo> long count(Example<S> example);

	<S extends UsuarioModulo> boolean exists(Example<S> example);

	void deleteAllInBatch();

	UsuarioModulo getOne(Integer id);

	<S extends UsuarioModulo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	UsuarioModulo getById(Integer id);

	void delete(UsuarioModulo entity);

	UsuarioModulo getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends UsuarioModulo> entities);

	<S extends UsuarioModulo> List<S> findAll(Example<S> example);

	<S extends UsuarioModulo> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}