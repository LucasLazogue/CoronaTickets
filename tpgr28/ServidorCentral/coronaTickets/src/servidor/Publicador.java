/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectaculoRepetidoException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.EstadoEspectaculoException;
import exepciones.FechaPasadaException;
import exepciones.FinVigAntesIniVigException;
import exepciones.FuncionLlenaExepcion;
import exepciones.FuncionNoExisteException;
import exepciones.FuncionRepetidaException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PaqueteRepetidoException;
import exepciones.PasswordErrorException;
import exepciones.RegistroNoSePuedeCanjearException;
import exepciones.UsuarioNoExisteException;
import exepciones.UsuarioRepetidoExepcion;
import exepciones.YaComproPaqueteException;
import logica.DataArtista;
import logica.DataDatos;
import logica.DataEspectaculo;
import logica.DataEspectador;
import logica.DataFuncion;
import logica.DataPaquete;
import logica.DataPlataforma;
import logica.DataPremio;
import logica.DataPuntaje;
import logica.DataRegistro;
import logica.DataUsuario;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;
import logica.ListaString;
import logica.LocalDateTimeUsuarios;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {

  private IEspectaculoController ice;
  private IUsuarioController icu;
  private Endpoint endpoint = null;
  //Constructor
  public Publicador(){
    Fabrica fab = Fabrica.getInstancia();
    ice = fab.getIEspectaculoController();
    icu = fab.getIUsuarioController();
  }

  //Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar(){
    Properties p = new Properties();
    try {
		p.load(new FileReader("/home/vagrant/.coronaTickets/config.properties"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    endpoint = Endpoint.publish("http://" + p.getProperty("IP") + ":" + p.getProperty("PORT") + "/publicador", this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
    return endpoint;
  }

  /* OPERACIONES DE IESPECTACULOCONTROLLER */
  @WebMethod
  public void ingresarDatosFuncion(String nombreFuncion,
      Date horaInicio, String[] nickArtistas, Date fechaAlta,
      String nombreEspectaculo, String urlimg) throws 
      FuncionRepetidaException, EspectaculoNoExisteException {
    ListaString lista = new ListaString(nickArtistas);
    if (urlimg.equals(""))
    	urlimg = null;
    ice.ingresarDatosFuncion(nombreFuncion, horaInicio, lista.getListaString(),
        fechaAlta, nombreEspectaculo, urlimg);
  }

  @WebMethod
  public void crearEspectaculo(String plataforma, String nicknameArtista, 
      String nombre, String descripcion, int duracion, int minEspectadores, 
      int maxEspectadores, String url, int costo, Date fechaRegistro, 
      String[] categorias, String urlImg, String descripcionPremio,
      int cantPremiosPorFuncion, String urlVideo) throws 
      EspectaculoRepetidoException, UsuarioNoExisteException, MaxEspMenorMinEspException {
    ice.crearEspectaculo(plataforma, nicknameArtista, nombre, descripcion, duracion,
        minEspectadores, maxEspectadores, url, costo, fechaRegistro, categorias, urlImg,
        descripcionPremio, cantPremiosPorFuncion, urlVideo);
  }

  @WebMethod
  public String[] listarEspectaculos(String plataforma) {
    String[] res = ice.listarEspectaculos(plataforma);
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public void confirmarRegistroAFuncion(String nombrePlataforma, String nombrePaquete, 
      String nombreEspectaculo, String nombreFuncion, String nombreUsuario, 
      String[] canjeaRegistros, Date fecha) throws FuncionLlenaExepcion, 
      NoEsEspectadorExepcion, EspectadorRegistradoEnFuncionExepcion, 
      FechaPasadaException, RegistroNoSePuedeCanjearException, ErrorCantidadCanjeExcepcion {
    ListaString lista = new ListaString(canjeaRegistros);
    ice.confirmarRegistroAFuncion(nombrePlataforma, nombrePaquete, nombreEspectaculo,
        nombreFuncion, nombreUsuario, lista.getListaString(), fecha);
  }

  @WebMethod
  public DataEspectaculo seleccionarEspectaculo(String nombreEspectaculo) throws 
      EspectaculoNoExisteException {
    return ice.seleccionarEspectaculo(nombreEspectaculo);
  }

  @WebMethod
  public String[] listarFuncionesEspectaculo(String nombrePlataforma, 
      String nombreEspectaculo) {
    String[] res = ice.listarFuncionesEspectaculo(nombrePlataforma, nombreEspectaculo);
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public DataFuncion consultaFuncion(String nombreFuncion, 
      String nombreEspectaculo) throws EspectaculoNoExisteException {
    return ice.consultaFuncion(nombreFuncion, nombreEspectaculo);
  }

  @WebMethod
  public void crearPaquete(String nombre, String descripcion, 
      Date inicioVigencia, Date finVigencia, int descuento, 
      Date fechaDeAlta, String url) throws PaqueteRepetidoException, 
      FinVigAntesIniVigException {
	if (url.equals("")) url = null;
    ice.crearPaquete(nombre, descripcion, inicioVigencia, finVigencia,
        descuento, fechaDeAlta, url);
  }

  @WebMethod
  public String[] listarPaquetes() {
    String[] res = ice.listarPaquetes();
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public String[] listarEspectaculosEnPaquete(String nomPlataforma, String nomPaquete) {
    String[] res = ice.listarEspectaculosEnPaquete(nomPlataforma, nomPaquete);
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public String[] listarEspectaculosNoEnPaquete(String nomPlataforma, String nomPaquete) {
    String[] res = ice.listarEspectaculosNoEnPaquete(nomPlataforma, nomPaquete);
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public void agregarEspectaculoAPaquete(String nomPlataforma, 
      String nomEspectaculo, String nomPaquete) {
    ice.agregarEspectaculoAPaquete(nomPlataforma, nomEspectaculo, nomPaquete);
  }

  @WebMethod
  public String[] getPlataformas() {
    String[] res = ice.getPlataformas();
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public DataPaquete seleccionarPaquete(String nomPaquete) {
    return ice.seleccionarPaquete(nomPaquete);
  }

  @WebMethod
  public DataEspectaculo seleccionarEspectaculoDePaquete(String nomPaquete, 
      String nomEspectaculo) {
    return ice.seleccionarEspectaculoDePaquete(nomPaquete, nomEspectaculo);
  }

  @WebMethod
  public DataPaquete[] listarPaqueteValidos(Date fecha) {
    DataPaquete[] res = ice.listarPaqueteValidos(fecha);
    if (res == null) return new DataPaquete[0];
    return res;
  }

  @WebMethod
  public String[] listarEspectaculosCategoria(String categoria) {
    String[] res = ice.listarEspectaculosCategoria(categoria);
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public String[] listarEspectaculosEstadoIngresado() {
    String[] res = ice.listarEspectaculosEstadoIngresado();
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public String[] listarCategorias() {
    String[] res = ice.listarCategorias();
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public DataPlataforma seleccionarPlataforma(String nombre) {
    return ice.seleccionarPlataforma(nombre);
  }

  @WebMethod
  public Object[] busqueda(String clave) {
    Object[] res =  ice.busqueda(clave);
    if (res == null) return new Object[0];
    return res;
  }
  
  @WebMethod
  public int[] valoracionEspectaculo(String nombreEspectaculo) {
      int[] res = ice.valoracionEspectaculo(nombreEspectaculo);
      if (res == null) return new int[0];
	  return res;
  }
  
  @WebMethod
  public String[] getEspectadoresRegistrados(String funcion, String espectaculo) {
      String[] res = ice.getEspectadoresRegistrados(funcion, espectaculo);
      if (res == null) return new String[0];
	  return res;
  }
  
  @WebMethod
  public String[] getEspectadoresGanadores(String funcion, String espectaculo) {
      String[] res = ice.getEspectadoresGanadores(funcion, espectaculo);
      if (res == null) return new String[0];
	  return res;
  }
  
  @WebMethod
  public void sortearPremios(String nombrePlataforma, String nombreEspectaculo,
          String nombreFuncion, Date fecha) throws FechaPasadaException {
	  ice.sortearPremios(nombrePlataforma, nombreEspectaculo, nombreFuncion, fecha);
  }
  /* OPERACIONES DE IUSUARIOCONTROLLER */
  
  @WebMethod
  public void nuevoArtista(String nickName, String email, String nombre, 
      String apellido, Date fecha, String descripcion, 
      String biografia, String web, String password, String veripassword, 
      String urlImg) throws UsuarioRepetidoExepcion, PasswordErrorException {
	  LocalDateTimeUsuarios fec = new LocalDateTimeUsuarios(fecha);
	  icu.nuevoArtista(nickName, email, nombre, apellido, fec.getFecha(), descripcion,
        biografia, web, password, veripassword, urlImg);
  }

  @WebMethod
  public void nuevoEspectador(String nickName, String email, String nombre, 
      String apellido, Date fecha, String password, String veripassword, 
      String urlImg)throws UsuarioRepetidoExepcion, PasswordErrorException {
	  LocalDateTimeUsuarios fec = new LocalDateTimeUsuarios(fecha);
    icu.nuevoEspectador(nickName, email, nombre, apellido, fec.getFecha(), password,
        veripassword, urlImg);
  }

  @WebMethod
  public String[] listarUsuarios() throws UsuarioNoExisteException {
    String[] res =  icu.listarUsuarios();
    if (res == null) return new String[0];
    return res;
  }

  @WebMethod
  public DataUsuario mostrarDatosUsuario(String nickname) throws UsuarioNoExisteException {
	  DataUsuario u = icu.mostrarDatosUsuario(nickname);
	  u.convertFecha();
	return u;
  }

  @WebMethod
  public DataUsuario mostrarDatosUsuarioGeneral(String nickname) throws UsuarioNoExisteException {
	  DataUsuario u = icu.mostrarDatosUsuario(nickname);
	  u.convertFecha();
    return u;
  }

  @WebMethod
  public DataEspectaculo seleccionarEspectaculoUsuario(String nickname, String nombre) 
      throws EspectaculoNoExisteException {
    return icu.seleccionarEspectaculo(nickname, nombre);
  }
  
  @WebMethod
  public DataRegistro[] listarFuncionesEspectador(String nickname) {
    DataRegistro[] res = icu.listarFuncionesEspectador(nickname);
    if (res == null) return new DataRegistro[0];
    return res;
  }

  @WebMethod
  public DataFuncion seleccionarFuncion(String nickname, String nombre) 
      throws FuncionNoExisteException {
    return icu.seleccionarFuncion(nickname, nombre);
  }

  @WebMethod
  public void modificarDatosUsuario(String nickname, String nombre, 
      String apellido, Date fecha, String descripcion, String biografia, 
      String web, String urlImg, String tipo_usuario) throws UsuarioNoExisteException {
	  LocalDateTimeUsuarios fec = new LocalDateTimeUsuarios(fecha);
	  if (tipo_usuario.equals("Espectador")) {
		  icu.modificarDatosUsuario(nickname, nombre, apellido, fec.getFecha(), null,
				  null, null, urlImg);
	  }
	  if (tipo_usuario.equals("Artista")) {
		  icu.modificarDatosUsuario(nickname, nombre, apellido, fec.getFecha(), descripcion,
				  biografia, web, urlImg);
	  }

  }

  @WebMethod
  public boolean validarUsuario(String nickname, 
      String password) throws UsuarioNoExisteException, PasswordErrorException {
    return icu.validarUsuario(nickname, password);
  }

  @WebMethod
  public void CompraPaquete(String nickName, String nombrePaquete, 
      Date fecha) throws YaComproPaqueteException {
    icu.CompraPaquete(nickName, nombrePaquete, fecha);
  }

  @WebMethod
  public void seguirUsuario(String nickname1, String nickname2) {
    icu.seguirUsuario(nickname1, nickname2);
  }

  @WebMethod
  public void dejarDeSeguirUsuario(String nickname1, String nickname2) {
    icu.dejarDeSeguirUsuario(nickname1, nickname2);
  }

  @WebMethod
  public void cargarDatosPrueba() {
    icu.cargarDatosPrueba();
  }
  
  @WebMethod
  public DataArtista publicarDataArtista() {
    return null;
  }
  
  @WebMethod
  public DataEspectador publicarDataEspectador() {
    return null;
  }

  @WebMethod
  public void finalizarEspectaculo(String nickname, String nombre) throws EstadoEspectaculoException {
	  ice.finalizarEspectaculo(nickname, nombre);
  }
  
  @WebMethod
  public void marcarEspectaculoFavorito(String nickname, String espectaculo) throws UsuarioNoExisteException, EspectaculoNoExisteException {
	  icu.marcarEspectaculoFavorito(nickname, espectaculo);
  }
  
  @WebMethod
  public void desmarcarEspectaculoFavorito(String nickname, String espectaculo) throws UsuarioNoExisteException, EspectaculoNoExisteException {
	  icu.desmarcarEspectaculoFavorito(nickname, espectaculo);
  }
  
  @WebMethod
  public DataEspectaculo[] listarEspectaculosFinalizado(String nickname) {
	  return ice.listarEspectaculoFinalizado(nickname);
  }

  
  @WebMethod
  public DataPremio[] mostrarPremios(String nickname) {
	  DataPremio[] ret = icu.mostrarPremios(nickname);
	  if (ret == null) {
		  ret = new DataPremio[0];
	  }
	  return ret;
  }
  
  @WebMethod
  public DataPuntaje existePuntaje(String espectador, String espectaculo) {
	  DataPuntaje dtP =  icu.existePuntaje(espectador, espectaculo);
	  if (dtP == null) return new DataPuntaje(0, null);
	  return dtP;
  }
  
  @WebMethod
  public void agregarPuntaje(int puntaje, String espectaculo, String espectador) {
	  icu.agregarPuntaje(puntaje, espectaculo, espectador);

  }
  
  @WebMethod
  public boolean habilitadoVal(String espectador, String espectaculo) {
    return ice.habilitadoVal(espectador, espectaculo);
  }
  
  @WebMethod
  public void agregarDatoHistorial(String ip, String url, String browser, String os) {
     icu.agregarDatoHistorial(ip, url, browser, os);
  }
  
  @WebMethod
  public DataDatos[] listarDatosHistorial(){
	  DataDatos[] res = icu.listarDatosHistorial();
	  if (res == null) {
		  return new DataDatos[0];
	  }
	  return res;

  }
  
  @WebMethod
  public boolean validarCodigo(String codigo){
    return icu.validarCodigo(codigo);
  }
  
  
}
