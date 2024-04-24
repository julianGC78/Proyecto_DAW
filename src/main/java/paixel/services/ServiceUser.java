package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.User;

public interface ServiceUser {

	Optional<User> findByUsername(String username);

	<S extends User> S save(S entity);

	<S extends User> List<S> saveAll(Iterable<S> entities);

	<S extends User> Optional<S> findOne(Example<S> example);

	List<User> findAll(Sort sort);

	void flush();

	Page<User> findAll(Pageable pageable);

	<S extends User> S saveAndFlush(S entity);

	<S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

	List<User> findAll();

	List<User> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<User> entities);

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<User> findById(Integer id);

	void deleteAllInBatch(Iterable<User> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends User> long count(Example<S> example);

	<S extends User> boolean exists(Example<S> example);

	void deleteAllInBatch();

	User getOne(Integer id);

	<S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	User getById(Integer id);

	void delete(User entity);

	User getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends User> entities);

	<S extends User> List<S> findAll(Example<S> example);

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}