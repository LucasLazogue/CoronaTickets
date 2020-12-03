package logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Espectaculo {

  private String nombre;
  private String descripcion;
  private int duracion;
  private int minEspectadores;
  private int maxEspectadores;
  private int costo;
  private String url;
  private Date fechaRegistro;
  private EstadoEspectaculo estado;
  private String urlImg;
  private Map<String, Funcion> funciones;
  private Map<String, Categoria> categorias;
  private DataPremio premio;
  private int cantPremiosPorFuncion;
  private String urlVideo;
  private List<Puntaje> puntajes;

  /**
  * Constructor de Espectaculo.
  */
  public Espectaculo(String nombre, String descripcion, int duracion, int minEspectadores,
      int maxEspectadores, String url, int costo, Date fechaRegistro, String urlImg,
      String descripcionPremio, int cantPremiosPorFuncion, String urlVideo) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.duracion = duracion;
    this.minEspectadores = minEspectadores;
    this.maxEspectadores = maxEspectadores;
    this.costo = costo;
    this.url = url;
    this.fechaRegistro = fechaRegistro;
    this.estado = EstadoEspectaculo.Ingresado;
    this.urlImg = urlImg;
    this.funciones = new HashMap<String, Funcion>();
    this.categorias = new HashMap<String, Categoria>();
    this.setPremio(new DataPremio(descripcionPremio, cantPremiosPorFuncion));
    this.cantPremiosPorFuncion = cantPremiosPorFuncion;
    this.setUrlVideo(urlVideo);
    this.puntajes = new ArrayList<Puntaje>();
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

  public void setDuracion(int value) {
    this.duracion = value;
  }

  public int getDuracion() {
    return this.duracion;
  }

  public void setMinEspectadores(int value) {
    this.minEspectadores = value;
  }

  public int getMinEspectadores() {
    return this.minEspectadores;
  }

  public void setMaxEspectadores(int value) {
    this.maxEspectadores = value;
  }

  public int getMaxEspectadores() {
    return this.maxEspectadores;
  }

  public void setUrl(String value) {
    this.url = value;
  }

  public String getUrl() {
    return this.url;
  }

  public void setCosto(int value) {
    this.costo = value;
  }

  public int getCosto() {
    return this.costo;
  }

  public void setFechaRegistro(Date value) {
    this.fechaRegistro = value;
  }

  public Date getFechaRegistro() {
    return this.fechaRegistro;
  }

  public EstadoEspectaculo getEstado() {
    return this.estado;
  }

  public void setEstado(EstadoEspectaculo esp) {
    this.estado = esp;
  }

  public String getUrlImg() {
    return this.urlImg;
  }

  public void setUrlImg(String urlImg) {
    this.urlImg = urlImg;
  }

  public DataPremio getPremio() {
    return premio;
  }

  public void setPremio(DataPremio premio) {
    this.premio = premio;
  }
  
  public int getCantPremiosPorFuncion() {
    return cantPremiosPorFuncion;
  }
  
  public void setCantPremiosPorFuncion(int cantPremiosPorFuncion) {
    this.cantPremiosPorFuncion = cantPremiosPorFuncion;
  }

  public String getUrlVideo() {
    return urlVideo;
  }

  public void setUrlVideo(String urlVideo) {
    this.urlVideo = urlVideo;
  }

  public void agregarFuncion(Funcion fun) {
    String nombre = fun.getNombre();
    funciones.put(nombre, fun);
  }

  public Funcion getFuncion(String nombre) {
    return (Funcion) funciones.get(nombre);
  }

  public Set<String> getNombreFunciones() {
    return this.funciones.keySet();
  }

  /**
  * Lista las funciones del espectáculo.
  */
  public String[] listarFunciones() {
    //Retornar null si no hay funciones
    if (this.funciones.isEmpty()) {
      return null;
    }
    //Si hay funciones, generar lista de STRINGS de nombres y retornarla
    Collection<Funcion> colF = this.funciones.values();
    Object[] objF = colF.toArray();
    String[] res = new String[objF.length];
    for (int i = 0; i < objF.length; i++) {
      res[i] = ((Funcion) objF[i]).getNombre();
    }
    return res;
  }

  /**
  * Lista los paquetes asociados al espectáculo.
  */
  public String[] listarPaquetesAsociados() {
    ManejadorPaquetes manPac = ManejadorPaquetes.getManejadorPaquetes();
    Paquete[] paquetes = manPac.getPaquetes();
    Set<String> setNomPaquetes = new HashSet<String>();
    for (int i = 0; paquetes != null && i < paquetes.length; i++) {
      if (paquetes[i].getEspectaculo(this.nombre) != null) {
        setNomPaquetes.add(paquetes[i].getNombre());
      }
    }
    String[] nomPaquetes;
    if (setNomPaquetes.size() == 0) {
      nomPaquetes = null;
    } else {
      nomPaquetes = new String[setNomPaquetes.size()];
      nomPaquetes = setNomPaquetes.toArray(nomPaquetes);
    }
    return nomPaquetes;
  }

  public void agregarCategoria(Categoria cat) {
    String nombre = cat.getNombre();
    this.categorias.put(nombre, cat);
  }

  /**
  * Lista las categorias del espectáculo.
  */
  public String[] listarCategorias() {
    //Retornar null si no hay categorias
    if (this.categorias.isEmpty()) {
      return null;
    }
    //Si hay categorias, generar lista de STRINGS de nombres y retornarla
    Collection<Categoria> colC = this.categorias.values();
    Object[] objC = colC.toArray();
    String[] res = new String[objC.length];
    for (int i = 0; i < objC.length; i++) {
      res[i] = ((Categoria) objC[i]).getNombre();
    }
    return res;
  }
  
  public void setPuntaje(Puntaje puntaje) {
    this.puntajes.add(puntaje);
  }
  
  public List<Puntaje> getPuntajes() {
	  return this.puntajes;
  }
}