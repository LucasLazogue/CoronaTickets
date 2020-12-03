package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Espectador extends Usuario {

  private Set<Registro> registro;
  private Map<String, Compra> compras;
  private Set<Compra> compra;
  private List<Puntaje> puntajes;
  private Map<String, Espectaculo> espectaculosFavoritos;

  /**
  * Constructor de Espectador.
  */
  public Espectador(String nickName, String email, String nombre, String apellido,
      LocalDateTime fecha, String password, String urlImg) {
    super(nickName, email, nombre, apellido, fecha, password, urlImg);
    this.registro = new HashSet<Registro>();
    this.compras = new HashMap<String, Compra>();
    this.espectaculosFavoritos = new HashMap<String, Espectaculo>();
    this.puntajes = new ArrayList<Puntaje>();
  }

  //CompraPaquete
  public void compraPaquete(Paquete paq, Date fecha) {
    Compra com = new Compra(fecha, paq);
    compras.put(paq.getNombre(), com);
  }

  public Compra getCompra(String nombrePaq) {
    return (Compra) compras.get(nombrePaq);
  }
  
  public Set<Compra> getCompra() {
    return this.compra;
  }
  
  /**
  * Get de los regostros.
  */
  public Set<Registro> getRegistro() {
    if (this.registro == null) {
      this.registro = new HashSet<Registro>();
    }
    return this.registro;
  }

  public Map<String, Compra> getCompras() {
    return this.compras;
  }

  /**
  * Registrar espectador a función.
  */
  public void registrarAFuncion(Funcion fun, String nombrePaquete, List<String> canjeaRegistros,
      Date fecha) {
	  
	System.out.println("******************** ESPECTADOR *********************************");  
    Registro reg;
    Paquete paq = null;
    Compra com = null;
    System.out.println(this.getNickname() + " REGISTRANDOSE A " + fun.getNombre());
    if (canjeaRegistros.isEmpty()) {
        System.out.println("NO CANJEA NADA");

      int desc = 0;
      if (!nombrePaquete.isEmpty()) {
        com = this.compras.get(nombrePaquete);
        paq = com.getPaquete();
        desc = paq.getDescuento();
      }
      int precio = fun.getEspectaculo().getCosto()
              - ((desc * fun.getEspectaculo().getCosto()) / 100);
      reg = new Registro(fecha, precio, fun);
    } else {
      Set<Registro> canjeados = new HashSet<Registro>();
      for (Registro r : registro) {
        if (canjeaRegistros.contains(r.getFuncion().getNombre())) {
          canjeados.add(r);
        }
      }
      
      System.out.println("REGISTROS ANTES DE CANJEAR");
      for (Registro r : this.registro) {
    	  System.out.println(r.getFuncion().getNombre());
      }
      
      System.out.println("CANJEA LOS REGISTROS");
      for (Registro r : canjeados) {
    	  System.out.println(r.getFuncion().getNombre());
      }
      

      for (Registro r : canjeados) {
        if (canjeaRegistros.contains(r.getFuncion().getNombre())) {
          registro.remove(r);
          r.getFuncion().eliminarRegistro(r);
        }
      }
      
      System.out.println("REGISTROS DESPUES DE CANJEAR");
      for (Registro r : this.registro) {
    	  System.out.println(r.getFuncion().getNombre());
      }
      
      reg = new Registro(fecha, 0, fun, canjeados);
    }
    reg.setPaquete(paq);
    reg.setUsuario(this.getNickname());
    this.registro.add(reg);
    fun.agregarRegistro(reg);
  }
  
  /**
  * True sii espectador está registrado a función.
  */
  public boolean registradoEnFuncion(String nombreFuncion) {
    for (Registro reg : this.registro) {
      if (reg.getFuncion().getNombre().equals(nombreFuncion)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Si existe un puntaje para espectaculo, lo devuelve, sino devuelve null.
   */
  public Puntaje existePuntaje(Espectaculo espectaculo) {
    Puntaje res = null;
    for (int i = 0; i < puntajes.size(); i++) {
      if (puntajes.get(i).getEspectaculo() == espectaculo) {
        res = puntajes.get(i);
      }
    }
    return res;
  }
  
  /**
   * Si existe un puntaje lo modifica, sino crea uno y ademas se lo asigan a espectaculo.
   */
  public void agregarPuntaje(int puntaje, Espectaculo espectaculo) {
    Puntaje nuevoPuntaje = this.existePuntaje(espectaculo);
    if (nuevoPuntaje != null) {
      nuevoPuntaje.modificarPuntaje(puntaje);
    } else {
      nuevoPuntaje = new Puntaje(puntaje, espectaculo);
      puntajes.add(nuevoPuntaje);
      espectaculo.setPuntaje(nuevoPuntaje);
    }
  }
  
  /**
   * Get espectáculos favoritos.
   */
  public String[] getEspectaculosFavoritos() {
    //Retornar null si no tiene espectáculos favoritos
    if (this.espectaculosFavoritos.isEmpty()) {
      return new String[0];
    }
    //Si tiene, generar lista de STRINGS de nombres y retornarla
    return this.espectaculosFavoritos.keySet().toArray(new String[0]);
  }
  
  public void marcarEspectaculoFavorito(Espectaculo esp) {
    String nombre = esp.getNombre();
    espectaculosFavoritos.put(nombre, esp);  
  }

  public void desmarcarEspectaculoFavorito(Espectaculo esp) {
    espectaculosFavoritos.remove(esp.getNombre());  
  }
}