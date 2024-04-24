package paixel.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import paixel.modelo.Role;

public interface ServiceRole {

	<S extends Role> S save(S entity);

	<S extends Role> List<S> saveAll(Iterable<S> entities);

	<S extends Role> Optional<S> findOne(Example<S> example);

	List<Role> findAll(Sort sort);

	void flush();

	Page<Role> findAll(Pageable pageable);

	<S extends Role> S saveAndFlush(S entity);

	<S extends Role> List<S> saveAllAndFlush(Iterable<S> entities);

	List<Role> findAll();

	List<Role> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<Role> entities);

	<S extends Role> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Role> findById(Integer id);

	void deleteAllInBatch(Iterable<Role> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Role> long count(Example<S> example);

	<S extends Role> boolean exists(Example<S> example);

	void deleteAllInBatch();

	Role getOne(Integer id);

	<S extends Role, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	Role getById(Integer id);

	void delete(Role entity);

	Role getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Role> entities);

	<S extends Role> List<S> findAll(Example<S> example);

	<S extends Role> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}