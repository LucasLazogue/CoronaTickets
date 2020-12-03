package logica;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DataRegistro {
  private Date fecha;
  private int costo;
  private String funcion;
  private String[] canjeados;
  private String paquete;
  
  public DataRegistro() {}

  /**
  * OA po OA lala OA teletubie!.
  */
  public DataRegistro(Date fecha, int costo, String funcion, String[] canjeados,
      String paquete) {
    super();
    this.fecha = fecha;
    this.costo = costo;
    this.funcion = funcion;
    this.canjeados = canjeados;
    this.paquete = paquete;
  }

  public Date getFecha() {
    return fecha;
  }

  public int getCosto() {
    return costo;
  }

  public String getFuncion() {
    return funcion;
  }

  public String[] getCanjeados() {
    return canjeados;
  }

  public String getPaquete() {
    return paquete;
  }	

}
