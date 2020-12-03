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

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import database.ArtistasDB;
import database.EspectaculosDB;
import database.FuncionesDB;

public class EspectaculoController implements IEspectaculoController  {
  /**
   * Gets the distance between tw points.
   */
  public void agregarPlataforma(String nombre, String url, String descripcion) 
      throws PlataformaRepetidaException {
    ManejadorPlataforma m = ManejadorPlataforma.getInstance();
    if (m.obtenerPlataforma(nombre) != null) {
      throw new PlataformaRepetidaException("La Plataforma " + nombre + " ya esta registrada");
    }
    Plataforma p = new Plataforma(nombre, url, descripcion);
    m.addPlataforma(p);
  }
  
  /**
  * Gets the distance between tw points.
  */
  public void ingresarDatosFuncion(String nombreFuncion, Date horaInicio, 
      List<String> nickArtistas, Date fechaAlta, String nombreEspectaculo, String urlimg)
          throws FuncionRepetidaException, EspectaculoNoExisteException {
    ManejadorUsuarios mau = ManejadorUsuarios.getManejadorUsuarios();
    ManejadorPlataforma map = ManejadorPlataforma.getInstance();
    String[] nomPlataformas = this.getPlataformas();
    Espectaculo esp = null;
    for (int i = 0; esp == null && i < nomPlataformas.length; i++) {
      esp = map.obtenerPlataforma(nomPlataformas[i]).obtenerEspectaculo(nombreEspectaculo);
    }
   
    if (esp == null) {
      throw new EspectaculoNoExisteException("El espectáculo " + nombreEspectaculo + " no existe"); 
    }
    Map<String, Artista> artistas = mau.getArtistas(nickArtistas);
    if (esp.getFuncion(nombreFuncion) != null) {
      throw new FuncionRepetidaException("La funcion " + nombreFuncion + " ya esta registrada "
          + "en el espectaculo " + nombreEspectaculo);
    }
      
    Funcion f = new Funcion(nombreFuncion, horaInicio, artistas, fechaAlta, esp, urlimg);
    esp.agregarFuncion(f);
  }

  /**
   * Gets the distance between tw points.
   */
  public void crearEspectaculo(String plataforma, String nicknameArtista, String nombre, 
      String descripcion, int duracion, int minEspectadores, int maxEspectadores, 
      String url, int costo, Date fechaRegistro, String[] categorias, String urlImg,
      String descripcionPremio, int cantPremiosPorFuncion, String urlVideo)
          throws EspectaculoRepetidoException, UsuarioNoExisteException,
          MaxEspMenorMinEspException {

    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();

    //Verificar que el nombre no esté en NINGUNA plataforma
    String[] nomPlataformas = this.getPlataformas();
    for (int i = 0; i < nomPlataformas.length; i++) {
      if ((plataformas.obtenerPlataforma(nomPlataformas[i])).obtenerEspectaculo(nombre) != null) {
        throw new EspectaculoRepetidoException("El espectáculo " + nombre + " ya está registrado "
            + "en la plataforma " + nomPlataformas[i]);
      }
    }

    //Verificar que puedan haber espectadores
    if (maxEspectadores < minEspectadores) {
      throw new MaxEspMenorMinEspException("El número mínimo de espectadores debe ser mayor "
          + "o igual que el número máximo");
    }

    //Verificar que exista el artista
    ManejadorUsuarios usuarios = ManejadorUsuarios.getManejadorUsuarios();
    Artista art = usuarios.getArtista(nicknameArtista);
    if (art == null) {
      throw new UsuarioNoExisteException("El artista " + nicknameArtista + " no existe");
    }

    //Crear espectáculo
    Espectaculo esp = new Espectaculo(nombre, descripcion, duracion, minEspectadores, 
        maxEspectadores, url, costo, fechaRegistro, urlImg, descripcionPremio, 
        cantPremiosPorFuncion, urlVideo);

    //Agregar las categorias al espectaculo y viceversa, y controlar que haya al menos una
    if (categorias == null || categorias.length < 1) {
      throw new EspectaculoRepetidoException("No se han ingresado categorías");
    }
    ManejadorCategorias manC = ManejadorCategorias.getInstance();
    for (int i = 0; i < categorias.length; i++) {
      Categoria cat = manC.getCategoria(categorias[i]);
      esp.agregarCategoria(cat);
      cat.addEspectaculo(esp);
    }
    //Agregar el espectáculo a la plataforma, y la función al espectáculo
    Plataforma plat = plataformas.obtenerPlataforma(plataforma);
    plat.agregarEspectaculo(esp);
    art.agregarEspectaculo(esp);
  }

  /**
   * Gets the distance between tw points.
   */
  public String[] listarEspectaculos(String plataforma) {
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    Plataforma plat = plataformas.obtenerPlataforma(plataforma);
    String[] res = plat.listarEspectaculos();
    return res;
  }

