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
@Table(name = "cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcurso;
	
	@Column(unique = true)
	private String titulo;
	
	 @Column(length = 500) 
	private String descripcion;
	 
	private String recurso;
	
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "iddocente")
    private Docente docente;
	
//	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
//	private List<PreguntaVO> preguntausuarios;
//
//	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
//	private List<ModuloVO> modulo;
//
//	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
//	private List<MatriculaVO> matriculausuario;

//	public CursoVO(String titulo, String descripcion, List<PreguntaVO> preguntausuarios, List<ModuloVO> modulo,
//			List<MatriculaVO> matriculausuario, UsuarioVO usuario) {
//		super();
//		this.titulo = titulo;
//		this.descripcion = descripcion;
//		this.preguntausuarios = preguntausuarios;
//		this.modulo = modulo;
//		this.matriculausuario = matriculausuario;
//		this.usuario = usuario;
//	}

}
