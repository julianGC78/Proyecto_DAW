package paixel.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import paixel.jwt.JwtService;
import paixel.modelo.Matricula;
import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.repository.MatriculaRepository;
import paixel.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MatriculaRepository matriculaRepository;

    public AuthReponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthReponse.builder()
            .token(token)           
            .build();

    }

  
    public AuthReponse register(RegisterRequest request) {
        // Verificar si ya existe un usuario con el mismo email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("Error: El correo electrónico ya está en uso.", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
            .genero(request.getGenero())
            .username(request.getUsername())
            .apellidos(request.getApellidos())
            .password(passwordEncoder.encode(request.getPassword()))
            .email(request.getEmail())
            .role(Role.USER)
            .build();

        user = userRepository.save(user); // Guardar el usuario y recibir la entidad guardada

        // Crear y guardar la matrícula para el nuevo usuario
        Matricula matricula = new Matricula();
        matricula.setUser(user);  
        matricula.setPagado(false); 
        matricula.setFechaPago(null); 
        matriculaRepository.save(matricula); 

        return AuthReponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }
    
    
//    public AuthReponse register(RegisterRequest request) {
//    	// Verificar si ya existe un usuario con el mismo email
//        if (userRepository.findByEmail(request.getEmail()).isPresent()){
//        	throw new CustomException("Error: El correo electrónico ya está en uso.", HttpStatus.BAD_REQUEST);
//        }
//        User user = User.builder()
//			.genero(request.getGenero()) 
//	        .username(request.getUsername())
//            .apellidos(request.getApellidos()) 
//            .password(passwordEncoder.encode( request.getPassword()))
//            .email(request.getEmail())
//            .role(Role.USER)
//            .build();
//
//        userRepository.save(user);
//
//        return AuthReponse.builder()
//            .token(jwtService.getToken(user))
//            .build();
//        
//    }
    
    public class CustomException extends RuntimeException {
        private HttpStatus httpStatus;

        public CustomException(String message, HttpStatus httpStatus) {
            super(message);
            this.httpStatus = httpStatus;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }

}