  /**
   * Gets the distance between tw points.
   */
  public DataEspectaculo seleccionarEspectaculo(String nombreEspectaculo) 
      throws EspectaculoNoExisteException {
    //Obtener el espectáculo
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    String[] nomPlataformas = this.getPlataformas();
    Espectaculo esp = null;
    for (int i = 0; esp == null && i < nomPlataformas.length; i++) {
      esp = plataformas.obtenerPlataforma(nomPlataformas[i]).obtenerEspectaculo(nombreEspectaculo);
    }
    if (esp == null) {
      throw new EspectaculoNoExisteException("El espectáculo " + nombreEspectaculo + " no existe");
    }
    String[] nomFunciones = esp.listarFunciones();
    String[] nomPaquetes = esp.listarPaquetesAsociados();
    String[] nomCategorias = esp.listarCategorias();
    DataEspectaculo res = new DataEspectaculo(esp.getNombre(), esp.getDescripcion(), 
        esp.getDuracion(), esp.getMinEspectadores(), esp.getMaxEspectadores(), 
        esp.getCosto(), esp.getUrl(), esp.getFechaRegistro(), esp.getEstado().toString(), 
        esp.getUrlImg(), nomFunciones, nomPaquetes, nomCategorias, esp.getPremio(),
        esp.getUrlVideo());
    return res;
  }

  @Override
  public String[] listarFuncionesEspectaculo(String nombrePlataforma, String nombreEspectaculo) {
    ManejadorPlataforma map = ManejadorPlataforma.getInstance();
    Plataforma plat = map.obtenerPlataforma(nombrePlataforma);
    Espectaculo esp = plat.obtenerEspectaculo(nombreEspectaculo);
    if (esp != null) {
      return esp.listarFunciones();
    } else {
      return null;
    }  
  }

  @Override
  public void confirmarRegistroAFuncion(String nombrePlataforma, String nombrePaquete, 
      String nombreEspectaculo, String nombreFuncion, String nombreUsuario, 
      List<String> canjeaRegistros, Date fecha)
          throws FuncionLlenaExepcion, NoEsEspectadorExepcion, 
          EspectadorRegistradoEnFuncionExepcion, FechaPasadaException, 
          RegistroNoSePuedeCanjearException, ErrorCantidadCanjeExcepcion {
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    ManejadorPaquetes paquetes = ManejadorPaquetes.getManejadorPaquetes();
    Paquete pac = paquetes.getPaquete(nombrePaquete);
    Plataforma plat = plataformas.obtenerPlataforma(nombrePlataforma);
    Espectaculo esp = plat.obtenerEspectaculo(nombreEspectaculo);
    Funcion fun = esp.getFuncion(nombreFuncion);

    if (pac != null) {
      if (fecha.before(pac.getFechaDeAlta())) {
        throw new FechaPasadaException("El paquete " + nombrePaquete 
            + " todav�a no fue dado de alta");
      }
      if (fecha.before(pac.getInicioVigencia()) || fecha.after(pac.getFinVigencia())) {
        throw new FechaPasadaException("El paquete " + nombrePaquete + " ha vencido");
      }
    }

    if (fecha.after(fun.getFechaHoraInicio())) {
      throw new FechaPasadaException("La funcion " + fun.getNombre() + " ya ha terminado");
    }
    if (fecha.before(fun.getFechaRegistro())) {
      throw new FechaPasadaException("La funcion " + fun.getNombre() 
      + " todav�a no ha sido registrada");
    }


    if (fun.getEspectaculo().getMaxEspectadores() <= fun.getCantRegistros()) {
      throw new FuncionLlenaExepcion("La funcion " + nombreFuncion 
          + " ha alcanzado su limite maximo");
    }
    ManejadorUsuarios mau = ManejadorUsuarios.getManejadorUsuarios();
    Usuario u = mau.getEspectador(nombreUsuario);
    if (u == null) {
      throw new NoEsEspectadorExepcion("El usuario " + nombreUsuario + " no es un espectador");
    }

    if (((Espectador) u).registradoEnFuncion(nombreFuncion)) {
      throw new EspectadorRegistradoEnFuncionExepcion("El usuario " + nombreUsuario 
          + " ya esta registrado en" + nombreFuncion);
    }

    if (!nombrePaquete.isEmpty() && canjeaRegistros.size() != 0) {
      throw new ErrorCantidadCanjeExcepcion("No se pueden canjear paquetes y registros a la vez");
    }

    Set<Registro> registrosEspectador = ((Espectador) u).getRegistro();

    if (canjeaRegistros.size() != 3 && canjeaRegistros.size() != 0) {
      throw new ErrorCantidadCanjeExcepcion("La cantidad de registros a "
          + "canjear tiene que ser exactamente 3 o 0");
    }

    for (Registro reg : registrosEspectador) {
      if (canjeaRegistros.contains(reg.getFuncion().getNombre())) {
        Funcion func = reg.getFuncion();
        func.setCantRegistros(func.getCantRegistros() - 1);
      }
    }

    ((Espectador) u).registrarAFuncion(fun, nombrePaquete, canjeaRegistros, fecha);
    fun.setCantRegistros(fun.getCantRegistros() + 1);
  } 

