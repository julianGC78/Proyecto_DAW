package paixel.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer iduser;
    @Basic
    @Column(nullable = false)
    String username;
    String apellidos;
    String password; 
    @Column(unique = true)
    String email;
    String genero;
    String dni;
    @Column(name = "fecha_nacimiento", nullable = true)
    LocalDate fechaNacimiento;
    String localidad;
    boolean matricula;
    @Enumerated(EnumType.STRING) 
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
	public User(String username, String apellidos, String password, String email, String genero, String dni,
			LocalDate fechaNacimiento, String localidad, boolean matricula, Role role) {
		super();
		this.username = username;
		this.apellidos = apellidos;
		this.password = password;
		this.email = email;
		this.genero = genero;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.localidad = localidad;
		this.matricula = matricula;
		this.role = role;
	}
	
	
}
