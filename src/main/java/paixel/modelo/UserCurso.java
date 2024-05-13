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
@Table(name = "usuarios_cursos", uniqueConstraints = { @UniqueConstraint(columnNames = { "iduser", "idcurso" }) })
public class UserCurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusercurso;
	private LocalDate fecha;
	private String estado;
	private boolean diploma;
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User usuario;
	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	public UserCurso(LocalDate fecha, String estado, boolean diploma, User usuario,
			Curso curso) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.diploma = diploma;
		this.usuario = usuario;
		this.curso = curso;
	}

}
