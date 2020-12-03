package logica;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPlataforma {

  private String nombre;
  private String descripcion;
  private String[] espectaculos;

  public DataPlataforma() {}
  
  /**
  * OA po OA lala OA teletubie!.
  */
  public DataPlataforma(String nombre, String descripcion, String[] espectaculos) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.espectaculos = espectaculos;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public String[] getEspectaculos() {
    return this.espectaculos;
  }

}