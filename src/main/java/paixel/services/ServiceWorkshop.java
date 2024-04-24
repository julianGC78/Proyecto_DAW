package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.Workshop;

public interface ServiceWorkshop {

	<S extends Workshop> S save(S entity);

	<S extends Workshop> List<S> saveAll(Iterable<S> entities);

	<S extends Workshop> Optional<S> findOne(Example<S> example);

	List<Workshop> findAll(Sort sort);

	void flush();

	Page<Workshop> findAll(Pageable pageable);

	<S extends Workshop> S saveAndFlush(S entity);

	<S extends Workshop> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Workshop> findAll();

	List<Workshop> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Workshop> entities);

	<S extends Workshop> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Workshop> findById(Integer id);

	void deleteAllInBatch(Iterable<Workshop> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Workshop> long count(Example<S> example);

	<S extends Workshop> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Workshop getOne(Integer id);

	<S extends Workshop, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Workshop getById(Integer id);

	void delete(Workshop entity);

	Workshop getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Workshop> entities);

	<S extends Workshop> List<S> findAll(Example<S> example);

	<S extends Workshop> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}