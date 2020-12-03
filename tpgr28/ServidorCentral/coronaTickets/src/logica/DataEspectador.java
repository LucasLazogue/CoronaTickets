package logica;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataEspectador extends DataUsuario {

  private String[] funciones;
  private String[] paquetes;
  private String[] espectaculosFavoritos;

  public DataEspectador() {}
  
  /**
  * OA po OA lala OA teletubie!.
  */
  public DataEspectador(String nickname, String nombre, String apellido, String email,
      LocalDateTime fecha, String[] funciones, String urlImg, String[] nomUsuariosSiguiendo,
      String[] nomUsuariosSeguidores, String[] espectaculosFavoritos) {
    super(nickname, nombre, apellido, email, fecha, urlImg, nomUsuariosSiguiendo,
        nomUsuariosSeguidores);
    this.funciones = funciones;
    this.paquetes = null;
    this.espectaculosFavoritos = espectaculosFavoritos;
  }

  /**
  * OA po OA lala OA teletubie!.
  */
  public DataEspectador(String nickname, String nombre, String apellido, String email,
      LocalDateTime fecha, String[] funciones, String[] paquetes, String urlImg,
      String[] nomUsuariosSiguiendo, String[] nomUsuariosSeguidores, String[] espectaculosFavoritos) {
    super(nickname, nombre, apellido, email, fecha, urlImg, nomUsuariosSiguiendo,
        nomUsuariosSeguidores);
    this.funciones = funciones;
    this.paquetes = paquetes;
    this.espectaculosFavoritos = espectaculosFavoritos;
  }

  public String[] getFunciones() {
    return this.funciones;
  }

  public String[] getPaquetes() {
    return this.paquetes;
  }

  public String[] getEspectaculosFavoritos() {
    return espectaculosFavoritos;
  }

}
