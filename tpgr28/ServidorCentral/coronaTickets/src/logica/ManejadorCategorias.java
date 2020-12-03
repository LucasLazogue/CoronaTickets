package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManejadorCategorias {
  private static ManejadorCategorias instancia = null;
  private Map<String, Categoria> categorias;

  private ManejadorCategorias() {
    categorias = new HashMap<String, Categoria>();
  }
  
  /**
   * Trae instancia del manejador.
   */
  public static ManejadorCategorias getInstance() {
    if (instancia == null) {
      instancia = new ManejadorCategorias();
    }
    return instancia;
  }
  
  /**
   * Agrega una categoria.
   */
  public void agregarCategoria(Categoria categoria) {
    String nombre = categoria.getNombre();
    if (!this.categorias.containsKey(nombre)) {
      this.categorias.put(nombre, categoria);
    }
  }

  public Categoria getCategoria(String nombreCategoria) {
    return this.categorias.get(nombreCategoria);
  }

  /**
   * Obtener arreglo con todas las categorias.
   */
  public Categoria[] getCategorias() {
    if (this.categorias.isEmpty()) {
      return null;
    } else {
      Collection<Categoria> cat = categorias.values();
      Object[] obj = cat.toArray();
      Categoria[] categorias = new Categoria[obj.length];
      for (int i = 0; i < obj.length; i++) {
        categorias[i] = (Categoria) obj[i];
      }
      return categorias;
    }
  }
}
