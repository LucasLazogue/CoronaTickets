package exepciones;

@SuppressWarnings("serial")
public class EspectadorRegistradoEnFuncionExepcion extends Exception {
  public EspectadorRegistradoEnFuncionExepcion(String exept) {
    super(exept);
  }
}