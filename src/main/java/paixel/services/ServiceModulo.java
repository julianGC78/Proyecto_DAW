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

import paixel.modelo.Modulo;

public interface ServiceModulo {

	<S extends Modulo> S save(S entity);

	<S extends Modulo> List<S> saveAll(Iterable<S> entities);

	<S extends Modulo> Optional<S> findOne(Example<S> example);

	List<Modulo> findAll(Sort sort);

	void flush();

	Page<Modulo> findAll(Pageable pageable);

	<S extends Modulo> S saveAndFlush(S entity);

	<S extends Modulo> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Modulo> findAll();

	List<Modulo> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Modulo> entities);

	<S extends Modulo> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Modulo> findById(Integer id);

	void deleteAllInBatch(Iterable<Modulo> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Modulo> long count(Example<S> example);

	<S extends Modulo> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Modulo getOne(Integer id);

	<S extends Modulo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Modulo getById(Integer id);

	void delete(Modulo entity);

	Modulo getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Modulo> entities);

	<S extends Modulo> List<S> findAll(Example<S> example);

	<S extends Modulo> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();
	
	List<Modulo> findByCursoId(Integer idcurso);

}