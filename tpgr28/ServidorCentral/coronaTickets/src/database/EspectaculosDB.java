package database;

import java.io.Serializable;
import java.lang.Long;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Espectaculo
 *
 */
@Entity

public class EspectaculosDB implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique=true, nullable=false)
  private String nombre;
  private String descripcion;
  private int duracion;
  private int costo;
  @Temporal(TemporalType.TIMESTAMP)
  private Date fecha_alta;
  @Temporal(TemporalType.TIMESTAMP)
  private Date fecha_finalizacion;
  private String url;
  private String nombre_plataforma;
  @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
  private Collection<FuncionesDB> funciones;

  public EspectaculosDB() {
    super();
  }
  
  public EspectaculosDB(String nombre, String descripcion, int duracion, int costo,
		  Date fechaRegistro, Date fechaFinalizacion, String url, String plat) {
	  this.nombre = nombre;
	  this.descripcion = descripcion;
	  this.duracion = duracion;
	  this.costo = costo;
	  this.fecha_alta = fechaRegistro;
	  this.fecha_finalizacion = fechaFinalizacion;
	  this.url = url;
	  this.nombre_plataforma = plat;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getDescripcion() {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public int getDuracion() {
    return this.duracion;
  }
  
  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }
  
  public int getCosto() {
    return this.costo;
  }
  
  public void setCosto(int costo) {
    this.costo = costo;
  }
  
  public Date getFechaAlta() {
    return this.fecha_alta;
  }
  
  public void setFechaAlta(Date fechaAlta) {
    this.fecha_alta = fechaAlta;
  }
  
  public Date getFechaFinalizacion() {
    return this.fecha_finalizacion;
  }
  
  public void setFechaFinalizacion(Date fechaFinalizacion) {
    this.fecha_finalizacion = fechaFinalizacion;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getNombrePlataforma() {
    return this.nombre_plataforma;
  }
  
  public void setNombrePlataforma(String nombrePlataforma) {
    this.nombre_plataforma = nombrePlataforma;
  }
  
  public Collection<FuncionesDB> getFunciones(){
	  return this.funciones;
  }
  
  public void setFunciones(Collection<FuncionesDB> funciones) {
    this.funciones = funciones;
  }
  
}
