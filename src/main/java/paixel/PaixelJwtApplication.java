package paixel;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PaixelJwtApplication {
	
	  @PostConstruct
	    public void init(){
	        // Establece la zona horaria predeterminada a UTC
	        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	    }

	public static void main(String[] args) {
		SpringApplication.run(PaixelJwtApplication.class, args);
	}
    
}
