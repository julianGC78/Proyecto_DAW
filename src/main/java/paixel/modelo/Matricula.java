package paixel.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "matriculas")
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPago;

	@ManyToOne
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	private User user;

	private boolean pagado;
	private LocalDate fechaPago;

	@Column(nullable = false)
	private boolean diplomaOtorgado = false;

	public Matricula(User usuario1, LocalDate of, boolean b, boolean c) {
		// TODO Auto-generated constructor stub
	}
}
