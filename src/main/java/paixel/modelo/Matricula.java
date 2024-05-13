package paixel.modelo;

import java.sql.Date;
import java.time.LocalDate;

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
@Table(name="matriculas")
public class Matricula {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;
	@ManyToOne
	@JoinColumn(name = "iduser") // Asegúrate de que esto refleja el nombre correcto de la columna en la base de datos.
	private User user;    
    private Boolean pagado = false;
    private LocalDate fechaPago;
}
