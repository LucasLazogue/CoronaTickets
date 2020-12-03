package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import logica.DataEspectaculo;
import logica.DataFuncion;
import logica.DataPaquete;
import logica.DataRegistro;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectaculoRepetidoException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.FechaPasadaException;
import exepciones.FuncionLlenaExepcion;
import exepciones.FuncionRepetidaException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PaqueteRepetidoException;
import exepciones.PasswordErrorException;
import exepciones.PlataformaRepetidaException;
import exepciones.RegistroNoSePuedeCanjearException;
import exepciones.UsuarioNoExisteException;
import exepciones.UsuarioRepetidoExepcion;
import exepciones.YaComproPaqueteException;
import exepciones.FinVigAntesIniVigException;

class EspectaculoControllerTest {

  private static IEspectaculoController ice;

  @BeforeAll
  public static void iniciar() {
    Fabrica fab = Fabrica.getInstancia();
    ice = fab.getIEspectaculoController();
  }

  @Test
  void testAgregarPlataformaOk() {
    String nomPlat = "platagplatok";
    try {
      ice.agregarPlataforma(nomPlat, "www.platagplatok.com", "descripcion");
      String[] plats = ice.getPlataformas();
      for (int i = 0; i < plats.length; i++) {
        if (plats[i] == nomPlat) {
          assertTrue(true);
        }    
      }
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testAgregarPlataformaRepetida() {
    String nomPlat = "platagplatrep";
    try {
      ice.agregarPlataforma(nomPlat, "www.platagplatrep.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(PlataformaRepetidaException.class, () -> { 
      ice.agregarPlataforma(nomPlat, "www.platagplatrep.com", "descripcion"); 
    });
  }

  @Test
  void testIngresarDatosFuncion() {
    //Crear Plataforma
    String nombrePlataforma = "YouTubeIngDatFunc";
    try {
      ice.agregarPlataforma(nombrePlataforma, nombrePlataforma + ".com", "default description");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Artista organizador
    String artOrgNick = "ArtFunOrgIngDatFunc";
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    try {
      icu.nuevoArtista(artOrgNick, artOrgNick + ".com", "Art", "Org",
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia", artOrgNick + ".com",
          "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categoría
    String cat = "IdfCat";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear Espectaculo
    String nombreEspectaculo = "EspFunTestIngDatFunc";
    Date fechaEspReg = Date.valueOf(LocalDate.now());
    try {
      ice.crearEspectaculo(nombrePlataforma, artOrgNick, nombreEspectaculo, "descripcion", 10, 1,
          100, nombreEspectaculo + ".com", 1, fechaEspReg, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException
        | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Artistas
    String artInvNick1 = "ArtFun1IngDatFunc";
    String artInvNick2 = "ArtFun2IngDatFunc";
    try {
      icu.nuevoArtista(artInvNick1, artInvNick1 + ".com", "Art", "Org",
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia",
          artInvNick1 + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    try {
      icu.nuevoArtista(artInvNick2, artInvNick2 + ".com", "Art", "Org",
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia",
          artInvNick2 + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    List<String> artInv = new LinkedList<String>();
    artInv.add(0, artInvNick1);
    artInv.add(1, artInvNick2);
    //TEST
    try {
      String nombreFuncion = "FunTestNomIngDatFunc";
      Date horaInicio = Date.valueOf(LocalDate.now());
      Date fechaAlta = Date.valueOf(LocalDate.now());
      String urlimg = "http://test.com";
      ice.ingresarDatosFuncion(nombreFuncion, horaInicio, artInv, fechaAlta,
          nombreEspectaculo, urlimg);
      DataFuncion dataF = ice.consultaFuncion(nombreFuncion, nombreEspectaculo);
      assertEquals(dataF.getNombre(), nombreFuncion);
      assertEquals(dataF.getEspectaculo(), nombreEspectaculo);
      assertEquals(dataF.getFechaAlta(), fechaAlta);
      assertEquals(dataF.getFechaHoraInicio(), horaInicio);
      for (int i = 0; i < dataF.getArtistas().length; i++) {
        assertTrue(dataF.getArtistas()[i] == artInvNick1 || dataF.getArtistas()[i] == artInvNick2);
      }
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testCrearEspectaculoOk() {
    //Crear artista organizador de espectáculo
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "art123";
    try {
      icu.nuevoArtista(nomArt, "art123@mail.com", "Juan", "Perez",
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion",
          "descripcion", "www.art123.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea el espectáculo
    String nomPlat = "plat123";
    try {
      ice.agregarPlataforma(nomPlat, "www.plat123.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorías
    String cat = "CatCrearEspOk";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //TEST
    try {
      String nomEsp = "esp123";
      String desc = "descripcion espectaculo";
      int dur = 120;
      int minEsp = 50;
      int maxEsp = 150;
      String url = "www.esp123.com";
      int costo = 500;
      Date freg = Date.valueOf(LocalDate.now());
      String img = "web.com/esp123.png";
      String descPremio = "Premio";
      int cantPremios = 5;
      String urlVideo = "youtube.com/video";
      ice.crearEspectaculo(nomPlat, nomArt, nomEsp, desc,
          dur, minEsp, maxEsp, url, costo, freg, cats, img, descPremio, cantPremios,
          urlVideo);
      //Busco en la lista del artista para corroborar que quedó insertado
      boolean estaEnArt = icu.listarEspectaculosDeArtista(nomArt).containsKey(nomEsp);
      if (!estaEnArt) {
        fail("No se ingresó en el artista " + nomArt);
      }
      //Busco el espectáculo en la plataforma y corroboro los datos
      DataEspectaculo desp = ice.seleccionarEspectaculo("esp123");
      assertEquals(nomEsp, desp.getNombre());
      assertEquals(desc, desp.getDescripcion());
      assertEquals(dur, desp.getDuracion());
      assertEquals(minEsp, desp.getMinEspectadores());
      assertEquals(maxEsp, desp.getMaxEspectadores());
      assertEquals(url, desp.getUrl());
      assertEquals(costo, desp.getCosto());
      assertEquals(freg, desp.getFechaRegistro());
      assertEquals(img, desp.getUrlImg());
      assertEquals("Ingresado", desp.getEstado());
      assertEquals(descPremio, desp.getPremio().getDescripcion());
      assertEquals(cantPremios, desp.getPremio().getCantPremiosPorFuncion());
      assertEquals(urlVideo, desp.getUrlVideo());
    } catch (EspectaculoNoExisteException | EspectaculoRepetidoException
        | UsuarioNoExisteException | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testCrearEspectaculoMaxEspMenorMinEsp() {
    //Crear artista organizador de espectáculo
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "artmaxespminesp";
    try {
      icu.nuevoArtista(nomArt, "artmaxespminesp@mail.com", "Juan", "Perez", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", 
          "descripcion", "www.artmaxespminesp.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea el espectáculo
    String nomPlat = "platmaxespminesp";
    try {
      ice.agregarPlataforma(nomPlat, "www.platmaxespminesp.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorias
    String cat = "CatCrearEspMaxEMenorMimE";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //TEST
    Date freg = Date.valueOf(LocalDate.now());
    assertThrows(MaxEspMenorMinEspException.class, () -> {
      ice.crearEspectaculo(nomPlat, nomArt, "espmaxespminesp", "descripción", 150, 
          500, 499, "www.espectaculo.com", 1200, freg, cats, "img", "premio", 0,
          "urlVideo");
    });
  }

  @Test
  void testCrearEspectaculoRepetido() {
    //Crear artista organizador de espectáculo
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "art109";
    try {
      icu.nuevoArtista(nomArt, "art109@mail.com", "Juan", "Perez",
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion",
          "descripcion", "www.art109.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea un espectáculo
    String nomPlat = "plat109";
    try {
      ice.agregarPlataforma(nomPlat, "www.plat109.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorias
    String cat = "CatCrearEspRep";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear espectáculo para luego volver a intentar crearlo
    String nomEsp = "esp109";
    String desc = "descripcion espectaculo";
    int dur = 120;
    int minEsp = 50;
    int maxEsp = 150;
    String url = "www.esp109.com";
    int costo = 500;
    Date freg = Date.valueOf(LocalDate.now());
    try {
      ice.crearEspectaculo(nomPlat, nomArt, nomEsp, desc, dur, minEsp,
          maxEsp, url, costo, freg, cats, "img", "premio", 0, 
          "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException
        | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(EspectaculoRepetidoException.class, () -> {
      ice.crearEspectaculo(nomPlat, nomArt, nomEsp, "Otra descripción", 111, 50, 
          5000, "www.espectaculo.com", 1200, freg, cats, "img2", "premio", 0, 
          "urlVideo");
    });
  }

  @Test
  void testCrearEspectaculoDeArtistaNoExistente() {
    //Crear plataforma que hostea un espectáculo
    String nomPlat = "plat693";
    try {
      ice.agregarPlataforma(nomPlat, "www.plat693.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorias
    String cat = "CatCrearEspArtNoEx";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear espectáculo con artista no existente
    String nomEsp = "esp693";
    String desc = "descripcion espectaculo";
    int dur = 120;
    int minEsp = 50;
    int maxEsp = 150;
    String url = "www.esp693.com";
    int costo = 500;
    Date freg = Date.valueOf(LocalDate.now());
    assertThrows(UsuarioNoExisteException.class, () -> {
      ice.crearEspectaculo(nomPlat, "artistaNoExistente", nomEsp, desc, dur, minEsp,
          maxEsp, url, costo, freg, cats, "img", "premio", 0, "urlVideo");
    });
  }

  @Test
  void testListarEspectaculosOk() {
    //Crear artista organizador de espectáculos
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "artParaListarEspectaculos";
    try {
      icu.nuevoArtista(nomArt, "artparalistarespectaculos@mail.com", "Juan", "Perez",
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", "descripcion", 
          "www.artparalistarespectaculos.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea los espectáculos
    String nomPlat = "plat para listar espectaculos";
    try {
      ice.agregarPlataforma(nomPlat, "www.platparalistarespectaculos.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorias
    String cat = "CatListarEspOk";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear lista de espectaculos para listar
    Date freg = Date.valueOf(LocalDate.now());
    String nomEspGral = "esp para listar espectaculos ";
    try {
      //Acepto algunos, rechazo otros y dejo otros en estado ingresado
      ice.crearEspectaculo(nomPlat, nomArt, nomEspGral + "1", nomEspGral, 100,
          1, 100, "www.esps.com", 1000, freg, cats, "img1", "premio", 0, "urlVideo");
      ice.aceptarEspectaculo(nomEspGral + "1", true);
      ice.crearEspectaculo(nomPlat, nomArt, nomEspGral + "2", nomEspGral, 100,
          1, 100, "www.esps.com", 1000, freg, cats, "img2", "premio", 0, "urlVideo");
      ice.aceptarEspectaculo(nomEspGral + "2", true);
      ice.crearEspectaculo(nomPlat, nomArt, nomEspGral + "3", nomEspGral, 100,
          1, 100, "www.esps.com", 1000, freg, cats, "img3", "premio", 0, "urlVideo");
      ice.aceptarEspectaculo(nomEspGral + "3", false);
      ice.crearEspectaculo(nomPlat, nomArt, nomEspGral + "4", nomEspGral, 100,
          1, 100, "www.esps.com", 1000, freg, cats, "img4", "premio", 0, "urlVideo");
      ice.crearEspectaculo(nomPlat, nomArt, nomEspGral + "5", nomEspGral, 100,
          1, 100, "www.esps.com", 1000, freg, cats, "img5", "premio", 0, "urlVideo");
      ice.aceptarEspectaculo(nomEspGral + "5", true);
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException
        | MaxEspMenorMinEspException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    String[] listaEsp = ice.listarEspectaculos(nomPlat);
    for (int i = 0; i < listaEsp.length; i++) {
      assertTrue(listaEsp[i].equals(nomEspGral + "1") || listaEsp[i].equals(nomEspGral + "2")
          || listaEsp[i].equals(nomEspGral + "5"));
    }
  }

  @Test
  void testListarEspectaculosVacio() {
    //Crear plataforma que no va a hostear espectáculos
    String nomPlat = "plat para listar espectaculos pero lista vacía";
    try {
      ice.agregarPlataforma(nomPlat, "www.platparalistarespectaculosperolistvacia.com",
          "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //TEST
    String[] listaEsp = ice.listarEspectaculos(nomPlat);
    assertNull(listaEsp);
  }

  @Test
  void testSeleccionarEspectaculo() {
    //Crear artista organizador de espectáculo
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "artselesp";
    try {
      icu.nuevoArtista(nomArt, "artselesp@mail.com", "Juan", "Perez", 
          LocalDateTime.of(1990, 1, 1, 1, 1),
          "descripcion", "descripcion", "www.artselesp.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea el espectáculo
    String nomPlat = "platselesp";
    try {
      ice.agregarPlataforma(nomPlat, "www.platselesp.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatSelEsp";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear espectaculo y seleccionarlo para verificar que anda bien
    try {
      String nomEsp = "espselesp";
      String desc = "descripcion espectaculo";
      int dur = 120;
      int minEsp = 50;
      int maxEsp = 150;
      String url = "www.espselesp.com";
      int costo = 500;
      Date freg = Date.valueOf(LocalDate.now());
      String urlimg = "http://test.com";
      ice.crearEspectaculo(nomPlat, nomArt, nomEsp, desc, dur, minEsp,
          maxEsp, url, costo, freg, cats, "img", "premio", 0, "urlVideo");
      //Funciones para ver que las liste bien
      ice.ingresarDatosFuncion("funcselesp1", freg, null, freg, nomEsp, urlimg);
      ice.ingresarDatosFuncion("funcselesp2", freg, null, freg, nomEsp, urlimg);
      //Paqutes para ver que los liste bien
      ice.crearPaquete("paqselesp1", "", freg, freg, 20, freg, null);
      ice.crearPaquete("paqselesp2", "", freg, freg, 30, freg, null);
      ice.crearPaquete("paqselesp3", "", freg, freg, 10, freg, null);
      ice.agregarEspectaculoAPaquete(nomPlat, nomEsp, "paqselesp1");
      ice.agregarEspectaculoAPaquete(nomPlat, nomEsp, "paqselesp2");
      //Busco el espectáculo en la plataforma y corroboro las listas
      DataEspectaculo desp = ice.seleccionarEspectaculo(nomEsp);
      String[] nomFunc = desp.getNombresFunciones();
      for (int i = 0; i < nomFunc.length; i++) {
        assertTrue(nomFunc[i].equals("funcselesp1") || nomFunc[i].equals("funcselesp2"));
      }
      String[] nomPaq = desp.getNombresPaquetes();
      for (int i = 0; i < nomPaq.length; i++) {
        assertEquals(nomPaq[i], "paqselesp" + String.valueOf(i + 1));
      }
    } catch (EspectaculoNoExisteException | EspectaculoRepetidoException
        | UsuarioNoExisteException | MaxEspMenorMinEspException
        | FuncionRepetidaException | PaqueteRepetidoException | FinVigAntesIniVigException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testConfirmarRegistroAFuncion() {

    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    //Crear espectador para la funcion
    try {
      String nomEspec = "espec123";
      icu.nuevoEspectador(nomEspec, "asdasdxzcxc", "Marcelo Hugo", "Tinelli",
          LocalDateTime.of(1990, 1, 1, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista organizador
    try {
      String nomArt = "Fabian Palito";
      icu.nuevoArtista(nomArt, "elfabipalito@gmail.com", "Fabian", "Palito",
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", "descripcion",
          "www.fabian.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plataforma prueba confirmar";
      ice.agregarPlataforma(nomPlat, "pruebaconfirmar", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFunc";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo("plataforma prueba confirmar", "Fabian Palito", "Lolapalooza 2020",
          "descrpicion", 120, 1, 1, "asd", 1, fini, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear funcion en espectaculo
    String nomFunc1 = "funcion123";
    Date fini = Date.valueOf(LocalDate.now());
    List<String> artistasFuncion = new ArrayList<String>();
    artistasFuncion.add("Fabian Palito");
    String urlimg = "http://test.com";
    try {
      ice.ingresarDatosFuncion(nomFunc1, fini, artistasFuncion, fini, "Lolapalooza 2020", urlimg);
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //TEST
    try {
      List<String> canjeaRegistros = new ArrayList<String>();
      ice.confirmarRegistroAFuncion("plataforma prueba confirmar", "", "Lolapalooza 2020", 
          "funcion123", "espec123", canjeaRegistros, Date.valueOf(LocalDate.now()));
      
      DataRegistro reg = null;
      DataRegistro[] funcEspec = icu.listarFuncionesEspectador("espec123");
      for(DataRegistro registro: funcEspec) {
    	  if((registro.getFuncion()).equals("funcion123")) {
    		  reg = registro;
    		  break;
    	  }
      }      
      assertEquals(reg.getFuncion(), "funcion123");
    } catch (FuncionLlenaExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (NoEsEspectadorExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (EspectadorRegistradoEnFuncionExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (ErrorCantidadCanjeExcepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (FechaPasadaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RegistroNoSePuedeCanjearException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  void testConfirmarRegistroAFuncionPaquete() {

    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    //Crear espectador para la funcion
    try {
      String nomEspec = "especPaquete";
      icu.nuevoEspectador(nomEspec, "fffff", "Jorge Carlos", "Pineyrua",
          LocalDateTime.of(1990, 1, 1, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista organizador
    try {
      String nomArt = "Fata Delgado";
      icu.nuevoArtista(nomArt, "fatadelgado@gmail.com", "Fata", "Delgado", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion",
          "descripcion", "www.fata.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "platPaquete";
      ice.agregarPlataforma(nomPlat, "pruebaPaqueteReg", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Categorias
    String cat = "CatConfregFuncLlena";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo("platPaquete", "Fata Delgado", "Macarena 2020", "descrpicion", 100,
          1, 1, "asd", 100, fini, cats, "asd", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear funcion en espectaculo
    String nomFunc1 = "funcionRegPaq";
    Date fini = Date.valueOf(LocalDate.now());
    List<String> artistasFuncion = new ArrayList<String>();
    artistasFuncion.add("Fata Delgado");
    String urlimg = "http://test.com";
    try {
      ice.ingresarDatosFuncion(nomFunc1, fini, artistasFuncion, fini, "Macarena 2020", urlimg);
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear paquete
    Date freg = Date.valueOf(LocalDate.now());
    Date freg2 = Date.valueOf(LocalDate.of(2021, 1, 17));
    Date freg3 = Date.valueOf(LocalDate.now());

    try {
      ice.crearPaquete("paqueteRegistro", "plenas", freg, freg2, 20,
          freg3, null);
    } catch (PaqueteRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (FinVigAntesIniVigException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Agregar espectaculo a paquete
    ice.agregarEspectaculoAPaquete("platPaquete", "Macarena 2020", "paqueteRegistro");

    //Espectador compra paquete
    try {
      icu.CompraPaquete("especPaquete", "paqueteRegistro", fini);
    } catch (YaComproPaqueteException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    //Registro a funcion
    try {
      List<String> canjeaRegistros = new ArrayList<String>();
      ice.confirmarRegistroAFuncion("platPaquete", "paqueteRegistro", "Macarena 2020",
          "funcionRegPaq", "especPaquete", canjeaRegistros, Date.valueOf(LocalDate.now()));
      
      DataRegistro reg = null;
      DataRegistro[] funcEspec = icu.listarFuncionesEspectador("espec123");
      for(DataRegistro registro: funcEspec) {
    	  if((registro.getFuncion()).equals("funcionRegPaq")) {
    		  reg = registro;
    		  break;
    	  }
      }      
      assertEquals(reg.getFuncion(), "funcionRegPaq");
      assertEquals(reg.getPaquete(), "paqueteRegistro");
      assertEquals(reg.getCosto(), 80);


    } catch (FuncionLlenaExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (NoEsEspectadorExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (EspectadorRegistradoEnFuncionExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (ErrorCantidadCanjeExcepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (FechaPasadaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RegistroNoSePuedeCanjearException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  @Test
  void testConfirmarRegistroAFuncionLlena() {

    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    //Crear espectador para registrar
    try {
      String nomEspecConfirmarLlena = "especPruebaLlena";
      icu.nuevoEspectador(nomEspecConfirmarLlena, "email@email", "Fabian Larry", "Estoyanoff", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista organizador
    try {
      String nomArt = "Hernan Coronel";
      icu.nuevoArtista(nomArt, "hernan@gmail.com", "Hernan", "Coronel", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", "descripcion",
          "www.herni.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plataforma prueba llena";
      ice.agregarPlataforma(nomPlat, "pruebaLlena", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFuncLlena";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo("plataforma prueba llena", "Hernan Coronel", 
          "Montevideo Rock 2020", "descrpicion", 120, 0, 0, "asd", 1, fini, cats, "img",
          "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear funcion en espectaculo
    String nomFunc1 = "funcion1";
    Date fini = Date.valueOf(LocalDate.now());
    List<String> artistasFuncion = new ArrayList<String>();
    artistasFuncion.add("Hernan Coronel");
    String urlimg = "http://test.com";
    try {
      ice.ingresarDatosFuncion(nomFunc1, fini, artistasFuncion, fini, 
          "Montevideo Rock 2020", urlimg);
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //TEST
    List<String> canjeaRegistros = new ArrayList<String>();
    assertThrows(FuncionLlenaExepcion.class, () -> {
      ice.confirmarRegistroAFuncion("plataforma prueba llena", "", 
          "Montevideo Rock 2020", "funcion1", "especPruebaLlena", 
          canjeaRegistros, Date.valueOf(LocalDate.now()));
    });

  }

  @Test
  void testConfirmarRegistroAFuncionNoArtista() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    

    //Crear artista organizador
    try {
      String nomArt = "Yesti Prieto";
      icu.nuevoArtista(nomArt, "asdasd", "Yesti", "Prieto", LocalDateTime.of(1990, 1, 1, 1, 1), 
          "descripcion", "descripcion", "asdasd", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plataforma prueba no artista";
      ice.agregarPlataforma(nomPlat, "sdfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFuncNoArt";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo("plataforma prueba no artista", "Yesti Prieto", "asdqwe", 
          "descrpicion", 120, 1, 1, "asd", 1, fini, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear funcion en espectaculo
    String nomFunc1 = "funcionArtista";
    Date fini = Date.valueOf(LocalDate.now());
    List<String> artistasFuncion = new ArrayList<String>();
    artistasFuncion.add("Yesti Prieto");
    String urlimg = "http://test.com";
    try {
      ice.ingresarDatosFuncion(nomFunc1, fini, artistasFuncion, fini, "asdqwe", urlimg);
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    List<String> canjeaRegistros = new ArrayList<String>();
    assertThrows(NoEsEspectadorExepcion.class, () -> {
      ice.confirmarRegistroAFuncion("plataforma prueba no artista", "", "asdqwe",
          "funcionArtista", "Yesti Prieto", canjeaRegistros, Date.valueOf(LocalDate.now()));
    });
  }

  @Test
  void testConfirmarRegistroAFuncionYaRegistrado() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    List<String> canjeaRegistros = new ArrayList<String>();

    //Crear espectador para la funcion
    try {
      String nomEspec = "espec12345";
      icu.nuevoEspectador(nomEspec, "asdasdgxzcxc", "Juan Roman", "Riquelme",
          LocalDateTime.of(1990, 1, 1, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista organizador
    try {
      String nomArt = "Carlos Corti y los muchachos";
      icu.nuevoArtista(nomArt, "qqweaasd", "Carlos", "Corti", LocalDateTime.of(1990, 1, 1, 1, 1),
          "descripcion", "descripcion", "asdasd", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plataforma prueba ya reg";
      ice.agregarPlataforma(nomPlat, "ffdsfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFuncYaReg";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo("plataforma prueba ya reg", "Carlos Corti y los muchachos", 
          "asqweqwedqwe", "descrpicion", 120, 1, 10, "asd", 1, fini, cats, "img",
          "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear funcion en espectaculo
    String nomFunc1 = "funcionRegistrado";
    Date fini = Date.valueOf(LocalDate.now());
    List<String> artistasFuncion = new ArrayList<String>();
    artistasFuncion.add("Yesti Prieto");
    String urlimg = "http://test.com";
    try {
      ice.ingresarDatosFuncion(nomFunc1, fini, artistasFuncion, fini, "asqweqwedqwe", urlimg);
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    try {
      ice.confirmarRegistroAFuncion("plataforma prueba ya reg", "", "asqweqwedqwe", 
          nomFunc1, "espec12345", canjeaRegistros, Date.valueOf(LocalDate.now()));
    } catch (FuncionLlenaExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (NoEsEspectadorExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (EspectadorRegistradoEnFuncionExepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (ErrorCantidadCanjeExcepcion e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (FechaPasadaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RegistroNoSePuedeCanjearException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertThrows(EspectadorRegistradoEnFuncionExepcion.class, () -> {
      ice.confirmarRegistroAFuncion("plataforma prueba ya reg", "", "asqweqwedqwe", 
          nomFunc1, "espec12345", canjeaRegistros, Date.valueOf(LocalDate.now()));
    });

  }

  @SuppressWarnings("deprecation")
@Test
  void testConfirmarRegistroAFuncionFueraDeFecha() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    IEspectaculoController iec = fab.getIEspectaculoController();


    try {
      icu.nuevoEspectador("aaasssaas", "sdfsdf@asdasd.com", "Antonio", "Pacheco",
          LocalDateTime.of(1955, 2, 14, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      icu.nuevoArtista("qweqwewqewqe", "qweqweqweqwe@tuta.io", "Village", "People", 
          LocalDateTime.of(1977, 1, 1, 1, 1), "Village People es una innovadora formaci�n musical "
              + "de estilo disco de finales de los a�os 70. Fue famosa tanto "
              + "por sus peculiares disfraces, como por sus canciones pegadizas, "
              + "con letras sugerentes y llenas de dobles sentidos.", 
              "Grupo americano del disco creado por Jacques Morali y Henry Belolo "
              + "en 1977. Seg�n Marjorie Burgess, todo comenz� cuando Morali "
              + "fue a un bar gay de Nueva York una noche y not� al bailar�n Felipe "
              + "Rose vestido como un nativo americano.", 
              "www.officialvillag epeople.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      iec.agregarPlataforma("qqqwwwwqqqqq", "Funcionalidad de la red social Instagram, "
          + "con la que los usuarios pueden transmitir v�deos en vivo", 
          "https://www.instagram.com/liveoficial");
    } catch (PlataformaRepetidaException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }


    //Crear categorias
    String cat = "CatConfregFuncFueraDeFecha";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    try {
      iec.crearEspectaculo("qqqwwwwqqqqq", "qweqwewqewqe", "espec2231231", 
          "Espectaculo de retorno de los Village People", 90, 10, 800,
          "https:// www.instagram.com /realvillagepeople/", 550,
          Date.valueOf(LocalDate.of(2020, 3, 31)), cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException
        | MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    List<String> artistasFuncion = new ArrayList<String>();
    try {
      iec.ingresarDatosFuncion("adadqeqweqwasd", new Date(2020, 4, 15), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 3, 31)), "espec2231231", "http://test.com");
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List<String> canjeaRegistros = new ArrayList<String>();
    assertThrows(FechaPasadaException.class, () -> {
      ice.confirmarRegistroAFuncion("qqqwwwwqqqqq", "", "espec2231231", "adadqeqweqwasd", 
          "aaasssaas", canjeaRegistros, new Date(2025, 1, 1));
    });

  }

  @SuppressWarnings("deprecation")
@Test
  void testConfirmarRegistroAFuncionFechaRegistro() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    IEspectaculoController iec = fab.getIEspectaculoController();
    List<String> canjeaRegistros = new ArrayList<String>();


    try {
      icu.nuevoEspectador("chinoprueba2", "chinoprueba2@trico.org.uy", "Alvaro", "Recoba", 
          LocalDateTime.of(1976, 3, 17, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      icu.nuevoArtista("dmode2", "dmode2@tuta.io", "Depeche", "Mode", 
          LocalDateTime.of(1980, 6, 14, 1, 1), "Depeche Mode es un grupo ingl�s "
              + "de m�sica electr�nica formado en Basildon, Essex, en 1980 por Vicent Clarke "
              + "y Andrew John Fletcher, a los que se unieron Martin Lee Gore y poco despu�s "
              + "David Gahan. Actualmente se le considera como grupo de m�sica alternativa.",
              " ", "www.depechemode.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      iec.agregarPlataforma("fbw2", "Servicio de video bajo demanda operado por Facebook.", 
          "https://www.facebook.com/watch/");
    } catch (PlataformaRepetidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFuncFechaRegistro";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    try {
      iec.crearEspectaculo("fbw2", "dmode2", "gs", "Espectaculo donde se presenta el album Spirit", 
          120, 30, 1300, "https://es-la.facebook.com/depechemode", 750,
          Date.valueOf(LocalDate.of(2020, 4, 20)), cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException 
        | MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }




    List<String> artistasFuncion = new ArrayList<String>();

    try {
      String urlimg = "http://test.com";
      iec.ingresarDatosFuncion("gs (0)", new Date(2020, 6, 10), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 4, 20)), "gs", urlimg);
      iec.ingresarDatosFuncion("gs (I)", new Date(2020, 6, 10), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 4, 20)), "gs", urlimg);
      iec.ingresarDatosFuncion("gs (II)", new Date(2020, 7, 10), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 4, 20)), "gs", urlimg);
      iec.ingresarDatosFuncion("gs (III)", new Date(2020, 8, 10), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 4, 20)), "gs", urlimg);
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    try {
      ice.confirmarRegistroAFuncion("fbw2", "", "gs", "gs (I)", "chinoprueba2", 
          canjeaRegistros, new Date(2020, 3, 20));
      ice.confirmarRegistroAFuncion("fbw2", "", "gs", "gs (II)", "chinoprueba2", 
          canjeaRegistros, new Date(2020, 3, 20));
      ice.confirmarRegistroAFuncion("fbw2", "", "gs", "gs (III)", "chinoprueba2", 
          canjeaRegistros, new Date(2020, 3, 20));
    } catch (FuncionLlenaExepcion | NoEsEspectadorExepcion | EspectadorRegistradoEnFuncionExepcion
        | FechaPasadaException | RegistroNoSePuedeCanjearException 
        | ErrorCantidadCanjeExcepcion e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    canjeaRegistros.add("gs (I)");
    canjeaRegistros.add("gs (II)");
    canjeaRegistros.add("gs (III)");

    assertThrows(FechaPasadaException.class, () -> {
      ice.confirmarRegistroAFuncion("fbw2", "", "gs", "gs (0)", "chinoprueba2", 
          canjeaRegistros, new Date(2025, 1, 1));
    });

  }

  @SuppressWarnings("deprecation")
@Test
  void testConfirmarRegistroAFuncionCantidad() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    IEspectaculoController iec = fab.getIEspectaculoController();
    List<String> canjeaRegistros = new ArrayList<String>();

    try {
      icu.nuevoEspectador("house2", "greghouse2@gmail.com", "Gregory", "House", 
          LocalDateTime.of(1959, 5, 15, 1, 1), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    try {
      icu.nuevoArtista("clauper2", "clauper2@hotmail.com", "Cyndi", "Lauper", 
          LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann Stephanie Lauper, "
              + "conocida simplemente como Cyndi Lauper, es una cantautora, "
              + "actriz y empresaria estadounidense. despu�s de participar en "
              + "el grupo musical, Blue Angel, en 1983 firm� con Portrait "
              + "Records (filial de Epic Records) y lanz� su exitoso �lbum "
              + "debut She's So Unusual a finales de ese mismo a�o. Sigui� "
              + "lanzando una serie de �lbumes en los que encontr� una inmensa "
              + "popularidad, superando los l�mites de contenido de las letras "
              + "de sus canciones", "Cynthia Ann Stephanie Lauper (Brooklyn, "
                  + "Nueva York; 22 de junio de 1953).", "cyndilauper.com", 
                  "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    try {
      iec.agregarPlataforma("twl", "Aplicaci�n de Twitter para la "
          + "transmisi�n de video en directo (streaming).", "https://twitter.com/");
    } catch (PlataformaRepetidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    //Crear categorias
    String cat = "CatConfregFuncCant";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    try {
      iec.crearEspectaculo("twl", "clauper2", "mbw", 
          "Espectaculo promoviendo album Memphis Blues", 110, 5, 
          1000, "https://twitter.com/cyndilauper", 800, 
          Date.valueOf(LocalDate.of(2020, 5, 31)), cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException 
        | MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    List<String> artistasFuncion = new ArrayList<String>();

    try {
      iec.ingresarDatosFuncion("mbw - A", new Date(2020, 8, 15), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 5, 30)), "mbw", "http://test.com");
      iec.ingresarDatosFuncion("mbw - B", new Date(2020, 8, 15), artistasFuncion, 
          Date.valueOf(LocalDate.of(2020, 5, 30)), "mbw", "http://test.com");

    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      ice.confirmarRegistroAFuncion("twl", "", "mbw", "mbw - A", "house2", 
          canjeaRegistros, new Date(2015, 1, 1));
    } catch (FuncionLlenaExepcion | NoEsEspectadorExepcion 
        | EspectadorRegistradoEnFuncionExepcion
        | FechaPasadaException | RegistroNoSePuedeCanjearException 
        | ErrorCantidadCanjeExcepcion e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    canjeaRegistros.add("mbw - A");


    assertThrows(ErrorCantidadCanjeExcepcion.class, () -> {
      ice.confirmarRegistroAFuncion("twl", "", "mbw", "mbw - B", "house2", 
          canjeaRegistros, new Date(2015, 1, 1));
    });

  }

  @Test
  void testListarEspectaculosPaquete() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    Date freg = Date.valueOf(LocalDate.now());

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "platPruebaLista";
      ice.agregarPlataforma(nomPlat, "ffdsfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista
    try {
      icu.nuevoArtista("Nestor", "nestor@hotmail.com", "Nestor", "En Bloque", 
          LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann Stephanie Lauper, "
              + "conocida simplemente como Cyndi Lauper, es una cantautora, "
              + "actriz y empresaria estadounidense. despu�s de participar en "
              + "el grupo musical, Blue Angel, en 1983 firm� con Portrait "
              + "Records (filial de Epic Records) y lanz� su exitoso �lbum "
              + "debut She's So Unusual a finales de ese mismo a�o. Sigui� "
              + "lanzando una serie de �lbumes en los que encontr� una "
              + "inmensa popularidad, superando los l�mites de contenido de "
              + "las letras de sus canciones", "Cynthia Ann Stephanie Lauper "
                  + "(Brooklyn, Nueva York; 22 de junio de 1953).", 
                  "cyndilauper.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFuncFechaRegistro";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};


    try {
      ice.crearEspectaculo("platPruebaLista", "Nestor", "esp123123", "sss", 
          12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
      ice.crearEspectaculo("platPruebaLista", "Nestor", "esp234234", "sss", 
          12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
      ice.crearEspectaculo("platPruebaLista", "Nestor", "esp345345", "sss", 
          12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }



    try {
      ice.crearPaquete("paq123123", "", freg, freg, 20, freg, null);
    } catch (PaqueteRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FinVigAntesIniVigException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

   

    ice.agregarEspectaculoAPaquete("platPruebaLista", "esp123123", "paq123123");
    ice.agregarEspectaculoAPaquete("platPruebaLista", "esp234234", "paq123123");
    ice.agregarEspectaculoAPaquete("platPruebaLista", "esp345345", "paq123123");

    String[] prueba = {"esp123123", "esp234234", "esp345345"};
    assertEquals(prueba[0], prueba[0]);
    assertEquals(prueba[1], prueba[1]);
    assertEquals(prueba[2], prueba[2]);

  }

  @Test
  void testSeleccionarEspectaculoPaquete() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    Date freg = Date.valueOf(LocalDate.now());

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "platPruebaSeleccionar";
      ice.agregarPlataforma(nomPlat, "ffdsfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista
    try {
      icu.nuevoArtista("Roman", "roman@hotmail.com", "Roman", 
          "El Original", LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann "
              + "Stephanie Lauper, conocida simplemente como Cyndi Lauper, "
              + "es una cantautora, actriz y empresaria estadounidense. "
              + "despu�s de participar en el grupo musical, Blue Angel, "
              + "en 1983 firm� con Portrait Records (filial de Epic Records) "
              + "y lanz� su exitoso �lbum debut She's So Unusual a finales "
              + "de ese mismo a�o. Sigui� lanzando una serie de �lbumes en "
              + "los que encontr� una inmensa popularidad, superando los "
              + "l�mites de contenido de las letras de sus canciones", 
              "Cynthia Ann Stephanie Lauper (Brooklyn, Nueva York; 22 de j"
              + "unio de 1953).", "cyndilauper.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatConfregFuncFechaRegistro";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};


    try {
      ice.crearEspectaculo("platPruebaSeleccionar", "Roman", "selEsp1", 
          "sss", 12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      ice.crearPaquete("paqSel", "", freg, freg, 20, freg, null);
    } catch (PaqueteRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FinVigAntesIniVigException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    ice.agregarEspectaculoAPaquete("platPruebaSeleccionar", "selEsp1", "paqSel");

    DataEspectaculo desp = ice.seleccionarEspectaculoDePaquete("paqSel", "selEsp1");

    assertEquals(desp.getNombre(), "selEsp1");

  }

  @Test
  void testListarEspectaculosCateogria() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    Date freg = Date.valueOf(LocalDate.now());

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "platListaEspCategoria";
      ice.agregarPlataforma(nomPlat, "ffdsfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista
    try {
      icu.nuevoArtista("Martin Quiroga", "martin@hotmail.com", "Martin", 
          "Quiroga", LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann "
              + "Stephanie Lauper, conocida simplemente como Cyndi Lauper, "
              + "es una cantautora, actriz y empresaria estadounidense. "
              + "despu�s de participar en el grupo musical, Blue Angel, en "
              + "1983 firm� con Portrait Records (filial de Epic Records) y "
              + "lanz� su exitoso �lbum debut She's So Unusual a finales "
              + "de ese mismo a�o. Sigui� lanzando una serie de �lbumes en "
              + "los que encontr� una inmensa popularidad, superando los "
              + "l�mites de contenido de las letras de sus canciones", 
              "Cynthia Ann Stephanie Lauper (Brooklyn, Nueva York; 22 "
              + "de junio de 1953).", "cyndilauper.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "catPruebaLista";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};


    try {
      ice.crearEspectaculo("platListaEspCategoria", "Martin Quiroga", 
          "espCat", "sss", 12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      ice.aceptarEspectaculo("espCat", true);
    } catch (EspectaculoNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String[] lcat = ice.listarEspectaculosCategoria("catPruebaLista");

    assertEquals(lcat[0], "espCat");
  }

  @Test
  void testListarEspectaculosIngresados() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    Date freg = Date.valueOf(LocalDate.now());

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "platListarIngresados";
      ice.agregarPlataforma(nomPlat, "ffdsfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista
    try {
      icu.nuevoArtista("Denis", "denis@hotmail.com", "Denis", "Elias", 
          LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann Stephanie "
              + "Lauper, conocida simplemente como Cyndi Lauper, es "
              + "una cantautora, actriz y empresaria estadounidense. "
              + "despu�s de participar en el grupo musical, Blue "
              + "Angel, en 1983 firm� con Portrait Records (filial "
              + "de Epic Records) y lanz� su exitoso �lbum debut "
              + "She's So Unusual a finales de ese mismo a�o. Sigui� "
              + "lanzando una serie de �lbumes en los que encontr� una "
              + "inmensa popularidad, superando los l�mites de "
              + "contenido de las letras de sus canciones", "Cynthia "
                  + "Ann Stephanie Lauper (Brooklyn, Nueva York; 22 "
                  + "de junio de 1953).", "cyndilauper.com", "pass", 
                  "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "catPruebaIngresados";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};


    try {
      ice.crearEspectaculo("platListarIngresados", "Denis", "espIngre", 
          "sss", 12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    String[] lingre = ice.listarEspectaculosEstadoIngresado();

    assertTrue(Arrays.asList(lingre).contains("espIngre"));

  }

  @Test
  void testListarCategorias() {
    String cat = "catListarCategorias";
    ice.ingresarDatosCategoria(cat);
    String[] lcateg = ice.listarCategorias();

    assertTrue(Arrays.asList(lcateg).contains("catListarCategorias"));
  }

  @SuppressWarnings("deprecation")
@Test
  void testListarFuncionesEspectaculo() {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    IEspectaculoController iec = fab.getIEspectaculoController();
    Date freg = Date.valueOf(LocalDate.now());

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "platListarFuncEsp";
      ice.agregarPlataforma(nomPlat, "ffdsfsdf", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear artista
    try {
      icu.nuevoArtista("Pablo Lescano", "pablo@hotmail.com", "Pablo", "Lescano", 
          LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann Stephanie Lauper, "
              + "conocida simplemente como Cyndi Lauper, es una cantautora, "
              + "actriz y empresaria estadounidense. despu�s de participar en "
              + "el grupo musical, Blue Angel, en 1983 firm� con Portrait "
              + "Records (filial de Epic Records) y lanz� su exitoso �lbum "
              + "debut She's So Unusual a finales de ese mismo a�o. Sigui� "
              + "lanzando una serie de �lbumes en los que encontr� una inmensa "
              + "popularidad, superando los l�mites de contenido de las letras "
              + "de sus canciones", "Cynthia Ann Stephanie Lauper (Brooklyn, "
                  + "Nueva York; 22 de junio de 1953).", "cyndilauper.com", 
                  "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "catPruebaListarFunc";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};


    try {
      ice.crearEspectaculo("platListarFuncEsp", "Pablo Lescano", "espListFunc", 
          "sss", 12, 1, 2, "ddd", 3, freg, cats, "img", "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    List<String> artistasFuncion = new ArrayList<String>();

    try {
      iec.ingresarDatosFuncion("asd123", new Date(2020, 8, 15), artistasFuncion, Date.valueOf(LocalDate.of(2020, 5, 30)), "espListFunc", "http://test.com");
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String[] lfuncEsp = ice.listarFuncionesEspectaculo("platListarFuncEsp", "espListFunc");
    assertTrue(Arrays.asList(lfuncEsp).contains("asd123"));

  }

  @Test
  void testCrearPaqueteR() {
    Date freg = Date.valueOf(LocalDate.now());
    Date freg2 = Date.valueOf(LocalDate.of(2021, 1, 17));
    Date freg3 = Date.valueOf(LocalDate.now());

    try {
      ice.crearPaquete("Pq", "ricota", freg, freg2, 2, freg3, null);
    } catch (PaqueteRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (FinVigAntesIniVigException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(PaqueteRepetidoException.class, () -> {
      ice.crearPaquete("Pq", "ricota", freg, freg2, 2, freg3, null);
    });
  }

  @Test
  void testCrearPaqueteFMala() {
    Date freg1 = Date.valueOf(LocalDate.now());
    Date freg2 = Date.valueOf(LocalDate.of(2021, 1, 17));
    Date freg3 = Date.valueOf(LocalDate.now());

    assertThrows(FinVigAntesIniVigException.class, () -> {
      ice.crearPaquete("Pq3", "ricota", freg2, freg1, 2, freg3, null);
    });
  }

  @Test
  void testListarEspectaculosNoEnPaquete() {

    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    //Crear artista organizador
    try {
      String nomArt = "Fabian Palito2";
      icu.nuevoArtista(nomArt, "elfabipalito2@gmail.com", "Fabian", 
          "Palito", LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", 
          "descripcion", "www.fabian.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plataforma prueba confirmar2";
      ice.agregarPlataforma(nomPlat, "pruebaconfirmar2", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatListarEspNoEnPaquete";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo("plataforma prueba confirmar2", "Fabian Palito2", 
          "Lolapalooza 20202", "descrpicion", 120, 1, 1, "asd", 1, fini, cats, "img",
          "premio", 0, "urlVideo");
      ice.aceptarEspectaculo("Lolapalooza 20202", true);
    } catch (EspectaculoRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    Date freg = Date.valueOf(LocalDate.now());
    Date freg2 = Date.valueOf(LocalDate.of(2021, 1, 17));
    Date freg3 = Date.valueOf(LocalDate.now());

    try {
      ice.crearPaquete("Pq2", "ricota", freg, freg2, 2, freg3, null);
    } catch (PaqueteRepetidoException e) {
      fail(e.getMessage());
      e.printStackTrace();
    } catch (FinVigAntesIniVigException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }


    //listar EspectaculosNoEnPaquete
    String[] espNoEnPaq = ice.listarEspectaculosNoEnPaquete("plataforma prueba confirmar2", "Pq2");
    assertEquals("Lolapalooza 20202", espNoEnPaq[0]);



    ice.agregarEspectaculoAPaquete("plataforma prueba confirmar2", "Lolapalooza 20202", "Pq2");


    //listar Paquetes
    ice.listarPaquetes();

  }

  @Test
  void testSeleccionarPaquete() {
    Date finiV = Date.valueOf(LocalDate.now());
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "artselpaq";
    try {
      icu.nuevoArtista(nomArt, nomArt, "Juan", "Perez", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", 
          "descripcion", "www.artselpaq.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorias
    String cat = "CatSelPaq";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    try {
      ice.agregarPlataforma("platselpaq", "platselpaq", "platselpaq");
      ice.crearPaquete("Parselpaq", "Parselpaq", finiV, finiV, 20, finiV, null);
      ice.crearEspectaculo("platselpaq", nomArt, "espselpaq", "espselpaq", 
          12233, 11, 1111, "espselpaq", 1232, finiV, cats, "url", "premio", 0, "urlVideo");
      ice.agregarEspectaculoAPaquete("platselpaq", "espselpaq", "Parselpaq");

    } catch (PaqueteRepetidoException | FinVigAntesIniVigException 
        | PlataformaRepetidaException | EspectaculoRepetidoException 
        | UsuarioNoExisteException | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    DataPaquete dtp = ice.seleccionarPaquete("Parselpaq");
    assertEquals("Parselpaq", dtp.getNombre());
    assertEquals("Parselpaq", dtp.getDescripcion());
    assertEquals(finiV, dtp.getInicioVigencia());
    assertEquals(finiV, dtp.getFinVigencia());
    assertEquals(20, dtp.getDescuento());
    assertEquals(finiV, dtp.getFechaDeAlta());
    for (int i = 0; i < dtp.getEspectaculos().length; i++) {
      assertTrue(dtp.getEspectaculos()[i] == "espselpaq");

    }
  }

  @Test
  void testAceptarEspectaculoTrue() {
    //Crear artista organizador de espectáculo
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "artAceptarEspTrue";
    try {
      icu.nuevoArtista(nomArt, "artAceptarEspTrue@mail.com", "Juan", "Perez", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", "descripcion", 
          "www.art123.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea el espectáculo
    String nomPlat = "platAceptarEspTrue";
    try {
      ice.agregarPlataforma(nomPlat, "www.plat123.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorías
    String cat = "CatAceptarEspTrue";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear espectaculo
    String nomEsp = "espAceptarEspTrue";
    try {
      Date freg = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo(nomPlat, nomArt, nomEsp, "d", 10, 1, 2, "u", 10, freg, cats, "i",
          "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException 
        | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //TEST
    try {
      ice.aceptarEspectaculo(nomEsp, true);
      DataEspectaculo desp = ice.seleccionarEspectaculo(nomEsp);
      assertEquals("Aceptado", desp.getEstado());
    } catch (EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testAceptarEspectaculoFalse() {
    //Crear artista organizador de espectáculo
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    String nomArt = "artAceptarEspFalse";
    try {
      icu.nuevoArtista(nomArt, "artAceptarEspFalse@mail.com", "Juan", 
          "Perez", LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", 
          "descripcion", "www.art123.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear plataforma que hostea el espectáculo
    String nomPlat = "platAceptarEspFalse";
    try {
      ice.agregarPlataforma(nomPlat, "www.plat123.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear categorías
    String cat = "CatAceptarEspFalse";
    ice.ingresarDatosCategoria(cat);
    String[] cats = {cat};
    //Crear espectaculo
    String nomEsp = "espAceptarEspFalse";
    try {
      Date freg = Date.valueOf(LocalDate.now());
      ice.crearEspectaculo(nomPlat, nomArt, nomEsp, "d", 10, 1, 2, "u", 10, freg, cats, "i",
          "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException 
        | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //TEST
    try {
      ice.aceptarEspectaculo(nomEsp, false);
      DataEspectaculo desp = ice.seleccionarEspectaculo(nomEsp);
      assertEquals("Rechazado", desp.getEstado());
    } catch (EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testAceptarEspectaculoNoExiste() {
    assertThrows(EspectaculoNoExisteException.class, () -> {
      ice.aceptarEspectaculo("espAceptarEspNoExiste", true);
    });
  }

  @Test
  void listarPaqueteV() throws PaqueteRepetidoException, FinVigAntesIniVigException, 
      EspectaculoRepetidoException, UsuarioNoExisteException, MaxEspMenorMinEspException, 
      UsuarioRepetidoExepcion, PlataformaRepetidaException, PasswordErrorException {
    Fabrica fab = Fabrica.getInstancia();
    IUsuarioController icu = fab.getIUsuarioController();
    

    String cat = "CatListarEspNoEnPaquetey";
    ice.ingresarDatosCategoria(cat);
    

    icu.nuevoArtista("teletubi", "asdasdLPV", "Juan", "Perez", 
        LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", "descripcion", 
        "www.artselpaq.com", "pass", "pass", "imagen");
    ice.agregarPlataforma("platselpaq2", "platselpaq", "platselpaq");
    Date finiV =  Date.valueOf(LocalDate.of(2020, 1, 17));
    Date ffinV =  Date.valueOf(LocalDate.of(2021, 1, 17));
    String[] cats = {cat};
    ice.crearPaquete("Parselpaq10", "Parselpaq", finiV, ffinV, 20, finiV, null);
    ice.crearEspectaculo("platselpaq2", "teletubi", "espselpaq15", 
        "espselpaq", 12233, 11, 1111, "espselpaq", 1232, finiV, cats, "url",
        "premio", 0, "urlVideo");
    ice.agregarEspectaculoAPaquete("platselpaq2", "espselpaq15", "Parselpaq10");

    DataPaquete[] res = ice.listarPaqueteValidos(finiV);
    for (int i = 0; i < res.length; i++) {
      if (res[i].getNombre() == "Parselpaq10") {
        assertEquals("Parselpaq10", res[i].getNombre());
      }
    }
  }
}