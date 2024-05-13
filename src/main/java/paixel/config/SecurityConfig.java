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

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
//				.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(authRequest -> authRequest
//						.requestMatchers("/usuario/findAll","/workshop/findAll","/curso/findAll").permitAll()
//						.requestMatchers("/usuario/update/**", "/usuario/delete/**").hasAuthority("USER")
//						.requestMatchers("/curso/findById/**").hasAuthority("USER") // Asegúrate de que la ruta esté correcta
//						.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
//				.sessionManagement(
//						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authenticationProvider(authProvider)
//				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authRequest -> authRequest
	                    .requestMatchers("/usuario/findAll","/workshop/findAll","/curso/findAll", "/curso/findById/**","/usuarioCurso/findById/**","/usuarioCurso/findByUserIdAndCursoId/**").permitAll()  // Permitir acceso público a los detalles del curso
	                    .requestMatchers("/matricula/pagar/**").permitAll()
	                    .requestMatchers("/usuario/update/**", "/usuario/delete/**").hasAuthority("USER")
	                    .requestMatchers("/auth/**").permitAll()
	                    .anyRequest().authenticated())
	            .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authenticationProvider(authProvider)
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500")); // Debes cambiar "*" por los orígenes
																					// específicos en producción
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