  /**
   * Gets the distance between tw points.
   */
  public DataFuncion consultaFuncion(String nombreFuncion, String nombreEspectaculo) 
      throws EspectaculoNoExisteException {
    ManejadorPlataforma map = ManejadorPlataforma.getInstance();
    String[] nomPlataformas = this.getPlataformas();
    Espectaculo esp = null;
    for (int i = 0; esp == null && i < nomPlataformas.length; i++) {
      esp = map.obtenerPlataforma(nomPlataformas[i]).obtenerEspectaculo(nombreEspectaculo);
    }
    if (esp == null) {
      throw new EspectaculoNoExisteException("El espectáculo " + nombreEspectaculo + " no existe"); 
    }
    Funcion fun = esp.getFuncion(nombreFuncion);
    DataFuncion resultado = new DataFuncion(nombreFuncion, fun.getFechaHoraInicio(), 
        fun.getNombresArtistas(), fun.getFechaRegistro(), nombreEspectaculo, fun.getUrlimg(), 
        fun.getFechaSorteo() != null);
    return resultado;
  }

  /**
   * Gets the distance between tw points.
   */
  public void crearPaquete(String nombre, String descripcion, Date inicioVigencia, Date finVigencia,
      int descuento, Date fechaDeAlta, String url) 
          throws PaqueteRepetidoException, FinVigAntesIniVigException {
    ManejadorPaquetes paquetes = ManejadorPaquetes.getManejadorPaquetes();
    if (paquetes.getPaquete(nombre) != null) {
      throw new PaqueteRepetidoException("Ya existe un paquete con nombre " + nombre);
    }
    if (inicioVigencia.after(finVigencia)) {
      throw new FinVigAntesIniVigException("La fecha de fin de la vigencia debe ser mayor "
          + "que la fecha de inicio");
    }
    Paquete pac = new Paquete(nombre, descripcion, inicioVigencia, finVigencia, descuento, 
        fechaDeAlta);
    if (url != null) {
      pac.setUrl(url);
    }
    paquetes.addPaquete(pac);
  }

  /**
   * Gets the distance between tw points.
   */
  public String[] listarPaquetes() {
    ManejadorPaquetes mpac = ManejadorPaquetes.getManejadorPaquetes();
    Paquete[] paquetes = mpac.getPaquetes();
    if (paquetes != null) {
      String[] res = new String[paquetes.length];
      for (int i = 0; i < paquetes.length; i++) {
        res[i] = paquetes[i].getNombre();
      }
      return res;
    } else {
      return null;
    }
  }


  /**
   * Gets the distance between tw points.
   */
  public String[] listarEspectaculosEnPaquete(String nomPlataforma, String nomPaquete) {
    //ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    ManejadorPaquetes paquetes = ManejadorPaquetes.getManejadorPaquetes();
    //Plataforma p = plataformas.obtenerPlataforma(nomPlataforma);
    Paquete paq = paquetes.getPaquete(nomPaquete);
    Espectaculo[] esps = paq.getEspectaculos();
    List<String> res = new ArrayList<String>();
    if (esps != null && esps.length != 0) {
      for (int i = 0; i < esps.length; i++) {
    	if (esps[i].getEstado() == EstadoEspectaculo.Aceptado) {
    		res.add(esps[i].getNombre()); 
    	}
      }
    }
    return res.toArray(new String[0]);
  }


  /**
   * Gets the distance between tw points.
   */
  public String[] listarEspectaculosNoEnPaquete(String nomPlataforma, String nomPaquete) {
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    String[] espectaculos = plataformas.obtenerPlataforma(nomPlataforma).listarEspectaculos();
    ManejadorPaquetes paquetes = ManejadorPaquetes.getManejadorPaquetes();
    Paquete pac = paquetes.getPaquete(nomPaquete);
    Set<String> setRes = new HashSet<String>();
    for (int i = 0; i < espectaculos.length; i++) {
      if (pac.getEspectaculo(espectaculos[i]) == null) {
        setRes.add(espectaculos[i]); 
      }
    }
    if (setRes.size() == 0) {
      return null;
    }
    String[] res = new String[setRes.size()];
    return setRes.toArray(res);
  }

