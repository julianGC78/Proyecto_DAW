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
@Table(name = "preguntas", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "idusuario", "idcurso", "fecha" }) })
public class Pregunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpregunta;
	private String contenido;
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private User usuario;
	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	public Pregunta(String contenido, LocalDate fecha, User usuario, Curso curso) {
		super();
		this.contenido = contenido;
		this.fecha = fecha;
		this.usuario = usuario;
		this.curso = curso;
	}

}
