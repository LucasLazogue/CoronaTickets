package logica;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessorType;

import exepciones.EspectaculoNoExisteException;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataEspectaculo {

  private String nombre;
  private String descripcion;
  private int duracion;
  private int minEspectadores;
  private int maxEspectadores;
  private int costo;
  private String url;
  private Date fechaRegistro;
  private String estado;
  private String urlImg;
  private String[] nombresFunciones;
  private String[] nombresPaquetes;
  private String[] nombresCategorias;
  private DataPremio premio;
  private String urlVideo;
  private int cantVecesEspectaculoFavorito;

  public DataEspectaculo() {}
  
  /**
  * Gets the distance between tw points.
  */
  public DataEspectaculo(String nombre, String descripcion, int duracion, int minEspectadores, 
      int maxEspectadores, int costo, String url, Date fechaRegistro, 
      String estado, String urlImg, String[] nombresFunciones, 
      String[] nombresPaquetes, String[] nombresCategorias, DataPremio premio, String urlVideo) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.duracion = duracion;
    this.minEspectadores = minEspectadores;
    this.maxEspectadores = maxEspectadores;
    this.costo = costo;
    this.url = url;
    this.fechaRegistro = fechaRegistro;
    this.estado = estado;
    this.urlImg = urlImg;
    this.nombresFunciones = nombresFunciones;
    this.nombresPaquetes = nombresPaquetes;
    this.nombresCategorias = nombresCategorias;
    this.premio = premio;
    this.urlVideo = urlVideo;
    try {
      this.cantVecesEspectaculoFavorito = Fabrica.getInstancia().getIEspectaculoController().
          cantVecesEspectaculoFavorito(nombre);
    } catch (EspectaculoNoExisteException e) {
      e.printStackTrace();
    }
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public int getDuracion() {
    return duracion;
  }

  public int getMinEspectadores() {
    return minEspectadores;
  }

  public int getMaxEspectadores() {
    return maxEspectadores;
  }

  public int getCosto() {
    return costo;
  }

  public String getUrl() {
    return url;
  }

  public Date getFechaRegistro() {
    return fechaRegistro;
  }

  public String getEstado() {
    return estado;
  }

  public String getUrlImg() {
    return urlImg;
  }
  
  public String[] getNombresFunciones() {
    return nombresFunciones;
  }

  public String[] getNombresPaquetes() {
    return nombresPaquetes;
  }

  public String[] getNombresCategorias() {
    return nombresCategorias;
  }

  public DataPremio getPremio() {
    return premio;
  }

  public String getUrlVideo() {
    return urlVideo;
  }

  public int getCantVecesEspectaculoFavorito() {
    return cantVecesEspectaculoFavorito;
  }
}
