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

import paixel.modelo.UserCurso;


public interface ServiceUserCurso {

	<S extends UserCurso> S save(S entity);

	<S extends UserCurso> List<S> saveAll(Iterable<S> entities);

	<S extends UserCurso> Optional<S> findOne(Example<S> example);

	List<UserCurso> findAll(Sort sort);

	void flush();

	Page<UserCurso> findAll(Pageable pageable);

	<S extends UserCurso> S saveAndFlush(S entity);

	<S extends UserCurso> List<S> saveAllAndFlush(Iterable<S> entities);

	List<UserCurso> findAll();

	List<UserCurso> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<UserCurso> entities);

	<S extends UserCurso> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<UserCurso> findById(Integer id);

	void deleteAllInBatch(Iterable<UserCurso> entities);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends UserCurso> long count(Example<S> example);

	<S extends UserCurso> boolean exists(Example<S> example);

	void deleteAllInBatch();

	UserCurso getOne(Integer id);

	<S extends UserCurso, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	UserCurso getById(Integer id);

	void delete(UserCurso entity);

	UserCurso getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends UserCurso> entities);

	<S extends UserCurso> List<S> findAll(Example<S> example);

	<S extends UserCurso> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}