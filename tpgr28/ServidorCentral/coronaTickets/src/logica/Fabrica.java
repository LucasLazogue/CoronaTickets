package logica;

public class Fabrica {

  private static Fabrica instancia = null;

  /**
  * Obtener la instancia del Singleton fábrica.
  */
  public static Fabrica getInstancia() {
    if (instancia == null) {
      instancia = new Fabrica();
    }
    return instancia;
  }

  public IUsuarioController getIUsuarioController() {
    return new UsuarioController();
  }

  public IEspectaculoController getIEspectaculoController() {
    return new EspectaculoController();
  }

  private Fabrica() {}
}
