package logica;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataFuncion {

  private String nombre;
  private Date fechaHoraInicio;
  private String[] artistas;
  private Date fechaAlta;
  private String espectaculo;
  private String urlimg;
  private boolean sorteado;

  public DataFuncion() {}
  
  /**
  * OA po OA lala OA teletubie!.
  */
  public DataFuncion(String nombre, Date fechaHI, String[] artistas, Date fechaA,
      String espectaculo, String urlimg, boolean sorteado) {
    this.nombre = nombre;
    this.fechaHoraInicio = fechaHI;
    this.artistas = artistas;
    this.fechaAlta = fechaA;
    this.espectaculo = espectaculo;
    this.urlimg = urlimg;
    this.sorteado = sorteado;
  }

  public String getNombre() {
    return this.nombre;
  }

  public Date getFechaHoraInicio() {
    return this.fechaHoraInicio;
  }

  public String[] getArtistas() {
    return this.artistas;
  }

  public Date getFechaAlta() {
    return this.fechaAlta;
  }

  public String getEspectaculo() {
    return this.espectaculo;
  }

  public String getUrlimg() {
    return this.urlimg;
  }
  
  public boolean sorteoRealizado() {
    return this.sorteado;
  }
}
