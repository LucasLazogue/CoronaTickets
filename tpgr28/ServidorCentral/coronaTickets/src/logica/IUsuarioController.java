package logica;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import exepciones.EspectaculoNoExisteException;
import exepciones.FuncionNoExisteException;
import exepciones.FuncionNoFinalizadaException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PasswordErrorException;
import exepciones.UsuarioNoExisteException;
import exepciones.UsuarioRepetidoExepcion;
import exepciones.YaComproPaqueteException;


public interface IUsuarioController {


  //Alta de Usuario
  public void nuevoArtista(String nickName, String email, String nombre, 
      String apellido, LocalDateTime fecha, String descripcion, 
      String biografia, String web, String password, String veripassword, 
      String urlImg) throws UsuarioRepetidoExepcion, PasswordErrorException;

  public void nuevoEspectador(String nickName, String email, String nombre, 
      String apellido, LocalDateTime fecha, String password, String veripassword, 
      String urlImg)throws UsuarioRepetidoExepcion, PasswordErrorException;

  //Consulta de Usuario
  public String[] listarUsuarios() throws UsuarioNoExisteException;

  public DataUsuario mostrarDatosUsuario(String nickname) throws UsuarioNoExisteException;

  public DataUsuario mostrarDatosUsuarioGeneral(String nickname) throws UsuarioNoExisteException;

  public DataEspectaculo seleccionarEspectaculo(String nickname, String nombre) 
      throws EspectaculoNoExisteException;

  public DataFuncion seleccionarFuncion(String nickname, String nombre) 
      throws FuncionNoExisteException;

  //Modificar Datos de Usuario
  public void modificarDatosUsuario(String nickname, String nombre, 
      String apellido, LocalDateTime fecha, String descripcion, String biografia, 
      String web, String urlImg) throws UsuarioNoExisteException;

  public Map<String, DataEspectaculo> listarEspectaculosDeArtista(String nickname) 
      throws EspectaculoNoExisteException;

  //public Map<String, DataRegistro> listarFuncionesEspectador(String nickname);

  public DataRegistro[] listarFuncionesEspectador(String nickname);
  //Validar Usuario
  public boolean validarUsuario(String nickname, 
      String password) throws UsuarioNoExisteException, PasswordErrorException;

  public void CompraPaquete(String nickName, String nombrePaquete, 
      Date fecha) throws YaComproPaqueteException;

  //Seguir y dejar de seguir usuario
  public void seguirUsuario(String nickname1, String nickname2);

  public void dejarDeSeguirUsuario(String nickname1, String nickname2);
  
  //Marcar y desmarcar espectaculo como favorito
  public void marcarEspectaculoFavorito(String nickname, String espectaculo)
      throws UsuarioNoExisteException, EspectaculoNoExisteException;
  
  public void desmarcarEspectaculoFavorito(String nickname, String espectaculo)
      throws UsuarioNoExisteException, EspectaculoNoExisteException;

  //Puntuar espectaculo
  public void puntuar(int puntaje, Espectaculo esp, Usuario usuario)
      throws FuncionNoFinalizadaException, NoEsEspectadorExepcion;
  
  //Datos de prueba
  public void cargarDatosPrueba();
  
  //Mostrar Premios
  public DataPremio[] mostrarPremios(String nickname);
  
  public DataPuntaje existePuntaje(String espectador, String espectaculo);
  
  public void agregarPuntaje(int puntaje, String espectaculo, String espectador);
  
  public void agregarDatoHistorial(String ip, String url, String browser, String os);
  
  public DataDatos[] listarDatosHistorial();
  
  public Boolean validarCodigo(String codigo); 

}
