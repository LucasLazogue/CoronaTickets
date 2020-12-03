package logica;

import java.util.Date;

public class Compra {

  private Date fecha;
  private Paquete paquete;

  public Compra(Date fecha, Paquete paquete) {
    this.fecha = fecha;
    this.paquete = paquete;
  }

  public void setFecha(Date value) {
    this.fecha = value;
  }

  public Date getFecha() {
    return this.fecha;
  }

  public void setPaquete(Paquete value) {
    this.paquete = value;
  }

  public Paquete getPaquete() {
    return this.paquete;
  }
}
