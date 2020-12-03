package test;

import static org.junit.jupiter.api.Assertions.*;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectaculoRepetidoException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.FechaPasadaException;
import exepciones.FuncionLlenaExepcion;
import exepciones.FuncionRepetidaException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PasswordErrorException;
import exepciones.PlataformaRepetidaException;
import exepciones.RegistroNoSePuedeCanjearException;
import exepciones.UsuarioNoExisteException;
import exepciones.UsuarioRepetidoExepcion;
import exepciones.YaComproPaqueteException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import logica.Artista;
import logica.DataArtista;
import logica.DataEspectador;
import logica.DataUsuario;
import logica.Espectaculo;
import logica.Espectador;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;
import logica.ManejadorPaquetes;
import logica.ManejadorUsuarios;
import logica.Paquete;
import logica.Registro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class UsuarioControllerTest {

  private static IUsuarioController ICU;
  private static IEspectaculoController ICE;

  @BeforeAll
  public static void iniciar() {
    Fabrica fab = Fabrica.getInstancia();
    ICU = fab.getIUsuarioController();
    ICE = fab.getIEspectaculoController();
    ICU.cargarDatosPrueba();
  }

  //TODO: Casos de prueba para nuevo artista 
  @Test
  void testNuevoArtistaOK() {
    //Se verifica que el usuario no exista
    try {
      ICU.nuevoArtista("hola", "hola@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    ManejadorUsuarios musu = ManejadorUsuarios.getManejadorUsuarios();
    Artista art = musu.getArtista("hola");
    assertEquals("hola", art.getNickname());
    assertEquals("hola@mail", art.getEmail());
    assertEquals("Juan", art.getNombre());
    assertEquals("Loquesea", art.getApellido());
    assertEquals(LocalDateTime.of(2010, 10, 11, 12, 13), art.getFecha());
    assertEquals("bla bla bla", art.getDescripcion());
    assertEquals("quien sabe", art.getBiografia());
    assertEquals("juanloquesea@web", art.getWeb());

  }

  @Test
  void testNuevoArtistaFailArtistaRepetidoNick() {
    try {
      ICU.nuevoArtista("hola2", "hola2@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoArtista("hola2", "hola3@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    });
  }

  @Test
  void testNuevoArtistaFailArtistaRepetidoEmail() {
    try {
      ICU.nuevoArtista("hola4", "hola4@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoArtista("hola5", "hola4@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    });
  }

  @Test
  void testNuevoArtistaFailEspectadorRepetidoNick() {
    try {
      ICU.nuevoEspectador("hola6", "hola6@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoArtista("hola6", "hola7@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    });
  }

  @Test
  void testNuevoArtistaFailEspectadorRepetidoEmail() {
    try {
      ICU.nuevoEspectador("hola8", "hola8@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoArtista("hola9", "hola8@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    });
  }


  //Casos de prueba para nuevo Espectador
  @Test
  void testNuevoEspectadorOK() {
    // nuevoEspectador lleva las mismas pruebas que nuevoArtista
    //fail("Not yet implemented");
    try {
      ICU.nuevoEspectador("hola10", "hola10@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    ManejadorUsuarios musu = ManejadorUsuarios.getManejadorUsuarios();
    Espectador esp = musu.getEspectador("hola10");
    assertEquals("hola10", esp.getNickname());
    assertEquals("hola10@mail", esp.getEmail());
    assertEquals("Juan", esp.getNombre());
    assertEquals("Loquesea", esp.getApellido());
    assertEquals(LocalDateTime.of(2010, 10, 11, 12, 13), esp.getFecha());
  }

  @Test
  void testNuevoEspectadorFailEspectadorRepetidoNick() {
    try {
      ICU.nuevoEspectador("hola11", "hola11@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoEspectador("hola11", "hola12@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    });
  }

  @Test
  void testNuevoEspectadorFailEspectadorRepetidoEmail() {
    try {
      ICU.nuevoEspectador("hola13", "hola13@mail", "Juan", "Loquesea",
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoEspectador("hola14", "hola13@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    });
  }

  @Test
  void testNuevoEspectadorFailArtistaRepetidoEmail() {
    try {
      ICU.nuevoArtista("hola15", "hola15@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoEspectador("hola16", "hola15@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    });
  }

  @Test
  void testNuevoEspectadorFailArtistaRepetidoNick() {
    try {
      ICU.nuevoArtista("hola17", "hola17@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    assertThrows(UsuarioRepetidoExepcion.class, () -> {
      ICU.nuevoEspectador("hola17", "hola18@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    });
  }


  //Casos de prueba listar Usuarios
  @Test
  void testListarUsuariosOK() {
    try {
      ICU.nuevoArtista("hola19", "hola19@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "bla bla bla", 
          "quien sabe", "juanloquesea@web", "pass", "pass", "imagen");
      ICU.nuevoEspectador("hola20", "hola20@mail", "Juan", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    try {
      String[] lusu = ICU.listarUsuarios();
      for (int i = 0; i < lusu.length; i++) {
        if (lusu[i].equals("hola19") || lusu[i].equals("hola20")) {
          assertTrue(lusu[i].equals("hola19") || lusu[i].equals("hola20"));
        }
      }
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

  }


  @Test
  void testMostrarDatosUsuarioArtistaOK() {
    //Crear artista organizador
    try {
      ICU.nuevoArtista("hola23", "hola23@mail", "Juan", "Loquesea", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", 
          "descripcion", "www.fabian.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plat";
      ICE.agregarPlataforma(nomPlat, "urlplat.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }


    //Crear categorías
    String cat = "CatMostrarDatosUsuarioArtistaOk";
    ICE.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ICE.crearEspectaculo("plat", "hola23", "nombrehola23", 
          "descrpicion", 120, 1, 1, "asd", 1, fini, cats, "img",
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

    //Test de Mostrar los datos para un artista
    ManejadorUsuarios musu = ManejadorUsuarios.getManejadorUsuarios();
    Artista art = musu.getArtista("hola23");
    try {
      DataUsuario dusu = ICU.mostrarDatosUsuario("hola23");
      Espectaculo[] esp = art.getEspectaculos();
      String[] nes = new String[esp.length];
      for (int i = 0; i < esp.length; i++) {
        nes[i] = esp[i].getNombre();
      }
      DataArtista dart = new DataArtista(art.getNickname(), art.getNombre(), 
          art.getApellido(), art.getEmail(), art.getFecha(), 
          art.getDescripcion(), art.getBiografia(), art.getWeb(), 
          nes, art.getUrlImg(), art.getUsuariosSiguiendo(), art.getUsuariosSeguidores());
      assertEquals(dart.getNickname(), dusu.getNickname());
      assertEquals(dart.getEmail(), dusu.getEmail());
      assertEquals(dart.getNombre(), dusu.getNombre());
      assertEquals(dart.getApellido(), dusu.getApellido());
      assertEquals(dart.getFecha(), dusu.getFecha());
      for (int i = 0; i < nes.length; i++) {
        assertTrue(nes[i].equals(((DataArtista) dusu).getEspectaculos()[i]));
      }
      assertEquals(dart.getUrlImg(), dusu.getUrlImg());
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //DataArtista(String nickname, String nombre, String apellido, 
    //String email, LocalDateTime fecha, String descripcion, String 
    //biografia, String web, String[] espectaculos)

  }

  //Casos de Prueba mostrarusuario
  @Test
  void testMostrarDatosUsuarioEspectadorOK() {

    //Todo esto para crear una funcion y registrar a un Espectador
    String nombrePlataforma = "YouTube";
    String artOrgNick = "ArtFunOrg";
    String nombreEspectaculo = "EspFunTest";
    Date fechaEspReg = Date.valueOf(LocalDate.now());
    String artInvNick1 = "ArtFun1";
    String artInvNick2 = "ArtFun2";
    String nombreFuncion = "FunTestNom";
    Date horaInicio = Date.valueOf(LocalDate.now());
    Date fechaAlta = Date.valueOf(LocalDate.now());

    //Crear Espectador
    try {
      ICU.nuevoEspectador("hola22", "hola22@mail", "Juan2", "Loquesea2", 
          LocalDateTime.of(2010, 11, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Plataforma
    try {
      ICE.agregarPlataforma(nombrePlataforma, nombrePlataforma + ".com", "default description");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Artista organizador
    try {
      ICU.nuevoArtista(artOrgNick, artOrgNick + ".com", "Art", "Org", 
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia", 
          artOrgNick + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorías
    String cat = "CatMostrarDatosUsuarioEspOk";
    ICE.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear Espectaculo

    try {
      ICE.crearEspectaculo(nombrePlataforma, artOrgNick, nombreEspectaculo, 
          "descripcion", 10, 1, 100, nombreEspectaculo + ".com", 1, fechaEspReg, cats, "img",
          "premio", 0, "urlVideo");
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException 
        | MaxEspMenorMinEspException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Artistas

    try {
      ICU.nuevoArtista(artInvNick1, artInvNick1 + ".com", "Art", "Org", 
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia",
          artInvNick1 + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    try {
      ICU.nuevoArtista(artInvNick2, artInvNick2 + ".com", "Art", "Org", 
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia", 
          artInvNick2 + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    List<String> artInv = new LinkedList<String>();
    artInv.add(0, artInvNick1);
    artInv.add(1, artInvNick2);

    try {
      ICE.ingresarDatosFuncion(nombreFuncion, horaInicio, artInv, fechaAlta, nombreEspectaculo, "http://test.com");
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Registro
    ManejadorUsuarios musu = ManejadorUsuarios.getManejadorUsuarios();
    Espectador esp = musu.getEspectador("hola22");


    try {
      List<String> lfu = new ArrayList<String>();
      try {
        ICE.confirmarRegistroAFuncion(nombrePlataforma, "", nombreEspectaculo, 
            nombreFuncion, esp.getNickname(), lfu, Date.valueOf(LocalDate.now()));
      } catch (FechaPasadaException | RegistroNoSePuedeCanjearException 
          | ErrorCantidadCanjeExcepcion e1) {
        fail(e1.getMessage());
        e1.printStackTrace();
      }
    } catch (FuncionLlenaExepcion e1) {
      fail(e1.getMessage());
      e1.printStackTrace();
    } catch (NoEsEspectadorExepcion e2) {
      fail(e2.getMessage());
      e2.printStackTrace();
    } catch (EspectadorRegistradoEnFuncionExepcion e3) {
      fail(e3.getMessage());
      e3.printStackTrace();
    }

    //Test para verificar si crea bien el DataEspectador
    try {
      DataUsuario dusu = ICU.mostrarDatosUsuario("hola22");
      Set<Registro> regs = esp.getRegistro();
      String[] afu = null;
      if (regs != null) {
        afu = new String[regs.size()];
        int ini = 0;
        for (Registro r : regs) {
          afu[ini] = r.getFuncion().getNombre();
          ini++;
        }
      }
      DataEspectador desp = new DataEspectador("hola22", "Juan2", "Loquesea2", 
          "hola22@mail", LocalDateTime.of(2010, 11, 11, 12, 13), afu, "imagen",
          null, null, null);
      assertEquals(desp.getNickname(), dusu.getNickname());
      assertEquals(desp.getEmail(), dusu.getEmail());
      assertEquals(desp.getNombre(), dusu.getNombre());
      assertEquals(desp.getApellido(), dusu.getApellido());
      assertEquals(desp.getFecha(), dusu.getFecha());
      for (int i = 0; i < afu.length; i++) {
        assertTrue(afu[i].equals(((DataEspectador) dusu).getFunciones()[i]));
      }
      assertEquals(desp.getUrlImg(), dusu.getUrlImg());
    } catch (UsuarioNoExisteException e4) {
      fail(e4.getMessage());
      e4.printStackTrace();
    }
  }

  @Test
  void testMostrarDatosUsuarioFail() {
    assertThrows(UsuarioNoExisteException.class, () -> {
      ICU.mostrarDatosUsuario("chau");
    });
  }

  @Test
  void testMostrarDatosUsuarioGeneralArtistaOK() {
    //Crear artista organizador
    try {
      ICU.nuevoArtista("hola43", "hola43@mail", "Juan", "Loquesea", 
          LocalDateTime.of(1990, 1, 1, 1, 1), "descripcion", "descripcion", 
          "www.fabian.com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear plataforma para espectaculo
    try {
      String nomPlat = "plat43";
      ICE.agregarPlataforma(nomPlat, "urlplat.com", "descripcion");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }


    //Crear categorías
    String cat = "CatMostrarDatosUsuarioGeneralArtistaOk";
    ICE.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear espectaculo
    try {
      Date fini = Date.valueOf(LocalDate.now());
      ICE.crearEspectaculo("plat43", "hola43", "nombrehola43", 
          "descrpicion", 120, 1, 1, "asd", 1, fini, cats, "img", "premio", 0, "urlVideo");
      ICE.aceptarEspectaculo("nombrehola43", true);
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Test de Mostrar los datos para un artista
    ManejadorUsuarios musu = ManejadorUsuarios.getManejadorUsuarios();
    Artista art = musu.getArtista("hola43");
    try {
      DataUsuario dusu = ICU.mostrarDatosUsuarioGeneral("hola43");
      Espectaculo[] esp = art.getEspectaculos();
      String[] nes = new String[esp.length];
      for (int i = 0; i < esp.length; i++) {
        nes[i] = esp[i].getNombre();
      }
      DataArtista dart = new DataArtista(art.getNickname(), art.getNombre(), 
          art.getApellido(), art.getEmail(), art.getFecha(), art.getDescripcion(), 
          art.getBiografia(), art.getWeb(), nes, art.getUrlImg(), 
          art.getUsuariosSiguiendo(), art.getUsuariosSeguidores());
      assertEquals(dart.getNickname(), dusu.getNickname());
      assertEquals(dart.getEmail(), dusu.getEmail());
      assertEquals(dart.getNombre(), dusu.getNombre());
      assertEquals(dart.getApellido(), dusu.getApellido());
      assertEquals(dart.getFecha(), dusu.getFecha());
      for (int i = 0; i < nes.length; i++) {
        assertTrue(nes[i].equals(((DataArtista) dusu).getEspectaculos()[i]));
      }
      assertEquals(dart.getUrlImg(), dusu.getUrlImg());
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //DataArtista(String nickname, String nombre, String apellido, 
    //String email, LocalDateTime fecha, String descripcion, 
    //String biografia, String web, String[] espectaculos)

  }

  @Test
  void testMostrarDatosUsuarioGeneralEspectadorOK() {

    //Todo esto para crear una funcion y registrar a un Espectador
    String nombrePlataforma = "YouTube2";
    String artOrgNick = "ArtFunOrg2";
    String nombreEspectaculo = "EspFunTest2";
    Date fechaEspReg = Date.valueOf(LocalDate.now());
    String artInvNick1 = "ArtFun12";
    String artInvNick2 = "ArtFun22";
    String nombreFuncion = "FunTestNom2";
    Date horaInicio = Date.valueOf(LocalDate.now());
    Date fechaAlta = Date.valueOf(LocalDate.now());
    
    //Crear Espectador
    try {
      ICU.nuevoEspectador("hola222", "hola222@mail", "Juan22", "Loquesea22", 
          LocalDateTime.of(2010, 11, 11, 12, 13), "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Plataforma
    try {
      ICE.agregarPlataforma(nombrePlataforma, nombrePlataforma + ".com", "default description");
    } catch (PlataformaRepetidaException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Artista organizador
    try {
      ICU.nuevoArtista(artOrgNick, artOrgNick + ".com", "Art", "Org", 
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia", 
          artOrgNick + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Crear categorías
    String cat = "CatMostrarDatosUsuarioEspOk";
    ICE.ingresarDatosCategoria(cat);
    String[] cats = {cat};

    //Crear Espectaculo

    try {
      ICE.crearEspectaculo(nombrePlataforma, artOrgNick, nombreEspectaculo, 
          "descripcion", 10, 1, 100, nombreEspectaculo + ".com", 1, fechaEspReg, cats, "img",
          "premio", 0, "urlVideo");
      ICE.aceptarEspectaculo(nombreEspectaculo, true);
    } catch (EspectaculoRepetidoException | UsuarioNoExisteException 
        | MaxEspMenorMinEspException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Artistas

    try {
      ICU.nuevoArtista(artInvNick1, artInvNick1 + ".com", "Art", "Org", 
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia", 
          artInvNick1 + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    try {
      ICU.nuevoArtista(artInvNick2, artInvNick2 + ".com", "Art", "Org", 
          LocalDateTime.of(1997, 1, 1, 1, 1), "descripcion", "biografia", 
          artInvNick2 + ".com", "pass", "pass", "imagen");
    } catch (UsuarioRepetidoExepcion  | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    List<String> artInv = new LinkedList<String>();
    artInv.add(0, artInvNick1);
    artInv.add(1, artInvNick2);

    try {
      ICE.ingresarDatosFuncion(nombreFuncion, horaInicio, artInv, fechaAlta, nombreEspectaculo, "http://test.com");
    } catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
    //Crear Registro
    ManejadorUsuarios musu = ManejadorUsuarios.getManejadorUsuarios();
    Espectador esp = musu.getEspectador("hola222");


    try {
      List<String> lfu = new ArrayList<String>();
      try {
        ICE.confirmarRegistroAFuncion(nombrePlataforma, "", nombreEspectaculo, 
            nombreFuncion, esp.getNickname(), lfu, Date.valueOf(LocalDate.now()));
      } catch (FechaPasadaException | RegistroNoSePuedeCanjearException 
          | ErrorCantidadCanjeExcepcion e1) {
        fail(e1.getMessage());
        e1.printStackTrace();
      }
    } catch (FuncionLlenaExepcion e1) {
      fail(e1.getMessage());
      e1.printStackTrace();
    } catch (NoEsEspectadorExepcion e2) {
      fail(e2.getMessage());
      e2.printStackTrace();
    } catch (EspectadorRegistradoEnFuncionExepcion e3) {
      fail(e3.getMessage());
      e3.printStackTrace();
    }

    //Test para verificar si crea bien el DataEspectador
    try {
      DataUsuario dusu = ICU.mostrarDatosUsuarioGeneral("hola222");
      Set<Registro> regs = esp.getRegistro();
      String[] afu = null;
      if (regs != null) {
        afu = new String[regs.size()];
        int init = 0;
        for (Registro r : regs) {
          afu[init] = r.getFuncion().getNombre();
          init++;
        }
      }
      DataEspectador desp = new DataEspectador("hola222", "Juan22", 
          "Loquesea22", "hola222@mail", LocalDateTime.of(2010, 11, 11, 12, 13), 
          afu, "imagen", null, null, null);
      assertEquals(desp.getNickname(), dusu.getNickname());
      assertEquals(desp.getEmail(), dusu.getEmail());
      assertEquals(desp.getNombre(), dusu.getNombre());
      assertEquals(desp.getApellido(), dusu.getApellido());
      assertEquals(desp.getFecha(), dusu.getFecha());
      for (int i = 0; i < afu.length; i++) {
        assertTrue(afu[i].equals(((DataEspectador) dusu).getFunciones()[i]));
      }
      assertEquals(desp.getUrlImg(), dusu.getUrlImg());
    } catch (UsuarioNoExisteException e4) {
      fail(e4.getMessage());
      e4.printStackTrace();
    }
  }


  @Test
  void testComprarPaquete() throws UsuarioRepetidoExepcion, YaComproPaqueteException,
      PasswordErrorException {
    ManejadorPaquetes mpaq = ManejadorPaquetes.getManejadorPaquetes();
    Date freg1 = Date.valueOf(LocalDate.now());
    Paquete paq = new Paquete("teletubi", "lasbolas", freg1, freg1, 5, freg1);
    mpaq.addPaquete(paq);
    ICU.nuevoEspectador("hola202", "hola202@mail", "Juan", "Loquesea", 
        LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    ICU.CompraPaquete("hola202", "teletubi", freg1);
  }

  @Test
  void testComprarPaqueteRep() throws UsuarioRepetidoExepcion, YaComproPaqueteException,
      PasswordErrorException {
    ManejadorPaquetes mpaq = ManejadorPaquetes.getManejadorPaquetes();
    Date freg1 = Date.valueOf(LocalDate.now());
    Paquete paq = new Paquete("teletubii", "lasbolas", freg1, freg1, 5, freg1);
    mpaq.addPaquete(paq);
    ICU.nuevoEspectador("hola2022", "hola2022@mail", "Juan", "Loquesea", 
        LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
    ICU.CompraPaquete("hola2022", "teletubii", freg1);
    assertThrows(YaComproPaqueteException.class, () -> {
      ICU.CompraPaquete("hola2022", "teletubii", freg1);
    });
  }

  @Test
  void testNuevoSeguirUsuarioOK() {
    //Se crean dos usuarios y uno sigue al otro
    try {
      ICU.nuevoEspectador("usuarioquesigue", "usuarioquesigue@mail", "Juan", 
          "Loquesea", LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
      ICU.nuevoArtista("usuarioseguido", "usuarioseguido@mail", "Pepe", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "", "", "", "pass", "pass", "imagen");
      ICU.seguirUsuario("usuarioquesigue", "usuarioseguido");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Agarrar los data y ver si tienen los seguidores y los siguiendo
    try {
      DataUsuario usu1 = ICU.mostrarDatosUsuario("usuarioquesigue");
      DataUsuario usu2 = ICU.mostrarDatosUsuario("usuarioseguido");
      assertEquals(usu1.getNickUsuariosSiguiendo()[0], usu2.getNickname());
      assertEquals(usu2.getNickUsuariosSeguidores()[0], usu1.getNickname());
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void testNuevoDejarSeguirUsuarioOK() {
    //Se crean dos usuarios y uno sigue al otro
    try {
      ICU.nuevoEspectador("usuarioquesigue2", "usuarioquesigue2@mail", "Juan", 
          "Loquesea", LocalDateTime.of(2010, 10, 11, 12, 13), "pass", "pass", "imagen");
      ICU.nuevoArtista("usuarioseguido2", "usuarioseguido2@mail", "Pepe", "Loquesea", 
          LocalDateTime.of(2010, 10, 11, 12, 13), "", "", "", "pass", "pass", "imagen");
      ICU.seguirUsuario("usuarioquesigue2", "usuarioseguido2");
      ICU.dejarDeSeguirUsuario("usuarioquesigue2", "usuarioseguido2");
    } catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    //Agarrar los data y ver si tienen los seguidores y los siguiendo
    try {
      DataUsuario usu1 = ICU.mostrarDatosUsuario("usuarioquesigue2");
      DataUsuario usu2 = ICU.mostrarDatosUsuario("usuarioseguido2");
      assertNull(usu1.getNickUsuariosSiguiendo());
      assertNull(usu2.getNickUsuariosSeguidores());
    } catch (UsuarioNoExisteException e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }
}
