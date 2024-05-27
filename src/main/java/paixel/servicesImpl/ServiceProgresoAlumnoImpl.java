package paixel.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import paixel.modelo.ProgresoAlumno;
import paixel.repository.ProgresoAlumnoRepository;
import paixel.services.ServiceProgresoAlumno;

@Service
public class ServiceProgresoAlumnoImpl implements ServiceProgresoAlumno {

	@Autowired
	private ProgresoAlumnoRepository progresoAlumnoRepository;

	

	@Override
	public <S extends ProgresoAlumno> S save(S entity) {
		return progresoAlumnoRepository.save(entity);
	}

	@Override
	public <S extends ProgresoAlumno> List<S> saveAll(Iterable<S> entities) {
		return progresoAlumnoRepository.saveAll(entities);
	}

	@Override
	public <S extends ProgresoAlumno> Optional<S> findOne(Example<S> example) {
		return progresoAlumnoRepository.findOne(example);
	}

	@Override
	public List<ProgresoAlumno> findAll(Sort sort) {
		return progresoAlumnoRepository.findAll(sort);
	}

	@Override
	public void flush() {
		progresoAlumnoRepository.flush();
	}

	@Override
	public Page<ProgresoAlumno> findAll(Pageable pageable) {
		return progresoAlumnoRepository.findAll(pageable);
	}

	@Override
	public <S extends ProgresoAlumno> S saveAndFlush(S entity) {
		return progresoAlumnoRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ProgresoAlumno> List<S> saveAllAndFlush(Iterable<S> entities) {
		return progresoAlumnoRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<ProgresoAlumno> findAll() {
		return progresoAlumnoRepository.findAll();
	}

	@Override
	public List<ProgresoAlumno> findAllById(Iterable<Integer> ids) {
		return progresoAlumnoRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<ProgresoAlumno> entities) {
		progresoAlumnoRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ProgresoAlumno> Page<S> findAll(Example<S> example, Pageable pageable) {
		return progresoAlumnoRepository.findAll(example, pageable);
	}

	@Override
	public Optional<ProgresoAlumno> findById(Integer id) {
		return progresoAlumnoRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProgresoAlumno> entities) {
		progresoAlumnoRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return progresoAlumnoRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		progresoAlumnoRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends ProgresoAlumno> long count(Example<S> example) {
		return progresoAlumnoRepository.count(example);
	}

	@Override
	public <S extends ProgresoAlumno> boolean exists(Example<S> example) {
		return progresoAlumnoRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		progresoAlumnoRepository.deleteAllInBatch();
	}

	@Override
	public ProgresoAlumno getOne(Integer id) {
		return progresoAlumnoRepository.getOne(id);
	}

	@Override
	public <S extends ProgresoAlumno, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return progresoAlumnoRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return progresoAlumnoRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		progresoAlumnoRepository.deleteById(id);
	}

	@Override
	public ProgresoAlumno getById(Integer id) {
		return progresoAlumnoRepository.getById(id);
	}

	@Override
	public void delete(ProgresoAlumno entity) {
		progresoAlumnoRepository.delete(entity);
	}

	@Override
	public ProgresoAlumno getReferenceById(Integer id) {
		return progresoAlumnoRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		progresoAlumnoRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends ProgresoAlumno> entities) {
		progresoAlumnoRepository.deleteAll(entities);
	}

	@Override
	public <S extends ProgresoAlumno> List<S> findAll(Example<S> example) {
		return progresoAlumnoRepository.findAll(example);
	}

	@Override
	public <S extends ProgresoAlumno> List<S> findAll(Example<S> example, Sort sort) {
		return progresoAlumnoRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		progresoAlumnoRepository.deleteAll();
	}
	
	  
//	  public Optional<ProgresoAlumno> findByModuloIdmoduloAndUsuarioIduser(Integer idModulo, Integer idUsuario) {
//	        return progresoAlumnoRepository.findByModuloIdmoduloAndUsuarioIduser(idModulo, idUsuario);
//	    }
	
}
