package paixel.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "progreso_alumno")
public class ProgresoAlumno {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idProgreso;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "idusuario", referencedColumnName = "iduser")
	    private User usuario;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo")
	    private Modulo modulo;

	    @Column(name = "fecha_visto", nullable = true)
	    private LocalDate fechaVisto;

	    @Column(nullable = false)
	    private boolean completado;
}
