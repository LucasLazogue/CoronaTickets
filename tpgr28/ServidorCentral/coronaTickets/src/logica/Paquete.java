package logica;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Paquete {

  private String nombre;
  private String descripcion;
  private Date inicioVigencia;
  private Date finVigencia;
  private int descuento;
  private Date fechaDeAlta;
  private Map<String, Espectaculo> espectaculos;
  private String url;

  /**
   * Listar categorias.
   */
  public String[] listarCategorias() {
    Map<String, String> generos = new HashMap<String, String>();
    for (Map.Entry<String, Espectaculo> entry: espectaculos.entrySet()) {
      if (entry.getValue().getEstado() == EstadoEspectaculo.Aceptado) {
    	String[] aux = entry.getValue().listarCategorias();
        if (aux != null) {
          for (int i = 0; i < aux.length; i++) {
            if (generos.get(aux[i]) == null) {
              generos.put(aux[i], aux[i]);
            }
          }
	    }
      }
    }
    if (generos.isEmpty()) {
      return null;
    } else {
      Collection<String> gen = generos.values();
      Object[] obj = gen.toArray();
      String[] genero = new String[obj.length];
      for (int i = 0; i < obj.length; i++) {
        genero[i] = (String) obj[i];
      }
      return genero;
    }
  }

  /**
   * Paquete.
   */
  public Paquete(String nombre, String descripcion, Date inicioVigencia, Date finVigencia,
      int descuento, Date fechaDeAlta) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.inicioVigencia = inicioVigencia;
    this.finVigencia = finVigencia;
    this.descuento = descuento;
    this.setFechaDeAlta(fechaDeAlta);
    this.espectaculos = new HashMap<String, Espectaculo>();
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrl() {
    return this.url;
  }

  public void setNombre(String value) {
    this.nombre = value;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setDescripcion(String value) {
    this.descripcion = value;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public void setInicioVigencia(Date value) {
    this.inicioVigencia = value;
  }

  public Date getInicioVigencia() {
    return this.inicioVigencia;
  }

  public void setFinVigencia(Date value) {
    this.finVigencia = value;
  }

  public Date getFinVigencia() {
    return this.finVigencia;
  }

  public void setDescuento(int value) {
    this.descuento = value;
  }

  public int getDescuento() {
    return this.descuento;
  }

  public Date getFechaDeAlta() {
    return fechaDeAlta;
  }

  public void setFechaDeAlta(Date fechaDeAlta) {
    this.fechaDeAlta = fechaDeAlta;
  }

  /**
   * Get espectaculos.
   */
  public Espectaculo[] getEspectaculos() {
    if (this.espectaculos.isEmpty()) {
      return null;
    } else {
      Collection<Espectaculo> esp = espectaculos.values();
      Object[] obj = esp.toArray();
      Espectaculo[] espectaculos = new Espectaculo[obj.length];
      for (int i = 0; i < obj.length; i++) {
        espectaculos[i] = (Espectaculo) obj[i];
      }
      return espectaculos;
    }
  }

  public void addEspectaculo(Espectaculo esp) {
    espectaculos.put(esp.getNombre(), esp);
  }

  public Espectaculo getEspectaculo(String nombre) {
    return (Espectaculo) espectaculos.get(nombre);
  }
}
