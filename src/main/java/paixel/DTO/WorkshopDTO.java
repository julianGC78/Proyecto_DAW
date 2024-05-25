package paixel.DTO;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import paixel.modelo.User;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WorkshopDTO {
	 private int idworkshop;
	    private String contenido;
	    private String descripcion;
	    private LocalDate fecha;
	    private String username;
	    private String apelluidos;
	    private int idusuario;
		public WorkshopDTO(String contenido, String descripcion, LocalDate fecha, String username, String apelluidos) {
			super();
			this.contenido = contenido;
			this.descripcion = descripcion;
			this.fecha = fecha;
			this.username = username;
			this.apelluidos = apelluidos;
		}
	    
		
}
