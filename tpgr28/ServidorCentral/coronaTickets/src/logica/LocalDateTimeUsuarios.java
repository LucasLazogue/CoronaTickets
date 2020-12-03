package logica;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeUsuarios {

	private LocalDateTime fecha;
	
	public LocalDateTimeUsuarios() {}
	
	public LocalDateTimeUsuarios(Date fecha) {
		this.fecha = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public LocalDateTime getFecha() {
		return this.fecha;
	}
	
}
