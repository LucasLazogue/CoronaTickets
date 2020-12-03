package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Plataforma {
  private String nombre;

  private String descripcion;

  private String url;

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setNombre(String value) {
    this.nombre = value;
  }

  public String getNombre() {
    return this.nombre;
  }
  
  /**
   * Plataforma.
   */
  public Plataforma(String nombre, String url, String descripcion) {
    this.nombre = nombre;
    this.url = url;
    this.descripcion = descripcion;
    this.espectaculo = new HashMap<String, Espectaculo>();
  }


  private Map<String, Espectaculo> espectaculo;

  /**
   * Espectaculo.
   */
  public Espectaculo[] getEspectaculos() {
    if (this.espectaculo.isEmpty()) {
      return null;
    } else {
      Collection<Espectaculo> epects = espectaculo.values();
      Object[] obj = epects.toArray();
      Espectaculo[] espectaculos =  new Espectaculo[obj.length];
      for (int i = 0; i < obj.length; i++) {
        espectaculos[i] = (Espectaculo) obj[i];
      }
      return espectaculos;
    }
  }

  public Espectaculo obtenerEspectaculo(String nombre) {
	return (Espectaculo) espectaculo.get(nombre);
  }

  public void agregarEspectaculo(Espectaculo esp) {
    String nombre = esp.getNombre();
    espectaculo.put(nombre, esp);
  }

  /**
   * Listar espectaculos.
   */
  public String[] listarEspectaculos() {
    //Retornar null si no hay espectáculos
    if (this.espectaculo.isEmpty()) { 
      return null;
    }
    //Si hay espectáculos, generar lista de STRINGS de nombres y retornarla
    Collection<Espectaculo> colE = espectaculo.values();
    Object[] objE = colE.toArray();
    //Determinar cantidad de espectaculos ACEPTADOS
    int cant = 0;
    for (int i = 0; i < objE.length; i++) {
      if (((Espectaculo) objE[i]).getEstado() == EstadoEspectaculo.Aceptado) {
        cant++;
      }
    }
    //Retornar null si no hay espectaculos EN ESTADO ACEPTADO
    if (cant == 0) {
      return null;
    }
    //Crear res y retornarlo
    String[] res = new String[cant];
    int jota = 0;
    for (int i = 0; i < objE.length; i++) {
      Espectaculo esp = (Espectaculo) objE[i];
      if (esp.getEstado() == EstadoEspectaculo.Aceptado) {
        res[jota] = esp.getNombre();
        jota++;
      }
    }
    return res;
  }

  /**
   * Listar espectaculos ingresados.
   */
  public String[] listarEspectaculosEstadoIngresado() {
    //Retornar null si no hay espectáculos
    if (this.espectaculo.isEmpty()) {
      return null;
    }
    //Si hay espectáculos en estado ingresado, generar lista de STRINGS de nombres
    Collection<Espectaculo> colE = espectaculo.values();
    Object[] objE = colE.toArray();
    //Determinar cantidad de espectaculos con estado INGRESADO
    int cant = 0;
    for (int i = 0; i < objE.length; i++) {
      if (((Espectaculo) objE[i]).getEstado() == EstadoEspectaculo.Ingresado) {
        cant++;
      }
    }
    //Retornar null si no hay espectaculos EN ESTADO INGRESADO
    if (cant == 0) {
      return null;
    }
    //Crear res y retornarlo
    String[] res = new String[cant];
    int jota = 0;
    for (int i = 0; i < objE.length; i++) {
      Espectaculo esp = (Espectaculo) objE[i];
      if (esp.getEstado() == EstadoEspectaculo.Ingresado) {
        res[jota] = esp.getNombre();
        jota++;
      }
    }
    return res;
  }

}
