package paixel.servicesImpl;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import paixel.DTO.WorkshopDTO;
import paixel.modelo.User;
import paixel.modelo.Workshop;
import paixel.repository.UserRepository;
import paixel.repository.WorkshopRepository;
import paixel.services.ServiceWorkshop;
@Service
public class ServiceWorkshopImpl implements ServiceWorkshop {
	@Autowired
	private WorkshopRepository workshopRepository;
	

	@Override
	public <S extends Workshop> S save(S entity) {
		return workshopRepository.save(entity);
	}

	@Override
	public <S extends Workshop> List<S> saveAll(Iterable<S> entities) {
		return workshopRepository.saveAll(entities);
	}

	@Override
	public <S extends Workshop> Optional<S> findOne(Example<S> example) {
		return workshopRepository.findOne(example);
	}

	@Override
	public List<Workshop> findAll(Sort sort) {
		return workshopRepository.findAll(sort);
	}

	@Override
	public void flush() {
		workshopRepository.flush();
	}

	@Override
	public Page<Workshop> findAll(Pageable pageable) {
		return workshopRepository.findAll(pageable);
	}

	@Override
	public <S extends Workshop> S saveAndFlush(S entity) {
		return workshopRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Workshop> List<S> saveAllAndFlush(Iterable<S> entities) {
		return workshopRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Workshop> findAll() {
		return workshopRepository.findAll();
	}

	@Override
	public List<Workshop> findAllById(Iterable<Integer> ids) {
		return workshopRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Workshop> entities) {
		workshopRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Workshop> Page<S> findAll(Example<S> example, Pageable pageable) {
		return workshopRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Workshop> findById(Integer id) {
		return workshopRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Workshop> entities) {
		workshopRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return workshopRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		workshopRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Workshop> long count(Example<S> example) {
		return workshopRepository.count(example);
	}

	@Override
	public <S extends Workshop> boolean exists(Example<S> example) {
		return workshopRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		workshopRepository.deleteAllInBatch();
	}

	@Override
	public Workshop getOne(Integer id) {
		return workshopRepository.getOne(id);
	}

	@Override
	public <S extends Workshop, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return workshopRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return workshopRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		workshopRepository.deleteById(id);
	}

	@Override
	public Workshop getById(Integer id) {
		return workshopRepository.getById(id);
	}

	@Override
	public void delete(Workshop entity) {
		workshopRepository.delete(entity);
	}

	@Override
	public Workshop getReferenceById(Integer id) {
		return workshopRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		workshopRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Workshop> entities) {
		workshopRepository.deleteAll(entities);
	}

	@Override
	public <S extends Workshop> List<S> findAll(Example<S> example) {
		return workshopRepository.findAll(example);
	}

	@Override
	public <S extends Workshop> List<S> findAll(Example<S> example, Sort sort) {
		return workshopRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		workshopRepository.deleteAll();
	}
	
	
	public List<WorkshopDTO> findAllWithUsernames() {
	    List<Workshop> workshops = workshopRepository.findAllWithUsers(); // Usa el nuevo método con fetch join
	    return workshops.stream()
	                    .map(w -> new WorkshopDTO(
	                    		w.getContenido(),
	                    		w.getDescripcion(),
	                    		w.getFecha(),
	                    		w.getUsuario().getUsername(),
	                    		w.getUsuario().getApellidos()))
	                    .collect(Collectors.toList());
	}
	
	 @Autowired
	    private UserRepository userRepository;
	
	public Workshop updateWorkshop(Integer id, Workshop workshopUpdates) {
        Optional<Workshop> workshopOptional = workshopRepository.findById(id);
        if (!workshopOptional.isPresent()) {
            throw new NoSuchElementException("No se encontró el workshop con el ID: " + id);
        }
        Workshop existingWorkshop = workshopOptional.get();

        if (workshopUpdates.getContenido() != null) {
            existingWorkshop.setContenido(workshopUpdates.getContenido());
        }
        if (workshopUpdates.getDescripcion() != null) {
            existingWorkshop.setDescripcion(workshopUpdates.getDescripcion());
        }
        if (workshopUpdates.getFecha() != null) {
            existingWorkshop.setFecha(workshopUpdates.getFecha());
        }
        if (workshopUpdates.getUsuario() != null) {
            User user = userRepository.findById(workshopUpdates.getUsuario().getIduser())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
            existingWorkshop.setUsuario(user);
        }

        return workshopRepository.save(existingWorkshop);
    }

	

}
