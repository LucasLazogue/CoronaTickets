package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Categoria {

  private String nombreCategoria;
  private Map<String, Espectaculo> espectaculos;

  public Categoria(String nombreCategoria) {
    this.nombreCategoria = nombreCategoria;
    espectaculos = new HashMap<String, Espectaculo>();
  }

  public String getNombre() {
    return this.nombreCategoria;
  }

  public Espectaculo getEspectaculo(String nombreEspectaculo) {
    return this.espectaculos.get(nombreEspectaculo);
  }

  /**
  * Gets the distance between tw points.
  */
  public void addEspectaculo(Espectaculo esp) {
    String nombre = esp.getNombre();
    if (!this.espectaculos.containsKey(nombre)) {
      this.espectaculos.put(nombre, esp);
    }
  }

  /**
  * Gets the distance between tw points.
  */
  public String[] listarEspectaculos() {
    if (this.espectaculos.isEmpty()) {
      return null;
    } else {
      Collection<Espectaculo> colE = espectaculos.values();
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
      //Crear resultado y devolverlo
      String[] resultado = new String[cant];
      int jota = 0;
      for (int i = 0; i < colE.size(); i++) {
        Espectaculo esp = (Espectaculo) objE[i];
        if (esp.getEstado() == EstadoEspectaculo.Aceptado) {
          resultado[jota] = esp.getNombre();
          jota++;
        }
      }
      return resultado;
    }
  }
}
