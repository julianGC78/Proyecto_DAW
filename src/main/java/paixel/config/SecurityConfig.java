package paixel.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import paixel.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;


	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authRequest -> authRequest
	                .requestMatchers("/usuario/findAll", "/workshop/findAll", "/curso/findAll", "/curso/findById/**", "/usuarioCurso/findById/**", "/usuarioCurso/findByUserIdAndCursoId/**").permitAll()
	                .requestMatchers("/matricula/pagar/**", "/modulos/byCurso/**").authenticated()
	                .requestMatchers("/modulo/findById/**", "/matricula/pagar/**", "/progreso/iniciar-video/**","/pregunta/findById/**").hasAnyAuthority("USER", "ADMIN")
	                .requestMatchers("/usuario/update/**", "/usuario/delete/**", "/usuario/add/**","/pregunta/add/**","pregunta/byModulo/**","/pregunta/update/**").hasAnyAuthority("USER", "ADMIN")
	                .requestMatchers("/workshop/add/**", "/modulo/add/**","/curso/preguntas-por-curso/**").hasAnyAuthority("ADMIN")
	                .requestMatchers("/workshop/update/**", "/modulo/update/**", "/curso/update/**").hasAnyAuthority("ADMIN")
	                .requestMatchers("/curso/add/**", "/curso/findByAll/**", "/curso/findById/**","/curso/delete/**").hasAnyAuthority("ADMIN")
	                .requestMatchers("/usuario/findById/**", "/docente/findAll/**", "/docente.delete/**", "/workshop.findAll/**").hasAnyAuthority("USER", "ADMIN")
	                .requestMatchers("/auth/**").permitAll()
	                .anyRequest().authenticated())
	            .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	    }

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500")); 
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

}