  /**
   * Gets the distance between tw points.
   */
  public void agregarEspectaculoAPaquete(String nomPlataforma, String nomEspectaculo, 
      String nomPaquete) {
    ManejadorPaquetes paquetes = ManejadorPaquetes.getManejadorPaquetes();
    Paquete pac = paquetes.getPaquete(nomPaquete);
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    Espectaculo e = plataformas.obtenerPlataforma(nomPlataforma).obtenerEspectaculo(nomEspectaculo);
    pac.addEspectaculo(e);
  }

  /**
   * Gets the distance between tw points.
   */
  public String[] getPlataformas() {
    ManejadorPlataforma map = ManejadorPlataforma.getInstance();
    Plataforma[] plataformas = map.getPlataformas();
    if (plataformas != null) {
      int tamanio = plataformas.length;
      String[] res = new String[tamanio];
      for (int i = 0; i < tamanio; i++) {
        res[i] = plataformas[i].getNombre();
      }
      return res;
    } else {
      return null;
    }
  }

  /**
   * Gets the distance between tw points.
   */
  public DataPaquete seleccionarPaquete(String nomPaquete) {
    ManejadorPaquetes mpaq = ManejadorPaquetes.getManejadorPaquetes();
    Paquete pac = mpaq.getPaquete(nomPaquete);
    Espectaculo[] espectaculos = pac.getEspectaculos();
    String[] nomEspectaculos = null;
    if (espectaculos != null) {
      nomEspectaculos = new String[espectaculos.length];
      for (int i = 0; i < espectaculos.length; i++) {
        nomEspectaculos[i] = espectaculos[i].getNombre();
      }
    }
    DataPaquete dtP = new DataPaquete(pac.getNombre(), pac.getDescripcion(), 
        pac.getInicioVigencia(), pac.getFinVigencia(), pac.getDescuento(), 
        pac.getFechaDeAlta(), nomEspectaculos, pac.getUrl(), pac.listarCategorias());
    return dtP;
  }

  /**
   * Gets the distance between tw points.
   */
  public DataEspectaculo seleccionarEspectaculoDePaquete(String nomPaquete, String nomEspectaculo) {
    ManejadorPaquetes map = ManejadorPaquetes.getManejadorPaquetes();
    Espectaculo esp = map.getPaquete(nomPaquete).getEspectaculo(nomEspectaculo);
    String[] nomFunciones = esp.listarFunciones();
    String[] nomPaquetes = esp.listarPaquetesAsociados();
    String[] nomCategorias = esp.listarCategorias();
    DataEspectaculo dtE = new DataEspectaculo(esp.getNombre(), esp.getDescripcion(), 
        esp.getDuracion(), esp.getMinEspectadores(), esp.getMaxEspectadores(), 
        esp.getCosto(), esp.getUrl(), esp.getFechaRegistro(), esp.getEstado().toString(), 
        esp.getUrlImg(), nomFunciones, nomPaquetes, nomCategorias, esp.getPremio(),
        esp.getUrlVideo());
    return dtE;
  }

  /**
   * Gets the distance between tw points.
   */
  public void ingresarDatosCategoria(String nombreCategoria) {
    Categoria categoria = new Categoria(nombreCategoria);
    ManejadorCategorias mac = ManejadorCategorias.getInstance();
    mac.agregarCategoria(categoria);
  }

  /**
   * Gets the distance between tw points.
   */
  public DataPaquete[] listarPaqueteValidos(Date fecha) {
    Set<DataPaquete> res = new HashSet<DataPaquete>();
    ManejadorPaquetes map = ManejadorPaquetes.getManejadorPaquetes();
    Paquete[] pac = map.getPaquetes();
    for (int i = 0; i < pac.length; i++) {
      if (pac[i].getFinVigencia().after(fecha)) {
        int cantPaq = pac.length;
        if (pac[i].getEspectaculos() != null) {
          int cantEsp = pac[i].getEspectaculos().length;
          String[] esp = new String[cantPaq];
          for (int k = 0; k < cantEsp; k++) {
            esp[k] = pac[i].getEspectaculos()[k].getNombre();
          }
          res.add(new DataPaquete(pac[i].getNombre(), pac[i].getDescripcion(), 
              pac[i].getInicioVigencia(), pac[i].getFinVigencia(), pac[i].getDescuento(), 
              pac[i].getFechaDeAlta(), esp, pac[i].getUrl(), pac[i].listarCategorias()));
        } else {
          res.add(new DataPaquete(pac[i].getNombre(), pac[i].getDescripcion(), 
              pac[i].getInicioVigencia(), pac[i].getFinVigencia(), pac[i].getDescuento(), 
              pac[i].getFechaDeAlta(), null, pac[i].getUrl(), pac[i].listarCategorias()));
        }
      }
    }
    Object[] obj = res.toArray();
    DataPaquete[] resA = new DataPaquete[obj.length];
    for (int i = 0; i < obj.length; i++) {
      resA[i] = (DataPaquete) obj[i];
    }
    return resA;
  }

