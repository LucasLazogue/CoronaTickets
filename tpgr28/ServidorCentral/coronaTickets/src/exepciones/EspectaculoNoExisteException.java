package exepciones;

@SuppressWarnings("serial")
public class EspectaculoNoExisteException extends Exception {
  public EspectaculoNoExisteException(String exept) {
    super(exept);
  }
}