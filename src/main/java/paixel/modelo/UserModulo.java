package paixel.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuarios_modulos", uniqueConstraints = { @UniqueConstraint(columnNames = { "idusuario", "idmodulo" }) })
public class UserModulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuariomodulo;
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private User usuario;
	@ManyToOne
	@JoinColumn(name = "idmodulo")
	private Modulo modulo;
	private String estado;
	
	public UserModulo(LocalDate fecha, User usuario, Modulo modulo, String estado) {
		super();
		this.fecha = fecha;
		this.usuario = usuario;
		this.modulo = modulo;
		this.estado = estado;
	}

	
}