  /**
   * Gets the distance between tw points.
   */
  public String[] listarEspectaculosCategoria(String categoria) {
    ManejadorCategorias categorias = ManejadorCategorias.getInstance();
    Categoria cat = categorias.getCategoria(categoria);
    String[] res = cat.listarEspectaculos();
    return res;
  }

  /**
   * Gets the distance between tw points.
   */
  public String[] listarEspectaculosEstadoIngresado() {
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    String[] nomPlataformas = this.getPlataformas();
    //Calcular tamanio del array resultado
    int tamanio = 0;
    for (int i = 0; i < nomPlataformas.length; i++) {
      Plataforma plat = plataformas.obtenerPlataforma(nomPlataformas[i]);
      if (plat.listarEspectaculosEstadoIngresado() != null) {
        tamanio += plat.listarEspectaculosEstadoIngresado().length; 
      }
    }
    //Retornar null si no hay ningún espectaculo en estado INGRESADO
    if (tamanio == 0) {
      return null;
    }
    //Crear array resultado
    String[] res = new String [tamanio];
    int kakaroto = 0;
    for (int i = 0; i < nomPlataformas.length; i++) {
      Plataforma plat = plataformas.obtenerPlataforma(nomPlataformas[i]);
      if (plat.listarEspectaculosEstadoIngresado() != null) {
        String[] esps = plat.listarEspectaculosEstadoIngresado();
        for (int j = 0; j < esps.length; j++) {
          res[kakaroto] = esps[j];
          kakaroto++;
        }
      }
    }
    return res;
  }

  //Si el espectáculo pasa a estado ACEPTADO, ingresar TRUE. Si pasa a RECHAZADO, FALSE.
  /**
   * Gets the distance between tw points.
   */
  public void aceptarEspectaculo(String nomEspectaculo, boolean aceptado) 
      throws EspectaculoNoExisteException {
    //Obtener el espectáculo
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    String[] nomPlataformas = this.getPlataformas();
    Espectaculo esp = null;
    for (int i = 0; esp == null && i < nomPlataformas.length; i++) {
      esp = plataformas.obtenerPlataforma(nomPlataformas[i]).obtenerEspectaculo(nomEspectaculo);
    }
    //Si no existe en ninguna plataforma tirar excepción
    if (esp == null) {
      throw new EspectaculoNoExisteException("El espectáculo " + nomEspectaculo + " no existe"); 
    }
    //Cambiar estado del espectaculo
    if (aceptado) {
      esp.setEstado(EstadoEspectaculo.Aceptado); 
    } else {
      esp.setEstado(EstadoEspectaculo.Rechazado); 
    }
  }

  /**
   * Gets the distance between tw points.
   */
  public String[] listarCategorias() {
    ManejadorCategorias mcat = ManejadorCategorias.getInstance();
    Categoria[] categorias = mcat.getCategorias();
    if (categorias != null) {
      String[] res = new String[categorias.length];
      for (int i = 0; i < categorias.length; i++) {
        res[i] = categorias[i].getNombre();
      }
      return res;
    } else {
      return null;
    }
  }

  /**
   * Gets the distance between tw points.
   */
  public DataPlataforma seleccionarPlataforma(String nombre) {
    Plataforma plat = ManejadorPlataforma.getInstance().obtenerPlataforma(nombre);
    DataPlataforma dtp = new DataPlataforma(plat.getNombre(), plat.getDescripcion(), 
        plat.listarEspectaculos());
    return dtp;
  }

