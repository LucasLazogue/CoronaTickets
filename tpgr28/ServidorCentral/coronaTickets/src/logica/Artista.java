package logica;



//import Espectaculo;
//import Funcion;
//import Usuario;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Artista extends Usuario {
  
  private String descripcion;
  private String web;
  private String biografia;
  private Map<String, Espectaculo> espectaculos;

  /**
  * Gets the distance between tw points.
  */
  public Artista(String nickName, String email, String nombre, String apellido, 
      LocalDateTime fecha, String descripcion, String biografia, String web, 
      String password, String urlImg) {
    super(nickName, email, nombre, apellido, fecha, password, urlImg);
    this.descripcion = descripcion;
    this.biografia = biografia;
    this.web = web;
    this.espectaculos = new HashMap<String, Espectaculo>();
  }


  public void setDescripcion(String value) {
    this.descripcion = value;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public void setBiografia(String value) {
    this.biografia = value;
  }

  public String getBiografia() {
    return this.biografia;
  }

  public void setWeb(String value) {
    this.web = value;
  }
 
  public String getWeb() {
    return this.web;
  }

  public void agregarEspectaculo(Espectaculo esp) {
    String nombre = esp.getNombre();
    espectaculos.put(nombre, esp);
  }

  public Espectaculo getEspectaculo(String nombre) {
    return (Espectaculo) espectaculos.get(nombre);
  }

  public Set<String> getNombresEspectaculos() {
    return this.espectaculos.keySet();
  }

  /**
  * Gets the distance between tw points.
  */
  public Espectaculo[] getEspectaculos() {
    if (this.espectaculos.isEmpty()) {
      return null;
    } else {
      Collection<Espectaculo> espec = this.espectaculos.values();
      Object[] obj = espec.toArray();
      Espectaculo[] esp = new Espectaculo[obj.length];
      for (int i = 0; i < obj.length; i++) {
        esp[i] = (Espectaculo) obj[i];
      }
      return esp;
    }
  }
}
