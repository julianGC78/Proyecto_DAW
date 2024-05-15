package paixel.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.Docente;

public interface ServiceDocente {

	<S extends Docente> S save(S entity);

	<S extends Docente> List<S> saveAll(Iterable<S> entities);

	<S extends Docente> Optional<S> findOne(Example<S> example);

	List<Docente> findAll(Sort sort);

	void flush();

	Page<Docente> findAll(Pageable pageable);

	<S extends Docente> S saveAndFlush(S entity);

	<S extends Docente> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Docente> findAll();

	List<Docente> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Docente> entities);

	<S extends Docente> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Docente> findById(Integer id);

	void deleteAllInBatch(Iterable<Docente> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Docente> long count(Example<S> example);

	<S extends Docente> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Docente getOne(Integer id);

	<S extends Docente, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Docente getById(Integer id);

	void delete(Docente entity);

	Docente getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Docente> entities);

	<S extends Docente> List<S> findAll(Example<S> example);

	<S extends Docente> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}