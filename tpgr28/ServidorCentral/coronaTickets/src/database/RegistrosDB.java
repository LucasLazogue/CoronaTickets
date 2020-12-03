package database;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import logica.Funcion;
import logica.Paquete;
import logica.Registro;

/**
 * Entity implementation class for Entity: Funciones
 *
 */
@Entity

public class RegistrosDB implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private int costo;	  	  	  
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private FuncionesDB id_funcion;
    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    private UsuariosDB id_usuario;

	private static final long serialVersionUID = 1L;

	public RegistrosDB() {
		super();
	}   
	
	public RegistrosDB(Date fecha, int costo, FuncionesDB fun, UsuariosDB usu) {
		this.fecha = fecha;
		this.costo = costo;
		this.id_funcion = fun;
		this.id_usuario = usu;
	}
	
	public int getCosto() {
		return this.costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
  
	public FuncionesDB getId_funcion() {
		return this.id_funcion;
	}

	public void setId_funcion(FuncionesDB id_funcion) {
		this.id_funcion = id_funcion;
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setIdUsuario(UsuariosDB user) {
		this.id_usuario= user;
	}   
	public UsuariosDB getIdUsuario() {
		return this.id_usuario;
	}	
	
	
   
}