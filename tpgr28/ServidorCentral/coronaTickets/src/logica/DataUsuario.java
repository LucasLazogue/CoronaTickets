package logica;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataUsuario {
  private String nickname;
  private String nombre;
  private String apellido;
  private String email;
  private LocalDateTime fecha;
  private Date fechaDate;
  private String urlImg;
  private String[] nickUsuariosSiguiendo;
  private String[] nickUsuariosSeguidores;

  public DataUsuario() {}
  
  /**
  * Constructor de DataUsuario.
  */
  public DataUsuario(String nickname, String nombre, String apellido, String email, LocalDateTime
      fecha, String urlImg, String[] nickUsuariosSiguiendo, String[] nickUsuariosSeguidores) {
    this.nickname = nickname;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.fecha = fecha;
    this.urlImg = urlImg;
    this.nickUsuariosSiguiendo = nickUsuariosSiguiendo;
    this.nickUsuariosSeguidores = nickUsuariosSeguidores;
  }

  public String getNickname() {
    return nickname;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getEmail() {
    return email;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }
  
  public Date getFechaDate() {
	  return fechaDate;
  }

  public void convertFecha() {
	  this.fechaDate = Date.from(this.fecha.atZone(ZoneId.systemDefault()).toInstant());
  }
  
  public String getUrlImg() {
    return urlImg;
  }

  public String[] getNickUsuariosSiguiendo() {
    return nickUsuariosSiguiendo;
  }

  public String[] getNickUsuariosSeguidores() {
    return nickUsuariosSeguidores;
  }
}
