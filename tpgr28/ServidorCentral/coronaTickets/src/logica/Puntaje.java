package logica;

public class Puntaje {
  private int puntaje;
  private Espectaculo espectaculo;
  
  public Puntaje(int puntaje, Espectaculo espectaculo) {
    this.puntaje = puntaje;
    this.espectaculo = espectaculo;
  }
  
  public void modificarPuntaje(int puntaje) {
    this.puntaje = puntaje;
  }
  
  public int getPuntaje() {
    return this.puntaje;
  }
  
  public Espectaculo getEspectaculo() {
    return this.espectaculo;
  }
}
