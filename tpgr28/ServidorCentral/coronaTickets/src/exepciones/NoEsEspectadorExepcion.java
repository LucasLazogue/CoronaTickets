package exepciones;

@SuppressWarnings("serial")
public class NoEsEspectadorExepcion extends Exception {
  public NoEsEspectadorExepcion(String exept) {
    super(exept);
  }
}