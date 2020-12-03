package exepciones;


@SuppressWarnings("serial")
public class UsuarioNoExisteException extends Exception {
  public UsuarioNoExisteException(String exept) {
    super(exept);
  }
}
