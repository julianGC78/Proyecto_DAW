package paixel.servicesImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import paixel.modelo.Docente;
import paixel.repository.DocenteRepository;

@Service
public class ServiceDocenteImpl implements ServiceDocente {

	@Autowired
	private DocenteRepository docenteRepository;

	@Override
	public <S extends Docente> S save(S entity) {
		return docenteRepository.save(entity);
	}

	@Override
	public <S extends Docente> List<S> saveAll(Iterable<S> entities) {
		return docenteRepository.saveAll(entities);
	}

	@Override
	public <S extends Docente> Optional<S> findOne(Example<S> example) {
		return docenteRepository.findOne(example);
	}

	@Override
	public List<Docente> findAll(Sort sort) {
		return docenteRepository.findAll(sort);
	}

	@Override
	public void flush() {
		docenteRepository.flush();
	}

	@Override
	public Page<Docente> findAll(Pageable pageable) {
		return docenteRepository.findAll(pageable);
	}

	@Override
	public <S extends Docente> S saveAndFlush(S entity) {
		return docenteRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Docente> List<S> saveAllAndFlush(Iterable<S> entities) {
		return docenteRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Docente> findAll() {
		return docenteRepository.findAll();
	}

	@Override
	public List<Docente> findAllById(Iterable<Integer> ids) {
		return docenteRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Docente> entities) {
		docenteRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Docente> Page<S> findAll(Example<S> example, Pageable pageable) {
		return docenteRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Docente> findById(Integer id) {
		return docenteRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Docente> entities) {
		docenteRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return docenteRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		docenteRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Docente> long count(Example<S> example) {
		return docenteRepository.count(example);
	}

	@Override
	public <S extends Docente> boolean exists(Example<S> example) {
		return docenteRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		docenteRepository.deleteAllInBatch();
	}

	@Override
	public Docente getOne(Integer id) {
		return docenteRepository.getOne(id);
	}

	@Override
	public <S extends Docente, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return docenteRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return docenteRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		docenteRepository.deleteById(id);
	}

	@Override
	public Docente getById(Integer id) {
		return docenteRepository.getById(id);
	}

	@Override
	public void delete(Docente entity) {
		docenteRepository.delete(entity);
	}

	@Override
	public Docente getReferenceById(Integer id) {
		return docenteRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		docenteRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Docente> entities) {
		docenteRepository.deleteAll(entities);
	}

	@Override
	public <S extends Docente> List<S> findAll(Example<S> example) {
		return docenteRepository.findAll(example);
	}

	@Override
	public <S extends Docente> List<S> findAll(Example<S> example, Sort sort) {
		return docenteRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		docenteRepository.deleteAll();
	}
	
	public Docente updateDocente(Integer id, Docente docenteUpdates) {
        Optional<Docente> docenteOptional = docenteRepository.findById(id);
        if (!docenteOptional.isPresent()) {
            throw new NoSuchElementException("No se encontró el docente con el ID: " + id);
        }
        Docente existingDocente = docenteOptional.get();

        if (docenteUpdates.getUsername() != null) {
            existingDocente.setUsername(docenteUpdates.getUsername());
        }
        if (docenteUpdates.getEspecialidad() != null) {
            existingDocente.setEspecialidad(docenteUpdates.getEspecialidad());
        }
        if (docenteUpdates.getDescripcion() != null) {
            existingDocente.setDescripcion(docenteUpdates.getDescripcion());
        }
        if (docenteUpdates.getRecurso() != null) {
            existingDocente.setRecurso(docenteUpdates.getRecurso());
        }

        return docenteRepository.save(existingDocente);
    }

}
