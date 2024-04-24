package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.Pregunta;

public interface ServicePregunta {

	<S extends Pregunta> S save(S entity);

	<S extends Pregunta> List<S> saveAll(Iterable<S> entities);

	<S extends Pregunta> Optional<S> findOne(Example<S> example);

	List<Pregunta> findAll(Sort sort);

	void flush();

	Page<Pregunta> findAll(Pageable pageable);

	<S extends Pregunta> S saveAndFlush(S entity);

	<S extends Pregunta> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Pregunta> findAll();

	List<Pregunta> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Pregunta> entities);

	<S extends Pregunta> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Pregunta> findById(Integer id);

	void deleteAllInBatch(Iterable<Pregunta> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Pregunta> long count(Example<S> example);

	<S extends Pregunta> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Pregunta getOne(Integer id);

	<S extends Pregunta, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Pregunta getById(Integer id);

	void delete(Pregunta entity);

	Pregunta getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Pregunta> entities);

	<S extends Pregunta> List<S> findAll(Example<S> example);

	<S extends Pregunta> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}