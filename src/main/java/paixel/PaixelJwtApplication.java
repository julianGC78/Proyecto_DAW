package paixel;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import paixel.modelo.Curso;
import paixel.modelo.Docente;
import paixel.modelo.Modulo;
import paixel.modelo.Role;
import paixel.modelo.User;
import paixel.repository.CursoRepository;
import paixel.repository.DocenteRepository;
import paixel.repository.ModuloRepository;
import paixel.repository.UserRepository;
import paixel.servicesImpl.ServiceWorkshopImpl;

@SpringBootApplication
public class PaixelJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaixelJwtApplication.class, args);
	}
    
}
