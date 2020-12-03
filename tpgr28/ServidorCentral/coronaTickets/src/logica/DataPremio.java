package logica;

import javax.xml.bind.annotation.XmlAccessorType;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPremio {

  private String descripcion;
  private int cantPremiosPorFuncion;
  private String funcion;
  private String espectaculo;
  private Date fecha;

  public DataPremio() {}
  
  /**
  * Constructor del DataPremio
  */
  public DataPremio(String descripcion, int cantPremiosPorFuncion) {
    this.descripcion = descripcion;
    this.cantPremiosPorFuncion = cantPremiosPorFuncion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public int getCantPremiosPorFuncion() {
    return cantPremiosPorFuncion;
  }

  public String getFuncion() {
    return funcion;
  }

  public void setFuncion(String funcion) {
    this.funcion = funcion;
  }

  public String getEspectaculo() {
    return espectaculo;
  }

  public void setEspectaculo(String espectaculo) {
    this.espectaculo = espectaculo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

}

  
