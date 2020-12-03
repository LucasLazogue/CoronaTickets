package logica;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Usuario {


  private String nickname;
  private String email;
  private String nombre;
  private String apellido;
  private LocalDateTime fecha;
  private String password;
  private String urlImg;
  private Map<String, Usuario> usuariosSiguiendo;
  private Map<String, Usuario> usuariosSeguidores;

  /**
   * Usuario.
   */
  public Usuario(String nickname, String email, String nombre, String apellido, 
      LocalDateTime fecha, String password, String urlImg) {
    this.nickname = nickname;
    this.email = email;
    this.nombre = nombre;
    this.apellido = apellido;
    this.fecha = fecha;
    this.password = password;
    this.urlImg = urlImg;
    this.usuariosSiguiendo = new HashMap<String, Usuario>();
    this.usuariosSeguidores = new HashMap<String, Usuario>();
  }

  public void setNickname(String value) {
    this.nickname = value;
  }

  public String getNickname() {
    return this.nickname;
  }



  public void setNombre(String value) {
    this.nombre = value;
  }

  public String getNombre() {
    return this.nombre;
  }



  public void setApellido(String value) {
    this.apellido = value;
  }

  public String getApellido() {
    return this.apellido;
  }



  public void setEmail(String value) {
    this.email = value;
  }

  public void setPassword(String value) {
    this.password = value;
  }

  public void setUrlImg(String value) {
    this.urlImg = value;
  }

  public String getEmail() {
    return this.email;
  }


  public String getPassword() {
    return this.password;
  }

  public void setFecha(LocalDateTime value) {
    this.fecha = value;
  }

  public LocalDateTime getFecha() {
    return this.fecha;
  }

  public String getUrlImg() {
    return this.urlImg;
  }

  public void agregarSeguidor(Usuario seg) {
    String nombre = seg.getNickname();
    usuariosSeguidores.put(nombre, seg);  
  }

  public void quitarSeguidor(Usuario seg) {
    usuariosSeguidores.remove(seg.getNickname());  
  }

  /**
   * Get usuarios.
   */
  public String[] getUsuariosSeguidores() {
    //Retornar null si no tiene seguidores
    if (this.usuariosSeguidores.isEmpty()) { 
      return null;
    }
    //Si lo siguen usuarios, generar lista de STRINGS de nombres y retornarla
    Collection<Usuario> colU = this.usuariosSeguidores.values();
    Object[] objU = colU.toArray();
    String[] res = new String[objU.length];
    for (int i = 0; i < objU.length; i++) {
      res[i] = ((Usuario) objU[i]).getNickname();
    }
    return res;
  }

  public void seguirUsuario(Usuario seg) {
    String nombre = seg.getNickname();
    usuariosSiguiendo.put(nombre, seg);  
  }

  public void dejarDeSeguirUsuario(Usuario seg) {
    usuariosSiguiendo.remove(seg.getNickname());  
  }

  /**
   * Get seguidos.
   */
  public String[] getUsuariosSiguiendo() {
    //Retornar null si no sigue ningun usuario
    if (this.usuariosSiguiendo.isEmpty()) {
      return null;
    }
    //Si sigue usuarios, generar lista de STRINGS de nombres y retornarla
    Collection<Usuario> colU = this.usuariosSiguiendo.values();
    Object[] objU = colU.toArray();
    String[] res = new String[objU.length];
    for (int i = 0; i < objU.length; i++) {
      res[i] = ((Usuario) objU[i]).getNickname();
    }
    return res;
  }
}
