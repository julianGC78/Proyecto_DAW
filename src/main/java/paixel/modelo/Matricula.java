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
@Table(name = "matriculas", uniqueConstraints = { @UniqueConstraint(columnNames = { "idusuario", "idcurso" }) })
public class Matricula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmatricula;
	private LocalDate fecha;
	private boolean pago;
	private boolean estado;
	private boolean diploma;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private User usuario;
	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	public Matricula(LocalDate fecha, boolean pago, boolean estado, boolean diploma, User usuario,
			Curso curso) {
		super();
		this.fecha = fecha;
		this.pago = pago;
		this.estado = estado;
		this.diploma = diploma;
		this.usuario = usuario;
		this.curso = curso;
	}

}
