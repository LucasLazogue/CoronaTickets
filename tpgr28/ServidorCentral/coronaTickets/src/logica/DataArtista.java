package logica;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataArtista extends DataUsuario {

  private String descripcion;
  private String biografia;
  private String web;
  private String[] espectaculos;

  public DataArtista() {}
  
  /**
  * Gets the distance between tw points.
  */
  public DataArtista(String nickname, String nombre, String apellido, String email, 
      LocalDateTime fecha, String descripcion, String biografia, String web, String[] espectaculos, 
      String urlImg, String[] nomUsuariosSiguiendo, String[] nomUsuariosSeguidores) {
    super(nickname, nombre, apellido, email, fecha, urlImg, nomUsuariosSiguiendo, 
        nomUsuariosSeguidores);
    this.descripcion = descripcion;
    this.biografia = biografia;
    this.web = web;
    this.espectaculos = espectaculos;
  }
  
  public String getDescripcion() {
    return descripcion;
  }

  public String getBiografia() {
    return biografia;
  }

  public String getWeb() {
    return web;
  }

  public String[] getEspectaculos() {
    return this.espectaculos;
  }
}
