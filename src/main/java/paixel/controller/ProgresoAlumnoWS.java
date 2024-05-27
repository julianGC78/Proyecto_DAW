package paixel.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paixel.modelo.Curso;
import paixel.modelo.Matricula;
import paixel.modelo.Modulo;
import paixel.modelo.ProgresoAlumno;
import paixel.modelo.User;
import paixel.repository.CursoRepository;
import paixel.repository.MatriculaRepository;
import paixel.repository.ModuloRepository;
import paixel.repository.UserRepository;
import paixel.servicesImpl.ServiceProgresoAlumnoImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/progreso")
public class ProgresoAlumnoWS {

    @Autowired
    private ServiceProgresoAlumnoImpl serviceProgresoAlumnoImpl;

    private Map<String, Object> response = new HashMap<>();

    @PostMapping("/add")
    public ResponseEntity<?> insert(@RequestBody ProgresoAlumno progresoAlumno) {
        ProgresoAlumno insertarProgreso;
        try {
            insertarProgreso = serviceProgresoAlumnoImpl.save(progresoAlumno);
        } catch (Exception e) {
            response.put("message", "Error al añadir progreso: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProgresoAlumno>(insertarProgreso, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<ProgresoAlumno> progreso;
        try {
            progreso = serviceProgresoAlumnoImpl.findById(id);
            if (!progreso.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "Error al buscar progreso: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(progreso.get(), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ProgresoAlumno> progresos;
        try {
            progresos = serviceProgresoAlumnoImpl.findAll();
        } catch (Exception e) {
            response.put("message", "Error al buscar progresos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ProgresoAlumno>>(progresos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
        	serviceProgresoAlumnoImpl.deleteById(id);
            response.put("message", "El progreso se borró correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Error al borrar progreso: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/registrarVisto")
//    public ResponseEntity<?> registrarModuloVisto(@RequestParam Integer idModulo, @RequestParam Integer idUsuario) {
//        Optional<Modulo> moduloOpt = moduloRepository.findById(idModulo);
//        Optional<User> userOpt = userRepository.findById(idUsuario);
//
//        if (moduloOpt.isPresent() && userOpt.isPresent()) {
//            Modulo modulo = moduloOpt.get();
//            User usuario = userOpt.get();
//
//            Optional<ProgresoAlumno> progresoOpt = serviceProgresoAlumnoImpl.findByModuloIdmoduloAndUsuarioIduser(idModulo, idUsuario);
//            ProgresoAlumno progreso;
//            if (progresoOpt.isPresent()) {
//                progreso = progresoOpt.get();
//            } else {
//                progreso = new ProgresoAlumno();
//                progreso.setModulo(modulo);
//                progreso.setUsuario(usuario);
//            }
//
//            progreso.setCompletado(true);
//            progreso.setFechaVisto(LocalDate.now());
//            serviceProgresoAlumnoImpl.save(progreso);
//
//         // Verificar si todos los módulos del curso están completos
//            Curso curso = modulo.getCurso();
//            List<Modulo> modulos = moduloRepository.findByCurso_Idcurso(curso.getIdcurso());
//            long count = modulos.stream()
//                    .filter(m -> serviceProgresoAlumnoImpl.findByModuloIdmoduloAndUsuarioIduser(m.getIdmodulo(), idUsuario)
//                            .map(ProgresoAlumno::isCompletado).orElse(false))
//                    .count();
//            if (count == modulos.size()) {
//                Optional<Matricula> matriculaOpt = matriculaRepository.findByUserIdAndCursoId(idUsuario, curso.getIdcurso());
//                if (matriculaOpt.isPresent()) {
//                    Matricula matricula = matriculaOpt.get();
//                    matricula.setDiplomaOtorgado(true);
//                    matriculaRepository.save(matricula);
//                }
//            }
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.badRequest().body("Modulo o Usuario no encontrado");
//        }
//    }
}