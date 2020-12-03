package logica;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Registro {
  private Date fecha;
  private int costo;
  private Funcion funcion;
  private Set<Registro> canjeados;
  private Paquete paquete;
  private boolean premio;
  private String usuario;

  /**
   * Registro.
   */
  public Registro(Date fecha, int costo, Funcion funcion) {
    super();
    this.fecha = fecha;
    this.costo = costo;
    this.funcion = funcion;
    this.canjeados = new HashSet<Registro>();
    this.paquete = null;
    this.premio = false;
  }

  /**
   * Registro.
   */
  public Registro(Date fecha, int costo, Funcion funcion, Paquete paquete) {
    super();
    this.fecha = fecha;
    this.costo = costo;
    this.funcion = funcion;
    this.canjeados = new HashSet<Registro>();
    this.paquete = paquete;
  }

  /**
   * Registro.
   */
  public Registro(Date fecha, int costo, Funcion funcion, Set<Registro> canjeados) {
    super();
    this.fecha = fecha;
    this.costo = costo;
    this.funcion = funcion;
    this.canjeados = canjeados;
    this.paquete = null;
  }


  public Date getFecha() {
    return fecha;
  }
  
  public int getCosto() {
    return costo;
  }
  
  public Funcion getFuncion() {
    return funcion;
  }
  
  public Set<Registro> getCanjeados() {
    return canjeados;
  }

  public void setPaquete(Paquete paq) {
    this.paquete = paq;
  }

  public Paquete getPaquete() {
    return this.paquete;
  }
  
  public void setPremio() {
	  this.premio = true;
  }
  
  public Boolean getPremio() {
	  return this.premio;
  }
  
  public String getUsuario() {
	  return this.usuario;
  }
  
  public void setUsuario(String nom) {
	  this.usuario = nom;
  }
  	
}