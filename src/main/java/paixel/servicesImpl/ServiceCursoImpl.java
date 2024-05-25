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

import paixel.modelo.Curso;
import paixel.modelo.Docente;
import paixel.modelo.User;
import paixel.repository.CursoRepository;
import paixel.repository.DocenteRepository;
import paixel.repository.UserRepository;
import paixel.services.ServiceCurso;
@Service
public class ServiceCursoImpl implements ServiceCurso {
	@Autowired 
	private CursoRepository cursoRepository;

	@Override
	public <S extends Curso> S save(S entity) {
		return cursoRepository.save(entity);
	}

	@Override
	public <S extends Curso> List<S> saveAll(Iterable<S> entities) {
		return cursoRepository.saveAll(entities);
	}

	@Override
	public <S extends Curso> Optional<S> findOne(Example<S> example) {
		return cursoRepository.findOne(example);
	}

	@Override
	public List<Curso> findAll(Sort sort) {
		return cursoRepository.findAll(sort);
	}

	@Override
	public void flush() {
		cursoRepository.flush();
	}

	@Override
	public Page<Curso> findAll(Pageable pageable) {
		return cursoRepository.findAll(pageable);
	}

	@Override
	public <S extends Curso> S saveAndFlush(S entity) {
		return cursoRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Curso> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cursoRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}

	@Override
	public List<Curso> findAllById(Iterable<Integer> ids) {
		return cursoRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Curso> entities) {
		cursoRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Curso> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cursoRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Curso> findById(Integer id) {
		return cursoRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Curso> entities) {
		cursoRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return cursoRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		cursoRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Curso> long count(Example<S> example) {
		return cursoRepository.count(example);
	}

	@Override
	public <S extends Curso> boolean exists(Example<S> example) {
		return cursoRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		cursoRepository.deleteAllInBatch();
	}

	@Override
	public Curso getOne(Integer id) {
		return cursoRepository.getOne(id);
	}

	@Override
	public <S extends Curso, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cursoRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return cursoRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		cursoRepository.deleteById(id);
	}

	@Override
	public Curso getById(Integer id) {
		return cursoRepository.getById(id);
	}

	@Override
	public void delete(Curso entity) {
		cursoRepository.delete(entity);
	}

	@Override
	public Curso getReferenceById(Integer id) {
		return cursoRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		cursoRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Curso> entities) {
		cursoRepository.deleteAll(entities);
	}

	@Override
	public <S extends Curso> List<S> findAll(Example<S> example) {
		return cursoRepository.findAll(example);
	}

	@Override
	public <S extends Curso> List<S> findAll(Example<S> example, Sort sort) {
		return cursoRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		cursoRepository.deleteAll();
	}
	
	
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private DocenteRepository docenteRepository;
	    
	  public Curso updateCurso(Integer id, Curso cursoUpdates) {
	        Optional<Curso> cursoOptional = cursoRepository.findById(id);
	        if (!cursoOptional.isPresent()) {
	            throw new NoSuchElementException("No se encontró el curso con el ID: " + id);
	        }
	        Curso existingCurso = cursoOptional.get();

	        if (cursoUpdates.getTitulo() != null) {
	            existingCurso.setTitulo(cursoUpdates.getTitulo());
	        }
	        if (cursoUpdates.getDescripcion() != null) {
	            existingCurso.setDescripcion(cursoUpdates.getDescripcion());
	        }
	        if (cursoUpdates.getRecurso() != null) {
	            existingCurso.setRecurso(cursoUpdates.getRecurso());
	        }
	        if (cursoUpdates.getUser() != null) {
	            User user = userRepository.findById(cursoUpdates.getUser().getIduser())
	                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
	            existingCurso.setUser(user);
	        }
	        if (cursoUpdates.getDocente() != null) {
	            Docente docente = docenteRepository.findById(cursoUpdates.getDocente().getIddocente())
	                .orElseThrow(() -> new NoSuchElementException("Docente no encontrado"));
	            existingCurso.setDocente(docente);
	        }

	        return cursoRepository.save(existingCurso);
	    }
	

}
