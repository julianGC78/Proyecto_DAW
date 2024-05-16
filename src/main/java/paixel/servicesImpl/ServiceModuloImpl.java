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

import paixel.modelo.Modulo;
import paixel.repository.ModuloRepository;
import paixel.services.ServiceModulo;
@Service
public class ServiceModuloImpl implements ServiceModulo {

	@Autowired
	private ModuloRepository moduloRepository;

	@Override
	public <S extends Modulo> S save(S entity) {
		return moduloRepository.save(entity);
	}

	@Override
	public <S extends Modulo> List<S> saveAll(Iterable<S> entities) {
		return moduloRepository.saveAll(entities);
	}

	@Override
	public <S extends Modulo> Optional<S> findOne(Example<S> example) {
		return moduloRepository.findOne(example);
	}

	@Override
	public List<Modulo> findAll(Sort sort) {
		return moduloRepository.findAll(sort);
	}

	@Override
	public void flush() {
		moduloRepository.flush();
	}

	@Override
	public Page<Modulo> findAll(Pageable pageable) {
		return moduloRepository.findAll(pageable);
	}

	@Override
	public <S extends Modulo> S saveAndFlush(S entity) {
		return moduloRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Modulo> List<S> saveAllAndFlush(Iterable<S> entities) {
		return moduloRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Modulo> findAll() {
		return moduloRepository.findAll();
	}

	@Override
	public List<Modulo> findAllById(Iterable<Integer> ids) {
		return moduloRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Modulo> entities) {
		moduloRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Modulo> Page<S> findAll(Example<S> example, Pageable pageable) {
		return moduloRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Modulo> findById(Integer id) {
		return moduloRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Modulo> entities) {
		moduloRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return moduloRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		moduloRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Modulo> long count(Example<S> example) {
		return moduloRepository.count(example);
	}

	@Override
	public <S extends Modulo> boolean exists(Example<S> example) {
		return moduloRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		moduloRepository.deleteAllInBatch();
	}

	@Override
	public Modulo getOne(Integer id) {
		return moduloRepository.getOne(id);
	}

	@Override
	public <S extends Modulo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return moduloRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return moduloRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		moduloRepository.deleteById(id);
	}

	@Override
	public Modulo getById(Integer id) {
		return moduloRepository.getById(id);
	}

	@Override
	public void delete(Modulo entity) {
		moduloRepository.delete(entity);
	}

	@Override
	public Modulo getReferenceById(Integer id) {
		return moduloRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		moduloRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Modulo> entities) {
		moduloRepository.deleteAll(entities);
	}

	@Override
	public <S extends Modulo> List<S> findAll(Example<S> example) {
		return moduloRepository.findAll(example);
	}

	@Override
	public <S extends Modulo> List<S> findAll(Example<S> example, Sort sort) {
		return moduloRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		moduloRepository.deleteAll();
	}

	public List<Modulo> findByCurso_Idcurso(Integer idcurso) {
		return moduloRepository.findByCurso_Idcurso(idcurso);
	}

	@Override
	public List<Modulo> findByCursoId(Integer idcurso) {
		  return moduloRepository.findByCurso_Idcurso(idcurso);
	}

	public Optional<Modulo> findByTitulo(String titulo) {
		return moduloRepository.findByTitulo(titulo);
	}

	
	
	
	
	  
}
