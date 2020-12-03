package logica;

import java.util.Arrays;
import java.util.List;

public class ListaString {
  List<String> strings;
  
  public ListaString() {}
  
  public ListaString(String[] array) {
    strings = Arrays.asList(array);
  }
  
  public List<String> getListaString() {
    return strings;
  }
}