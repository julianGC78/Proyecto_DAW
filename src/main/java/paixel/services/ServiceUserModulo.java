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

import paixel.modelo.UserModulo;

public interface ServiceUserModulo {

	<S extends UserModulo> S save(S entity);

	<S extends UserModulo> List<S> saveAll(Iterable<S> entities);

	<S extends UserModulo> Optional<S> findOne(Example<S> example);

	List<UserModulo> findAll(Sort sort);

	void flush();

	Page<UserModulo> findAll(Pageable pageable);

	<S extends UserModulo> S saveAndFlush(S entity);

	<S extends UserModulo> List<S> saveAllAndFlush(Iterable<S> entities);

	List<UserModulo> findAll();

	List<UserModulo> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<UserModulo> entities);

	<S extends UserModulo> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<UserModulo> findById(Integer id);

	void deleteAllInBatch(Iterable<UserModulo> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends UserModulo> long count(Example<S> example);

	<S extends UserModulo> boolean exists(Example<S> example);

	void deleteAllInBatch();

	UserModulo getOne(Integer id);

	<S extends UserModulo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	UserModulo getById(Integer id);

	void delete(UserModulo entity);

	UserModulo getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends UserModulo> entities);

	<S extends UserModulo> List<S> findAll(Example<S> example);

	<S extends UserModulo> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}