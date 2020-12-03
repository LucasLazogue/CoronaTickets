package logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Funcion {
  private String nombre;

  /**
   * Para que ande el checkstyle.
   */
  public Funcion(String nombre, Date fechaHoraInicio, Map<String, Artista> artistas, 
      Date fechaAlta, Espectaculo espectaculo, String urlimg) {
    this.nombre = nombre;
    this.fechaHoraInicio = fechaHoraInicio;
    this.artista = artistas;
    this.fechaRegistro = fechaAlta;
    this.espectaculo = espectaculo;
    this.urlimg = urlimg;
    this.registros = new HashSet<Registro>();
    this.fechaSorteo = null;
    this.cantRegistros = 0;
  }

  public void setNombre(String value) {
    this.nombre = value;
  }

  public String getNombre() {
    return this.nombre;
  }

  private Date fechaHoraInicio;

  public void setFechaHoraInicio(Date value) {
    this.fechaHoraInicio = value;
  }

  public Date getFechaHoraInicio() {
    return this.fechaHoraInicio;
  }

  private Date fechaRegistro;

  public void setFechaRegistro(Date value) {
    this.fechaRegistro = value;
  }

  public Date getFechaRegistro() {
    return this.fechaRegistro;
  }

  private Espectaculo espectaculo;

  public void setEspectaculo(Espectaculo value) {
    this.espectaculo = value;
  }

  public Espectaculo getEspectaculo() {
    return this.espectaculo;
  }

  private Map<String, Artista> artista;

  /**
   * Funcion getArtista.
   */
  public Map<String, Artista> getArtista() {
    /* if (this.artista == null) {
    this.artista = new HashSet<Artista>();
    } */
    return this.artista;
  }

  private int cantRegistros;

  public void setCantRegistros(int value) {
    this.cantRegistros = value;
  }

  public int getCantRegistros() {
    return this.cantRegistros;
  }
  
  private Set<Registro> registros;
  
  public Set<Registro> getRegistros() {
	  return this.registros;
  }
  
  public void agregarRegistro(Registro reg) {
	  System.out.println("AGREGANDO REGISTRO " + reg.getFuncion().getNombre() + " DEL USUARIO " + this.getNombre() + " EL REGISTRO TIENE EL NOMBRE DE " + reg.getUsuario());
	  System.out.println("LA FUNCION " + this.getNombre() + " ANTES DE AGREAR TIENE LOS REGISTROS DE");
	  Registro[] reg2 = this.registros.toArray(new Registro[0]);
	  for (Registro r : reg2) {
		  System.out.println(r.getUsuario());
	  }	  
	  this.registros.add(reg);
	  Registro[] reg3 = this.registros.toArray(new Registro[0]);
	  System.out.println("LA FUNCION " + this.getNombre() + " DESPUES DE AGREGAR TIENE LOS REGISTROS DE");
	  for (Registro r : reg3) {
		  System.out.println(r.getUsuario());
	  }
  }
  
  public void eliminarRegistro(Registro reg) {
	  System.out.println("ELIMINANDO EL REGISTRO DE " + reg.getUsuario() + " A " + reg.getFuncion().getNombre() + "DE " + this.getNombre());
	  System.out.println("LA FUNCION " + this.getNombre() + " ANTES DE CANJEAR TIENE LOS REGISTROS DE");
	  Registro[] reg2 = this.registros.toArray(new Registro[0]);
	  for (Registro r : reg2) {
		  System.out.println(r.getUsuario());
	  }
	  for (Registro r : this.registros) {
		  if (r.getFuncion().getNombre().equals(reg.getFuncion().getNombre()) && r.getUsuario().equals(reg.getUsuario())) {
			  this.registros.remove(r);
			  break;
		  }
	  }
	  Registro[] reg3 = this.registros.toArray(new Registro[0]);
	  System.out.println("LA FUNCION " + this.getNombre() + " DESPUES DE CANJEAR TIENE LOS REGISTROS DE");
	  for (Registro r : reg3) {
		  System.out.println(r.getUsuario());
	  }
	  
	  //this.registros.remove(reg);
  }
  
  private Date fechaSorteo;
  
  public void setFechaSorteo(Date date) {
	  this.fechaSorteo = date;
  }
  
  public Date getFechaSorteo() {
	  return this.fechaSorteo;
  }
 

  /**
   * Funcion para traer nombres de artistas.
   */
  public String[] getNombresArtistas() {
    if (this.artista.isEmpty()) {
      return null;
    } else {
      Collection<Artista> colA = artista.values();
      Object[] objA = colA.toArray();
      String[] resultado = new String[colA.size()];
      for (int i = 0; i < colA.size(); i++) {
        resultado[i] = ((Artista) objA[i]).getNickname();
      }
      return resultado;
    }
  }

  private String urlimg;

  public String getUrlimg() {
    return this.urlimg;
  }
  
  public void sortearPremios() {
	  System.out.println("***** SORTEO *******");
	  if (this.espectaculo.getCantPremiosPorFuncion() != 0) {
		  if (this.espectaculo.getCantPremiosPorFuncion() >= this.registros.size()) {
			  for(Registro reg : this.registros) {
				  reg.setPremio();
			  }
			  System.out.println("PRIMER IF");
			  System.out.println("GANADORES");
			  for(Registro reg : this.registros) {
				  System.out.println(reg.getUsuario() + " - " + reg.getPremio());
			  }

		  } else {
			  List<Registro> auxLst = new ArrayList<>();
			  auxLst.addAll(this.registros);
			  Collections.shuffle(auxLst);
			  for (int i = 0; i < this.espectaculo.getCantPremiosPorFuncion() && i < auxLst.size(); i++) {
				  auxLst.get(i).setPremio();
			  }
			  System.out.println("SEGUNDO IF");
			  System.out.println("GANADORES");
			  for(Registro reg : this.registros) {
				  System.out.println(reg.getUsuario() + " - " + reg.getPremio());
			  }
		  }
	  }
  }
  
  public void sortearPremiosArreglado(String[] ganadores) {
	  Usuario[] usuariosGanadores = new Usuario[ganadores.length];
	  ManejadorUsuarios manU = ManejadorUsuarios.getManejadorUsuarios();
	  for (int i = 0; i < ganadores.length; i++) {
		  usuariosGanadores[i] = manU.getEspectador(ganadores[i]);
	  }
	  for(Registro reg : this.registros) {
		  for (String usu: ganadores) {
			  if (reg.getUsuario().equals(usu)) {
				  reg.setPremio();
			  }
		  }
	  }
  }
}
