package paixel.modelo;

import java.time.LocalDateTime;

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
@Table(name = "workshops", uniqueConstraints = { @UniqueConstraint(columnNames = { "idusuario", "fecha" }) })
public class Workshop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idworkshop;
	private String contenido;
	private String descripcion;
	private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private User usuario;
	public Workshop(String contenido, String descripcion, LocalDateTime fecha, User usuario) {
		super();
		this.contenido = contenido;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.usuario = usuario;
	}


}
