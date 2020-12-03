package database;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

import logica.Espectaculo;

/**
 * Entity implementation class for Entity: EspectadoresDB
 *
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class ArtistasDB extends UsuariosDB {
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Collection<FuncionesDB> funciones;
	
	private static final long serialVersionUID = 1L;
	
	public ArtistasDB() {
		super();
	}
	
  public ArtistasDB(String nickName, String email, String nombre, String apellido, Date fecha) {
    super(nickName, email, nombre, apellido, fecha, "Artista");
    funciones = new ArrayList<FuncionesDB>();
  }
  
  public Collection<FuncionesDB> getFunciones() {
  	return this.funciones;
  }

  public void setFunciones(Collection<FuncionesDB> funcs) {
	this.funciones = funcs;
  }
	
	
	

}
