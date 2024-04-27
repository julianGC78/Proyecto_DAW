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

import paixel.modelo.Curso;

public interface ServiceCurso {

	<S extends Curso> S save(S entity);

	<S extends Curso> List<S> saveAll(Iterable<S> entities);

	<S extends Curso> Optional<S> findOne(Example<S> example);

	List<Curso> findAll(Sort sort);

	void flush();

	Page<Curso> findAll(Pageable pageable);

	<S extends Curso> S saveAndFlush(S entity);

	<S extends Curso> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Curso> findAll();

	List<Curso> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Curso> entities);

	<S extends Curso> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Curso> findById(Integer id);

	void deleteAllInBatch(Iterable<Curso> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Curso> long count(Example<S> example);

	<S extends Curso> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Curso getOne(Integer id);

	<S extends Curso, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Curso getById(Integer id);

	void delete(Curso entity);

	Curso getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Curso> entities);

	<S extends Curso> List<S> findAll(Example<S> example);

	<S extends Curso> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}