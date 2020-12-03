package logica;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectaculoRepetidoException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.EstadoEspectaculoException;
import exepciones.FechaPasadaException;
import exepciones.FinVigAntesIniVigException;
import exepciones.FuncionLlenaExepcion;
import exepciones.FuncionRepetidaException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PaqueteRepetidoException;
import exepciones.PlataformaRepetidaException;
import exepciones.RegistroNoSePuedeCanjearException;
import exepciones.UsuarioNoExisteException;
import java.util.Date;
import java.util.List;

public interface IEspectaculoController {
  public abstract void agregarPlataforma(String nombre, String url,
      String descripcion) throws PlataformaRepetidaException;
  
  public abstract void ingresarDatosFuncion(String nombreFuncion,
      Date horaInicio, List<String> nickArtistas, Date fechaAlta,
      String nombreEspectaculo, String urlimg) throws 
      FuncionRepetidaException, EspectaculoNoExisteException;

  public abstract void crearEspectaculo(String plataforma, String nicknameArtista, 
      String nombre, String descripcion, int duracion, int minEspectadores, 
      int maxEspectadores, String url, int costo, Date fechaRegistro, 
      String[] categorias, String urlImg, String descripcionPremio,
      int cantPremiosPorFuncion, String urlVideo) throws 
      EspectaculoRepetidoException, UsuarioNoExisteException, MaxEspMenorMinEspException;

  public abstract String[] listarEspectaculos(String plataforma);

  public abstract void confirmarRegistroAFuncion(String nombrePlataforma, String nombrePaquete, 
      String nombreEspectaculo, String nombreFuncion, String nombreUsuario, 
      List<String> canjeaRegistros, Date fecha) throws FuncionLlenaExepcion, 
      NoEsEspectadorExepcion, EspectadorRegistradoEnFuncionExepcion, 
      FechaPasadaException, RegistroNoSePuedeCanjearException, ErrorCantidadCanjeExcepcion;

  public abstract DataEspectaculo seleccionarEspectaculo(String nombreEspectaculo) throws 
      EspectaculoNoExisteException;

  public abstract String[] listarFuncionesEspectaculo(String nombrePlataforma, 
      String nombreEspectaculo);

  public abstract DataFuncion consultaFuncion(String nombreFuncion, 
      String nombreEspectaculo) throws EspectaculoNoExisteException;

  public abstract void crearPaquete(String nombre, String descripcion, 
      Date inicioVigencia, Date finVigencia, int descuento, 
      Date fechaDeAlta, String url) throws PaqueteRepetidoException, 
      FinVigAntesIniVigException;

  public abstract String[] listarPaquetes();

  public abstract String[] listarEspectaculosEnPaquete(String nomPlataforma, String nomPaquete);

  public abstract String[] listarEspectaculosNoEnPaquete(String nomPlataforma, String nomPaquete);

  public abstract void agregarEspectaculoAPaquete(String nomPlataforma, 
      String nomEspectaculo, String nomPaquete);

  public abstract String[] getPlataformas();

  public abstract DataPaquete seleccionarPaquete(String nomPaquete);

  public abstract DataEspectaculo seleccionarEspectaculoDePaquete(String nomPaquete, 
      String nomEspectaculo);

  public abstract void ingresarDatosCategoria(String nombreCategoria);

  public abstract DataPaquete[] listarPaqueteValidos(Date fecha);

  public abstract String[] listarEspectaculosCategoria(String categoria);

  public abstract String[] listarEspectaculosEstadoIngresado();

  public abstract void aceptarEspectaculo(String nomEspectaculo, 
      boolean aceptado) throws EspectaculoNoExisteException;

  public abstract String[] listarCategorias();

  public abstract DataPlataforma seleccionarPlataforma(String nombre);

  public abstract Object[] busqueda(String clave);
  
  public abstract void sortearPremios(String nombrePlataforma, String nombreEspectaculo,
      String nombreFuncion, Date fecha) throws FechaPasadaException;
  
  public abstract int cantVecesEspectaculoFavorito(String espectaculo)
      throws EspectaculoNoExisteException;
  
  public abstract void finalizarEspectaculo(String nickname, String nombre) 
      throws EstadoEspectaculoException;
  
  public abstract DataEspectaculo[] listarEspectaculoFinalizado(String nickname);

  public abstract int[] valoracionEspectaculo(String nombreEspectaculo);
  
  public abstract String[] getEspectadoresRegistrados(String funcion, String espectaculo);
  
  public abstract String[] getEspectadoresGanadores(String funcion, String espectaculo);

  public abstract boolean habilitadoVal(String espectador, String espectaculo);
  
  public void sortearPremiosArreglado(String nombrePlataforma, String nombreEspectaculo,
      String nombreFuncion, Date fecha, String[] ganadores) throws FechaPasadaException;
}
