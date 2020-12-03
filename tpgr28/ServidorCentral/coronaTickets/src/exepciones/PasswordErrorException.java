package exepciones;

@SuppressWarnings("serial")
public class PasswordErrorException extends Exception{
  public PasswordErrorException(String exept) {
    super(exept);
  }
}