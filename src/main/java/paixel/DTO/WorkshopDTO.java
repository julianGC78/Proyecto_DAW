package paixel.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WorkshopDTO {
	 private int idworkshop;
	    private String contenido;
	    private String descripcion;
	    private LocalDate fecha;
	    private String username; // Nombre de usuario para mostrar

	    public WorkshopDTO(int idworkshop, String contenido, String descripcion, LocalDate fecha, String username) {
	        this.idworkshop = idworkshop;
	        this.contenido = contenido;
	        this.descripcion = descripcion;
	        this.fecha = fecha;
	        this.username = username;
	    }

}