  /**
   * Gets the distance between tw points.
   */
  public Object[] busqueda(String clave) {
    Plataforma[] plats = ManejadorPlataforma.getInstance().getPlataformas();
    Set<DataEspectaculo> espectaculos = new HashSet<DataEspectaculo>();
    for (Plataforma plat: plats) {
      for (String e: plat.listarEspectaculos()) {
        DataEspectaculo esp;
        try {
          esp = this.seleccionarEspectaculo(e);
          if (esp.getNombre().toLowerCase().contains(clave.toLowerCase()) || esp.getDescripcion().toLowerCase().contains(clave.toLowerCase())) {
            espectaculos.add(esp);
          }
        } catch (EspectaculoNoExisteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
    Set<DataPaquete> paquetes = new HashSet<DataPaquete>();
    for (Paquete p: ManejadorPaquetes.getManejadorPaquetes().getPaquetes()) {
      if (p.getNombre().toLowerCase().contains(clave.toLowerCase()) || p.getDescripcion().toLowerCase().contains(clave.toLowerCase())) {
        paquetes.add(this.seleccionarPaquete(p.getNombre()));
      }
    }
    Object[] res = null;
    if (!espectaculos.isEmpty() && !paquetes.isEmpty()) {
      res = new Object[espectaculos.size() + paquetes.size()];
      Object[] arrEsp = espectaculos.toArray();
      Object[] arrPaq = paquetes.toArray();
      for (int i = 0; i < arrEsp.length; i++) {
        res[i] = arrEsp[i];
      }
      for (int i = arrEsp.length; i < arrPaq.length + arrEsp.length; i++) {
        res[i] = arrPaq[i - arrEsp.length];
      }
    } else if (!paquetes.isEmpty()) {
      res = paquetes.toArray();
    } else if (!espectaculos.isEmpty()) {
      res = espectaculos.toArray();
    }
    return res;
  }

  /**
  * Dada una funcion, sortea los premios para la misma.
  */
  public void sortearPremios(String nombrePlataforma, String nombreEspectaculo,
          String nombreFuncion, Date fecha) throws FechaPasadaException {
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    Plataforma plat = plataformas.obtenerPlataforma(nombrePlataforma);
    Espectaculo esp = plat.obtenerEspectaculo(nombreEspectaculo);
    Funcion fun = esp.getFuncion(nombreFuncion);
    
    if (fun.getFechaSorteo() != null) {
      throw new FechaPasadaException("La funcion " + nombreFuncion 
            + " ya ha sorteado premios");
    }
    
    if (fecha.before(fun.getFechaHoraInicio())) {
      //Cuando se lanza esta exception hay que hacer un catch y mostrar
      //los espectadores que ganaron.
      throw new FechaPasadaException("La funcion " + nombreFuncion 
            + " todavìa no ha terminado");
    }
    fun.setFechaSorteo(fecha);
    fun.sortearPremios();
  }
  
  /**
  * Retorna la cantidad de veces que un espectaculo fue marcado como favorito.
  */
  public int cantVecesEspectaculoFavorito(String espectaculo) throws EspectaculoNoExisteException {
    //Obtener todos los espectadores
    Espectador[] espectadores = ManejadorUsuarios.getManejadorUsuarios().getEspectadores();
    //Contar los espectadores que tienen al espectáculo como favorito
    int res = 0;
    if (espectadores != null) {
      for (Espectador usu: espectadores) {
        if (usu.getEspectaculosFavoritos() != null) {
          for (String nomEsp: usu.getEspectaculosFavoritos()) {
            if (nomEsp.equals(espectaculo)) {
              res++;
            }
          }
        }
      }
    }
    return res;

  }
  
  /**
  * Finaliza un espectaculo.
  */
  public void finalizarEspectaculo(String nickname, String nombre)
        throws EstadoEspectaculoException {
    ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
    Artista art = mus.getArtista(nickname);
    Espectaculo esp = art.getEspectaculo(nombre);
    Plataforma plat = null;
    for (Plataforma pla: ManejadorPlataforma.getInstance().getPlataformas()) {
    	if (pla.listarEspectaculos() != null) {
	    	for (String espPlat: pla.listarEspectaculos()) {
	    		if(espPlat.equals(nombre)) {
	    			plat = pla;
	    		}
	    	}
    	}
    }

    if (esp.getEstado() == EstadoEspectaculo.Aceptado) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("ctUy-ServidorCentral");
  	  EntityManager em = emf.createEntityManager();
  	  try {
  		TypedQuery<EspectaculosDB> select = em.createQuery("SELECT a FROM EspectaculosDB a WHERE a.nombre = :nombre",
  				EspectaculosDB.class);
		select.setParameter("nombre", nombre);
		List<EspectaculosDB> espectaculoExiste = select.getResultList();
		if (espectaculoExiste.isEmpty()) {
	  		em.getTransaction().begin();
	  		
	  		EspectaculosDB espDB = new EspectaculosDB(esp.getNombre(), esp.getDescripcion(),
	  				esp.getDuracion(), esp.getCosto(), esp.getFechaRegistro(), new Date(),
	  				esp.getUrl(), plat.getNombre());
	  		
	  		TypedQuery<ArtistasDB> queryArt =
	  				em.createQuery("SELECT a FROM ArtistasDB a WHERE a.nickname = :nick",
	  				ArtistasDB.class);
			queryArt.setParameter("nick", nickname);
			List<ArtistasDB> artistaExiste = queryArt.getResultList();
			ArtistasDB artDB;
			if (artistaExiste.isEmpty()) {
		  		artDB = new ArtistasDB(art.getNickname(), art.getEmail(), art.getNombre(),
		  				art.getApellido(), Date.from(art.getFecha().atZone(ZoneId.systemDefault()).toInstant()));
			} else {
				artDB = queryArt.getSingleResult();
			}
		  	Collection<FuncionesDB> artFuncs = artDB.getFunciones();
	  		List<FuncionesDB> funcsDB = new ArrayList<>();
	  		if (esp.listarFunciones() != null) {
		  		for (String fun: esp.listarFunciones()) {
		  			Funcion funcion = esp.getFuncion(fun);
		  			FuncionesDB funDB = new FuncionesDB(funcion.getNombre(), funcion.getFechaHoraInicio(),
		  					funcion.getFechaRegistro(), espDB, artDB);
		  			artFuncs.add(funDB);
		  			funcsDB.add(funDB);
		  		}
	  		}
	  		artDB.setFunciones(artFuncs);
	  		espDB.setFunciones(funcsDB);
	  		
	  		em.persist(espDB);
			em.flush();
	  		em.getTransaction().commit();
		}
  	  } catch (Exception e) {
  		e.printStackTrace();
  		em.getTransaction().rollback();
  	  } finally {
  		
  		em.close();
  		emf.close();
  	  }
      esp.setEstado(EstadoEspectaculo.Finalizado);
    } else {
      throw new EstadoEspectaculoException("El espectaculo todavia no ha sido "
            + "aceptado o ya fue finalizado");
    }
  }
  
  /**
  * Retorna un array de los nombres de los espectaculos finalizados.
  */
  public DataEspectaculo[] listarEspectaculoFinalizado(String nickname) {
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ctUy-ServidorCentral");
	  EntityManager em = emf.createEntityManager();
	  DataEspectaculo[] dtEsp = new DataEspectaculo[0];
	  try {
		  TypedQuery<EspectaculosDB> select = 
				  em.createQuery("SELECT distinct e FROM EspectaculosDB e, FuncionesDB f, ArtistasDB a "
				  		+ "WHERE e.id = f.id_espectaculo.id AND f.id_artista.id = a.id AND a.nickname = :nick",
	  				EspectaculosDB.class);
		  select.setParameter("nick", nickname);
		  List<EspectaculosDB> especs = select.getResultList();
		  if (especs != null) {
			    dtEsp = new DataEspectaculo[especs.size()];
			    for (int i = 0; i < especs.size(); i++) {
			    	List<String> nomFuncs = new ArrayList<>();
			    	for(FuncionesDB fun: especs.get(i).getFunciones()) {
			    		nomFuncs.add(fun.getNombre());
			    	}
			    	  DataEspectaculo des = new DataEspectaculo(especs.get(i).getNombre(), 
			    			  especs.get(i).getDescripcion(), especs.get(i).getDuracion(), 
			    			  0, 0, especs.get(i).getCosto(), especs.get(i).getUrl(),
			    			  especs.get(i).getFechaAlta(), "Finalizado", null, nomFuncs.toArray(new String[0]), null,
			    			  null, null, null);
			    	  dtEsp[i] = des;
			      }
			    }
	  } catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	  } finally {
		em.close();
		emf.close();
	  }
		/*
		 * Espectaculo[] especs = ManejadorUsuarios.getManejadorUsuarios()
		 * .getArtista(nickname).getEspectaculos(); //List<String> nomEspec = new
		 * ArrayList<String>(); List<DataEspectaculo> esp = new
		 * ArrayList<DataEspectaculo>(); DataEspectaculo [] ret; //String[] ret = null;
		 * if (especs != null) { for (int i = 0; i < especs.length; i++) { if
		 * (especs[i].getEstado() == EstadoEspectaculo.Finalizado) {
		 * 
		 * // nomEspec.add(especs[i].getNombre()); DataEspectaculo des = new
		 * DataEspectaculo(especs[i].getNombre(), especs[i].getDescripcion(),
		 * especs[i].getDuracion(), especs[i].getMinEspectadores(),
		 * especs[i].getMaxEspectadores(), especs[i].getCosto(), especs[i].getUrl(),
		 * especs[i].getFechaRegistro(), especs[i].getEstado().toString(),
		 * especs[i].getUrlImg(), especs[i].listarFunciones(),
		 * especs[i].listarPaquetesAsociados(),especs[i].listarCategorias(),
		 * especs[i].getPremio(), especs[i].getUrlVideo()); esp.add(des); } } } ret =
		 * new DataEspectaculo[esp.size()]; for(int i = 0; i < esp.size(); i++) { ret[i]
		 * = esp.get(i); } //if (nomEspec.size() != 0) { // ret = nomEspec.toArray(new
		 * String[0]); //}
		 */
	  return dtEsp;
  }
  
  /**
  * Retorna un array de tamaño 5 donde la posicion i contiene la cantidad
  * de veces que el espectaculo fue valorado con puntacion i+1.
  */
  public int[] valoracionEspectaculo(String nombreEspectaculo) {
    Plataforma[] plataformas = ManejadorPlataforma.getInstance().getPlataformas();
    Espectaculo espectaculo = null;
    Boolean terminado = false;
    for (int i = 0; i < plataformas.length && !terminado; i++) {
      if (plataformas[i].obtenerEspectaculo(nombreEspectaculo) != null) {
        espectaculo = plataformas[i].obtenerEspectaculo(nombreEspectaculo);
        terminado = true;
      }
    }
    int[] res = new int[5];
    List<Puntaje> puntajes = espectaculo.getPuntajes();
    for (int i = 0; i < puntajes.size(); i++) {
      int valoracion = puntajes.get(i).getPuntaje();
      if (valoracion == 5) {
        res[4] = res[4] + 1;
      } else {
        if (valoracion == 4) {
          res[3] = res[3] + 1;
        } else {
          if (valoracion == 3) {
            res[2] = res[2] + 1;
          } else {
            if (valoracion == 2) {
              res[1] = res[1] + 1;
            } else {
              res[0] = res[0] + 1;
            }
          }
        }
      }
    }
    return res;
  }
  
  public String[] getEspectadoresRegistrados(String funcion, String espectaculo) {
    Plataforma[] plataformas = ManejadorPlataforma.getInstance().getPlataformas();
    Plataforma plataforma = null;
    boolean terminado = false;
    for (int i = 0; i < plataformas.length && !terminado; i++) {
      if (plataformas[i].obtenerEspectaculo(espectaculo) != null) {
        plataforma = plataformas[i];
        terminado = true;  
      }
    }
    Funcion funcionBuscada = plataforma.obtenerEspectaculo(espectaculo).getFuncion(funcion);
    Set<Registro> registros = funcionBuscada.getRegistros();
    Registro[] registrosArray = registros.toArray(new Registro[0]);
    String[] espectadores = new String[registros.size()];
    for (int i = 0; i < registros.size(); i++) {
      espectadores[i] = registrosArray[i].getUsuario();
    }
    return espectadores;
  }
  
  public String[] getEspectadoresGanadores(String funcion, String espectaculo) {
    Plataforma[] plataformas = ManejadorPlataforma.getInstance().getPlataformas();
    Plataforma plataforma = null;
    boolean terminado = false;
    for (int i = 0; i < plataformas.length && !terminado; i++) {
      if (plataformas[i].obtenerEspectaculo(espectaculo) != null) {
        plataforma = plataformas[i];
        terminado = true;  
      }
    }
    Funcion funcionBuscada = plataforma.obtenerEspectaculo(espectaculo).getFuncion(funcion);
    Set<Registro> registros = funcionBuscada.getRegistros();
    Registro[] registrosArray = registros.toArray(new Registro[0]);
    int cont = 0;
    for (int i = 0; i < registros.size(); i++) {
      if (registrosArray[i].getPremio()) {
        cont++;
      }
    }
    String[] espectadoresGanadores = new String[cont];
    int voyEn = 0;
    for (int i = 0; i < registros.size(); i++) {
      if (registrosArray[i].getPremio()) {
        espectadoresGanadores[voyEn] = registrosArray[i].getUsuario();
        voyEn++;
      }
    }
    return espectadoresGanadores;
  }
  
  public boolean habilitadoVal(String espectador, String espectaculo) {
    boolean habilitado = false;
    Espectador esp = ManejadorUsuarios.getManejadorUsuarios().getEspectador(espectador);
    Date fechaActual = new Date();
    if (esp != null) {
      Registro[] reg = esp.getRegistro().toArray(new Registro[0]);
      for (int i = 0; i < reg.length && !habilitado; i++) {
        Funcion fun = reg[i].getFuncion();
        if (fun.getFechaHoraInicio().compareTo(fechaActual) < 0) {
          if (fun.getEspectaculo().getNombre().equals(espectaculo)) {
            habilitado = true;  
          }
        }
      }
    }
    return habilitado;
  }
  
  /**
  * Dada una funcion, sortea los premios para la misma. Se conocen los ganadores de antemano.
  */
  public void sortearPremiosArreglado(String nombrePlataforma, String nombreEspectaculo,
          String nombreFuncion, Date fecha, String[] ganadores) throws FechaPasadaException {
    ManejadorPlataforma plataformas = ManejadorPlataforma.getInstance();
    Plataforma plat = plataformas.obtenerPlataforma(nombrePlataforma);
    Espectaculo esp = plat.obtenerEspectaculo(nombreEspectaculo);
    Funcion fun = esp.getFuncion(nombreFuncion);
    
    if (fun.getFechaSorteo() != null) {
      throw new FechaPasadaException("La funcion " + nombreFuncion 
            + " ya ha sorteado premios");
    }
    
    if (fecha.before(fun.getFechaHoraInicio())) {
      //Cuando se lanza esta exception hay que hacer un catch y mostrar
      //los espectadores que ganaron.
      throw new FechaPasadaException("La funcion " + nombreFuncion 
            + " todavìa no ha terminado");
    }
    fun.setFechaSorteo(fecha);
    fun.sortearPremiosArreglado(ganadores);
  }
}