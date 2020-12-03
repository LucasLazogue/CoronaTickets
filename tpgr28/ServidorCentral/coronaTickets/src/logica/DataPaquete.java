package logica;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPaquete {

  private String nombre;
  private String descripcion;
  private Date inicioVigencia;
  private Date finVigencia;
  private int descuento;
  private Date fechaDeAlta;
  private String[] espectaculos;
  private String urlImagen;
  private String[] categorias;

  public DataPaquete() {}
  
  /**
  * OA po OA lala OA teletubie!.
  */
  public DataPaquete(String nombre, String descripcion, Date inicioVigencia, Date finVigencia,
      int descuento, Date fechaDeAlta, String[] espectaculos, String url, String[] categorias) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.inicioVigencia = inicioVigencia;
    this.finVigencia = finVigencia;
    this.descuento = descuento;
    this.fechaDeAlta = fechaDeAlta;
    this.espectaculos = espectaculos;
    this.urlImagen = url;
    this.categorias = categorias;
  }

  public String[] getGeneros() {
    return this.categorias;
  }

  public String getUrlImagen() {
    return this.urlImagen;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public Date getInicioVigencia() {
    return this.inicioVigencia;
  }

  public Date getFinVigencia() {
    return this.finVigencia;
  }

  public int getDescuento() {
    return this.descuento;
  }

  public Date getFechaDeAlta() {
    return fechaDeAlta;
  }

  public String[] getEspectaculos() {
    return espectaculos;
  }
}
