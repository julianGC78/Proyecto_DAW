package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.Matricula;

public interface ServiceMatricula {

	<S extends Matricula> S save(S entity);

	<S extends Matricula> List<S> saveAll(Iterable<S> entities);

	<S extends Matricula> Optional<S> findOne(Example<S> example);

	List<Matricula> findAll(Sort sort);

	void flush();

	Page<Matricula> findAll(Pageable pageable);

	<S extends Matricula> S saveAndFlush(S entity);

	<S extends Matricula> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Matricula> findAll();

	List<Matricula> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Matricula> entities);

	<S extends Matricula> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Matricula> findById(Integer id);

	void deleteAllInBatch(Iterable<Matricula> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Matricula> long count(Example<S> example);

	<S extends Matricula> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Matricula getOne(Integer id);

	<S extends Matricula, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Matricula getById(Integer id);

	void delete(Matricula entity);

	Matricula getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Matricula> entities);

	<S extends Matricula> List<S> findAll(Example<S> example);

	<S extends Matricula> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}