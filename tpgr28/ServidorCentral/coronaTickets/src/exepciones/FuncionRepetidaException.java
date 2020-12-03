package exepciones;

@SuppressWarnings("serial")
public class FuncionRepetidaException extends Exception {
  public FuncionRepetidaException(String except) {
    super(except);
  }
}
