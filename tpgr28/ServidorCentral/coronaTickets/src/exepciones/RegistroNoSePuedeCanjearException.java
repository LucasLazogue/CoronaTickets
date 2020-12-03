package exepciones;

@SuppressWarnings("serial")
public class RegistroNoSePuedeCanjearException extends Exception {
  public RegistroNoSePuedeCanjearException(String exept) {
    super(exept);
  }
}