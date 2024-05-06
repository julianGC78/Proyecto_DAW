package paixel.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "modulos")
public class Modulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modulo;
	@Column(unique = true)
	private String titulo;
	private String descripcion;
	private String recurso;
	private int orden;
	@OneToMany(mappedBy = "modulo", cascade = CascadeType.REMOVE)
	private List<UsuarioModulo> usuario;
	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	public Modulo(String titulo, String descripcion, String recurso, int orden, List<UsuarioModulo> usuario,
			Curso curso) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.recurso = recurso;
		this.orden = orden;
		this.usuario = usuario;
		this.curso = curso;
	}


}