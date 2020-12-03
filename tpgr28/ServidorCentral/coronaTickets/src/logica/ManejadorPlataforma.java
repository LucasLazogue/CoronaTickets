package logica;




import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManejadorPlataforma {

  private Map<String, Plataforma> plataformas;
  private static ManejadorPlataforma instancia = null;

  private ManejadorPlataforma() {
    plataformas = new HashMap<String, Plataforma>();
  }

  /**
   * Trae el manejador de plataformas.
   */
  public static ManejadorPlataforma getInstance() {
    if (instancia == null) {
      instancia = new ManejadorPlataforma();
    }
    return instancia;
  }

  public void addPlataforma(Plataforma paq) {
    String nombre = paq.getNombre();
    plataformas.put(nombre, paq);
  }

  public Plataforma obtenerPlataforma(String nombre) {
    return (Plataforma) plataformas.get(nombre);
  }

  /**
   * Devuelve un arreglo con todas las plataformas.
   */
  public Plataforma[] getPlataformas() {
    if (this.plataformas.isEmpty()) {
      return null;
    } else {
      Collection<Plataforma> plats = plataformas.values();
      Object[] obj = plats.toArray();
      Plataforma[] plataformas = new Plataforma[obj.length];
      for (int i = 0; i < obj.length; i++) {
        plataformas[i] = (Plataforma) obj[i];
      }
      return plataformas;
    }
  }

}
