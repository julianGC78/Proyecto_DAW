package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.ProgresoAlumno;

public interface ServiceProgresoAlumno {

	

	<S extends ProgresoAlumno> S save(S entity);

	<S extends ProgresoAlumno> List<S> saveAll(Iterable<S> entities);

	<S extends ProgresoAlumno> Optional<S> findOne(Example<S> example);

	List<ProgresoAlumno> findAll(Sort sort);

	void flush();

	Page<ProgresoAlumno> findAll(Pageable pageable);

	<S extends ProgresoAlumno> S saveAndFlush(S entity);

	<S extends ProgresoAlumno> List<S> saveAllAndFlush(Iterable<S> entities);

	List<ProgresoAlumno> findAll();

	List<ProgresoAlumno> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<ProgresoAlumno> entities);

	<S extends ProgresoAlumno> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<ProgresoAlumno> findById(Integer id);

	void deleteAllInBatch(Iterable<ProgresoAlumno> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends ProgresoAlumno> long count(Example<S> example);

	<S extends ProgresoAlumno> boolean exists(Example<S> example);

	void deleteAllInBatch();

	ProgresoAlumno getOne(Integer id);

	<S extends ProgresoAlumno, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	ProgresoAlumno getById(Integer id);

	void delete(ProgresoAlumno entity);

	ProgresoAlumno getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends ProgresoAlumno> entities);

	<S extends ProgresoAlumno> List<S> findAll(Example<S> example);

	<S extends ProgresoAlumno> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}