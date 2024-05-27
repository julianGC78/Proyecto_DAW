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

import jakarta.transaction.Transactional;
import paixel.modelo.Matricula;
import paixel.repository.MatriculaRepository;
import paixel.services.ServiceMatricula;

@Service
@Transactional
public class ServiceMatriculaImpl implements ServiceMatricula {
	
	@Autowired
	private MatriculaRepository matriculaRepository;

	@Override
	public <S extends Matricula> S save(S entity) {
		return matriculaRepository.save(entity);
	}

	@Override
	public <S extends Matricula> List<S> saveAll(Iterable<S> entities) {
		return matriculaRepository.saveAll(entities);
	}

	@Override
	public <S extends Matricula> Optional<S> findOne(Example<S> example) {
		return matriculaRepository.findOne(example);
	}

	@Override
	public List<Matricula> findAll(Sort sort) {
		return matriculaRepository.findAll(sort);
	}

	@Override
	public void flush() {
		matriculaRepository.flush();
	}

	@Override
	public Page<Matricula> findAll(Pageable pageable) {
		return matriculaRepository.findAll(pageable);
	}

	@Override
	public <S extends Matricula> S saveAndFlush(S entity) {
		return matriculaRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Matricula> List<S> saveAllAndFlush(Iterable<S> entities) {
		return matriculaRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Matricula> findAll() {
		return matriculaRepository.findAll();
	}

	@Override
	public List<Matricula> findAllById(Iterable<Integer> ids) {
		return matriculaRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Matricula> entities) {
		matriculaRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Matricula> Page<S> findAll(Example<S> example, Pageable pageable) {
		return matriculaRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Matricula> findById(Integer id) {
		return matriculaRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Matricula> entities) {
		matriculaRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return matriculaRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		matriculaRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Matricula> long count(Example<S> example) {
		return matriculaRepository.count(example);
	}

	@Override
	public <S extends Matricula> boolean exists(Example<S> example) {
		return matriculaRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		matriculaRepository.deleteAllInBatch();
	}

	@Override
	public Matricula getOne(Integer id) {
		return matriculaRepository.getOne(id);
	}

	@Override
	public <S extends Matricula, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return matriculaRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return matriculaRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		matriculaRepository.deleteById(id);
	}

	@Override
	public Matricula getById(Integer id) {
		return matriculaRepository.getById(id);
	}

	@Override
	public void delete(Matricula entity) {
		matriculaRepository.delete(entity);
	}

	@Override
	public Matricula getReferenceById(Integer id) {
		return matriculaRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		matriculaRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Matricula> entities) {
		matriculaRepository.deleteAll(entities);
	}

	@Override
	public <S extends Matricula> List<S> findAll(Example<S> example) {
		return matriculaRepository.findAll(example);
	}

	@Override
	public <S extends Matricula> List<S> findAll(Example<S> example, Sort sort) {
		return matriculaRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		matriculaRepository.deleteAll();
	}

	public Optional<Matricula> findByUser_Iduser(Integer iduser) {
		return matriculaRepository.findByUser_Iduser(iduser);
	}

	
	  public long countMatriculatedUsers() {
	        return matriculaRepository.countByPagadoTrue();
	    }
	
//	   public Optional<Matricula> findByUserIdAndCursoId(Integer idUsuario, Integer idCurso) {
//	        return matriculaRepository.findByUserIdAndCursoId(idUsuario, idCurso);
//	    }

}
