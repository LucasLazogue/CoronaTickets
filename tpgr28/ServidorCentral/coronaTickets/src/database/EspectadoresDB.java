package database;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EspectadoresDB
 *
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class EspectadoresDB extends UsuariosDB {
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Collection<RegistrosDB> regs;
	
	private static final long serialVersionUID = 1L;
	
	public EspectadoresDB() {
		super();
	}
	
    public EspectadoresDB(String nickName, String email, String nombre, String apellido, Date fecha, Collection<RegistrosDB> regs) {
	    super(nickName, email, nombre, apellido, fecha, "Espectador");
	    this.regs = regs;
	}
	
	

}
