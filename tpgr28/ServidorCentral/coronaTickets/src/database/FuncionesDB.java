package database;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Funciones
 *
 */
@Entity

public class FuncionesDB implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true, nullable=false)
	private String nombre;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_alta;
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private EspectaculosDB id_espectaculo;
    @ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private ArtistasDB id_artista;

	private static final long serialVersionUID = 1L;

	public FuncionesDB() {
		super();
	}
	
	public FuncionesDB(String nombre, Date fecha, Date fecha_alta, EspectaculosDB esp, ArtistasDB art) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.fecha_alta = fecha_alta;
		this.id_espectaculo = esp;
		this.id_artista = art;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public Date getFecha_alta() {
		return this.fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}   
	public EspectaculosDB getId_espectaculo() {
		return this.id_espectaculo;
	}

	public void setId_espectaculo(EspectaculosDB id_espectaculo) {
		this.id_espectaculo = id_espectaculo;
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArtistasDB getId_artista() {
		return id_artista;
	}
	public void setId_artista(ArtistasDB id_artista) {
		this.id_artista = id_artista;
	}
   
}
