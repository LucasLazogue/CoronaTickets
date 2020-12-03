package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPuntaje {

  private int puntaje;
  private String espectaculo;
  
  public DataPuntaje() {}
  
  public DataPuntaje(int puntaje, String espectaculo) {
    this.puntaje = puntaje;
    this.espectaculo = espectaculo;
  }
  
  public int getPuntaje() {
    return this.puntaje;
  }
  
  public String fetEspectaculo() {
    return this.espectaculo;
  }
}
