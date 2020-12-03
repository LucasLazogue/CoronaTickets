package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManejadorPaquetes {

  private Map<String, Paquete> paquetes;
  private static ManejadorPaquetes instancia = null;

  private ManejadorPaquetes() {
    paquetes = new HashMap<String, Paquete>();
  }

  /**
   * Trae el manejador de paquetes.
   */
  public static ManejadorPaquetes getManejadorPaquetes() {
    if (instancia == null) {
      instancia = new ManejadorPaquetes();
    }
    return instancia;
  }

  public void addPaquete(Paquete paq) {
    paquetes.put(paq.getNombre(), paq);
  }

  public Paquete getPaquete(String nombre) {
    return (Paquete) paquetes.get(nombre);
  }

  /**
   * Devuelve un arreglo con todos los paquetes.
   */
  public Paquete[] getPaquetes() {
    if (this.paquetes.isEmpty()) {
      return null;
    } else {
      Collection<Paquete> paq = paquetes.values();
      Object[] obj = paq.toArray();
      Paquete[] paquetes = new Paquete[obj.length];
      for (int i = 0; i < obj.length; i++) {
        paquetes[i] = (Paquete) obj[i];
      }
      return paquetes;
    }
  }
}
