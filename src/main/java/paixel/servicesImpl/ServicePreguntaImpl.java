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

import paixel.modelo.Modulo;
import paixel.modelo.Pregunta;
import paixel.modelo.User;
import paixel.repository.ModuloRepository;
import paixel.repository.PreguntaRepository;
import paixel.repository.UserRepository;
import paixel.services.ServicePregunta;
@Service
public class ServicePreguntaImpl implements ServicePregunta {

	@Autowired
	private PreguntaRepository preguntaRepository;

	@Override
	public <S extends Pregunta> S save(S entity) {
		return preguntaRepository.save(entity);
	}

	@Override
	public <S extends Pregunta> List<S> saveAll(Iterable<S> entities) {
		return preguntaRepository.saveAll(entities);
	}

	@Override
	public <S extends Pregunta> Optional<S> findOne(Example<S> example) {
		return preguntaRepository.findOne(example);
	}

	@Override
	public List<Pregunta> findAll(Sort sort) {
		return preguntaRepository.findAll(sort);
	}

	@Override
	public void flush() {
		preguntaRepository.flush();
	}

	@Override
	public Page<Pregunta> findAll(Pageable pageable) {
		return preguntaRepository.findAll(pageable);
	}

	@Override
	public <S extends Pregunta> S saveAndFlush(S entity) {
		return preguntaRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Pregunta> List<S> saveAllAndFlush(Iterable<S> entities) {
		return preguntaRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Pregunta> findAll() {
		return preguntaRepository.findAll();
	}

	public List<Pregunta> findAllById(Iterable<Integer> ids) {
		return preguntaRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Pregunta> entities) {
		preguntaRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Pregunta> Page<S> findAll(Example<S> example, Pageable pageable) {
		return preguntaRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Pregunta> findById(Integer id) {
		return preguntaRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Pregunta> entities) {
		preguntaRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return preguntaRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		preguntaRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Pregunta> long count(Example<S> example) {
		return preguntaRepository.count(example);
	}

	@Override
	public <S extends Pregunta> boolean exists(Example<S> example) {
		return preguntaRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		preguntaRepository.deleteAllInBatch();
	}

	@Override
	public Pregunta getOne(Integer id) {
		return preguntaRepository.getOne(id);
	}

	@Override
	public <S extends Pregunta, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return preguntaRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return preguntaRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		preguntaRepository.deleteById(id);
	}

	@Override
	public Pregunta getById(Integer id) {
		return preguntaRepository.getById(id);
	}

	@Override
	public void delete(Pregunta entity) {
		preguntaRepository.delete(entity);
	}

	@Override
	public Pregunta getReferenceById(Integer id) {
		return preguntaRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		preguntaRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Pregunta> entities) {
		preguntaRepository.deleteAll(entities);
	}

	@Override
	public <S extends Pregunta> List<S> findAll(Example<S> example) {
		return preguntaRepository.findAll(example);
	}

	@Override
	public <S extends Pregunta> List<S> findAll(Example<S> example, Sort sort) {
		return preguntaRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		preguntaRepository.deleteAll();
	}
	

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuloRepository moduloRepository;
	public List<Pregunta> findByModuloId(Integer idmodulo) {
        List<Pregunta> preguntas = preguntaRepository.findByModulo_Idmodulo(idmodulo);
        // Asegúrate de cargar la relación con usuario
        for (Pregunta pregunta : preguntas) {
            pregunta.getUsuario().getUsername(); // Fuerza la carga de la relación
        }
        return preguntas;
    }
	 public Pregunta updatePregunta(Integer id, Pregunta preguntaUpdates) {
	        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(id);
	        if (!preguntaOptional.isPresent()) {
	            throw new NoSuchElementException("No se encontró la pregunta con el ID: " + id);
	        }
	        Pregunta existingPregunta = preguntaOptional.get();

	        if (preguntaUpdates.getContenido() != null) {
	            existingPregunta.setContenido(preguntaUpdates.getContenido());
	        }
	        if (preguntaUpdates.getFecha() != null) {
	            existingPregunta.setFecha(preguntaUpdates.getFecha());
	        }
	        if (preguntaUpdates.getUsuario() != null) {
	            User user = userRepository.findById(preguntaUpdates.getUsuario().getIduser())
	                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
	            existingPregunta.setUsuario(user);
	        }
	        if (preguntaUpdates.getModulo() != null) {
	            Modulo modulo = moduloRepository.findById(preguntaUpdates.getModulo().getIdmodulo())
	                .orElseThrow(() -> new NoSuchElementException("Módulo no encontrado"));
	            existingPregunta.setModulo(modulo);
	        }

	        return preguntaRepository.save(existingPregunta);
	    }
}
