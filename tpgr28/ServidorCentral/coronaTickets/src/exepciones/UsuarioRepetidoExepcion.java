package exepciones;

@SuppressWarnings("serial")
public class UsuarioRepetidoExepcion extends Exception {
  public UsuarioRepetidoExepcion(String string) {
    super(string);
  }
}
