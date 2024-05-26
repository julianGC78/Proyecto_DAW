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
	    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
	    private User user;  // Relación Many-to-One con la entidad User

	    private Boolean pagado;  // Campo booleano para indicar si está pagado
	    private LocalDate fechaPago;  // Fecha de pago
}
