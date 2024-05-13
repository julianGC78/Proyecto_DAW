package paixel.servicesImpl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import paixel.controller.UserCursoWS;
import paixel.modelo.UserCurso;
import paixel.repository.UserCursoRepository;
import paixel.services.ServiceUserCurso;

@Service
public class ServiceUserCursoImpl  implements ServiceUserCurso{

	@Autowired
	private UserCursoRepository userCursoRepository;

	public <S extends UserCurso> S save(S entity) {
		return userCursoRepository.save(entity);
	}

	public <S extends UserCurso> List<S> saveAll(Iterable<S> entities) {
		return userCursoRepository.saveAll(entities);
	}

	public <S extends UserCurso> Optional<S> findOne(Example<S> example) {
		return userCursoRepository.findOne(example);
	}

	public List<UserCurso> findAll(org.springframework.data.domain.Sort sort) {
		return userCursoRepository.findAll(sort);
	}

	public void flush() {
		userCursoRepository.flush();
	}

	public Page<UserCurso> findAll(org.springframework.data.domain.Pageable pageable) {
		return userCursoRepository.findAll(pageable);
	}

	public <S extends UserCurso> S saveAndFlush(S entity) {
		return userCursoRepository.saveAndFlush(entity);
	}

	public <S extends UserCurso> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userCursoRepository.saveAllAndFlush(entities);
	}

	public List<UserCurso> findAll() {
		return userCursoRepository.findAll();
	}

	public List<UserCurso> findAllById(Iterable<Integer> ids) {
		return userCursoRepository.findAllById(ids);
	}

	public void deleteInBatch(Iterable<UserCurso> entities) {
		userCursoRepository.deleteInBatch(entities);
	}

	public <S extends UserCurso> Page<S> findAll(Example<S> example,
			org.springframework.data.domain.Pageable pageable) {
		return userCursoRepository.findAll(example, pageable);
	}

	public Optional<UserCurso> findById(Integer id) {
		return userCursoRepository.findById(id);
	}

	public void deleteAllInBatch(Iterable<UserCurso> entities) {
		userCursoRepository.deleteAllInBatch(entities);
	}

	public boolean existsById(Integer id) {
		return userCursoRepository.existsById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userCursoRepository.deleteAllByIdInBatch(ids);
	}

	public <S extends UserCurso> long count(Example<S> example) {
		return userCursoRepository.count(example);
	}

	public <S extends UserCurso> boolean exists(Example<S> example) {
		return userCursoRepository.exists(example);
	}

	public void deleteAllInBatch() {
		userCursoRepository.deleteAllInBatch();
	}

	public UserCurso getOne(Integer id) {
		return userCursoRepository.getOne(id);
	}

	public <S extends UserCurso, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userCursoRepository.findBy(example, queryFunction);
	}

	public long count() {
		return userCursoRepository.count();
	}

	public void deleteById(Integer id) {
		userCursoRepository.deleteById(id);
	}

	public UserCurso getById(Integer id) {
		return userCursoRepository.getById(id);
	}

	public void delete(UserCurso entity) {
		userCursoRepository.delete(entity);
	}

	public UserCurso getReferenceById(Integer id) {
		return userCursoRepository.getReferenceById(id);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		userCursoRepository.deleteAllById(ids);
	}

	public void deleteAll(Iterable<? extends UserCurso> entities) {
		userCursoRepository.deleteAll(entities);
	}

	public <S extends UserCurso> List<S> findAll(Example<S> example) {
		return userCursoRepository.findAll(example);
	}

	public <S extends UserCurso> List<S> findAll(Example<S> example, org.springframework.data.domain.Sort sort) {
		return userCursoRepository.findAll(example, sort);
	}

	public void deleteAll() {
		userCursoRepository.deleteAll();
	}

	
	public List<UserCurso> findByUsuarioIdAndCursoId(int iduser, int idcurso) {
        return userCursoRepository.findByUsuarioIduserAndCursoIdcurso(iduser, idcurso);
    }

	

	
	
	
}
