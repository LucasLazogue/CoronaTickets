package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ManejadorUsuarios {
  /*Atributos */
  private static ManejadorUsuarios instancia = null;

  private Map<String, Artista> artistas;
  private Map<String, Espectador> espectadores;

  /*Metodos*/
  //ManejadorUsuarios getManejadorUsuarios()
  //void agregarArtista(String nick)
  //void agregarEspectador(String nick)
  //Espectador getEspectador(String nick)
  //Artista getArtista(String nick)
  //Artista[] getArtistas()
  //Espectador[] getEspectadores()


  private ManejadorUsuarios() {
    artistas = new HashMap<String, Artista>();
    espectadores = new HashMap<String, Espectador>();
  }

  /**
   * Manejador Usuarios.
   */
  public static ManejadorUsuarios getManejadorUsuarios() {
    if (instancia == null) {
      instancia = new ManejadorUsuarios();
    }
    return instancia;
  }

  /**
   * Agregar artista.
   */
  public void agregarArtista(Artista artista) {
    String nick = artista.getNickname();
    if (!this.artistas.containsKey(nick)) {
      this.artistas.put(nick, artista);
    }
  }

  /**
   * Agregar espectador.
   */
  public void agregarEspectador(Espectador espectador) {
    String nick = espectador.getNickname();
    if (!this.espectadores.containsKey(nick)) {
      this.espectadores.put(nick, espectador);
    }
  }

  public Espectador getEspectador(String nick) {
    return this.espectadores.get(nick);
  }

  public Artista getArtista(String nick) {
    return this.artistas.get(nick);
  }

  /**
   * Get artistas.
   */
  public Artista[] getArtistas() {
    if (this.artistas.isEmpty()) {
      return null;
    } else {
      Collection<Artista> arts = this.artistas.values();
      Object[] obj = arts.toArray();
      Artista[] art = new Artista[obj.length];
      for (int i = 0; i < obj.length; i++) {
        art[i] = (Artista) obj[i];
      }
      return art;
    }
  }

  /**
   * Get Artistas.
   */
  public Map<String, Artista> getArtistas(List<String> nickArtista) {
    if (nickArtista == null) {
      return null;
    }
    int listSize = nickArtista.size();
    Map<String, Artista> resultado = new HashMap<String, Artista>();
    for (int i = 0; i < listSize; i++) {
      resultado.put(nickArtista.get(i), artistas.get(nickArtista.get(i)));
    }
    return resultado;
  }

  /**
   * Get espectadores.
   */
  public Espectador[] getEspectadores() {
    if (this.espectadores.isEmpty()) {
      return null;
    } else {
      Collection<Espectador> espec = this.espectadores.values();
      Object[] obj = espec.toArray();
      Espectador[] esp = new Espectador[obj.length];
      for (int i = 0; i < obj.length; i++) {
        esp[i] = (Espectador) obj[i];
      }
      return esp;
    }
  }


}
