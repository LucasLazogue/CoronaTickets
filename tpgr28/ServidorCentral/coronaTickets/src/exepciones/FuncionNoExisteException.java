package exepciones;


@SuppressWarnings("serial")
public class FuncionNoExisteException extends Exception {
  public FuncionNoExisteException(String exept) {
    super(exept);
  }
}