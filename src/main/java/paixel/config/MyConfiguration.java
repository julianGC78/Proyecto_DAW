package paixel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyConfiguration {

	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Puedes ajustar este patrón para ser más específico si es necesario.
                        .allowedOrigins("http://127.0.0.1:5500") // Cambia esto por el origen desde el que haces las solicitudes
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Los métodos que quieres permitir
                        .allowedHeaders("*") // Los encabezados permitidos
                        .allowCredentials(true);
            }
        };
    }
}
