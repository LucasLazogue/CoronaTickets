package controladores;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import servidor.DataEspectaculo;
import servidor.DataPaquete;


@WebServlet(description = "Página que retorna la búsqueda", urlPatterns = { "/Busqueda" })
public class Busqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Busqueda() {
        super();
    }
    
    private Object[] ordenar(Object[] arr, boolean alfabetico) {
      if (arr != null) {
      	if (alfabetico) {
  	    	for (int i = 0; i < arr.length; i++) {
  	    		for (int j = i; j >= 0; j--) {
  	    			if (j > 0) {
  	    				String nom1 = arr[j] instanceof DataEspectaculo? 
  	    						((DataEspectaculo)arr[j]).getNombre() : ((DataPaquete)arr[j]).getNombre();
  						String nom2 = arr[j - 1] instanceof DataEspectaculo? 
  	    						((DataEspectaculo)arr[j - 1]).getNombre() : ((DataPaquete)arr[j - 1]).getNombre();
  	    				if (nom1.compareToIgnoreCase(nom2) < 0) {
  	    					Object aux = arr[j];
  	    					arr[j] = arr[j - 1];
  	    					arr[j -1] = aux;
  	    				}
  	    			}
  	    		}
  	    	}
      	} else {
  	    	for (int i = 0; i < arr.length; i++) {
  	    		for (int j = i; j >= 0; j--) {
  	    			if (j > 0) {
  	    				  XMLGregorianCalendar f1 = arr[j] instanceof DataEspectaculo? 
  	    					(XMLGregorianCalendar) ((DataEspectaculo)arr[j]).getFechaRegistro() : ((DataPaquete)arr[j]).getFechaDeAlta();
  	    					XMLGregorianCalendar f2 = arr[j - 1] instanceof DataEspectaculo? 
  	    					(XMLGregorianCalendar) ((DataEspectaculo)arr[j - 1]).getFechaRegistro() : ((DataPaquete)arr[j - 1]).getFechaDeAlta();
  	    				if (f2.compare(f1) == DatatypeConstants.LESSER) {
  	    					Object aux = arr[j];
  	    					arr[j] = arr[j - 1];
  	    					arr[j -1] = aux;
  	    				}
  	    			}
  	    		}
  	    	}
      	}
      }
    	return arr;
    }
    
    private Object[] filtrarPorCategoria(Object[] resultado, String cat) {
    	Set<Object> filtrado = new HashSet<Object>();
    	for (Object r: resultado) {
    		if (r instanceof DataEspectaculo) {
    			for (String categoria: ((DataEspectaculo)r).getNombresCategorias()) {
    				if (categoria.equals(cat)) filtrado.add(r);
    			}
    		} else {
    			for (String categoria: ((DataPaquete)r).getCategorias()) {
    				if (categoria.equals(cat)) filtrado.add(r);
    			}
    		}
    	}
    	if (filtrado.size() == 0) return null;
		return filtrado.toArray();
    }
    
    private Object[] filtrarPorPlataforma(Object[] resultado, String plat, servidor.Publicador port) {
      if (resultado != null) {
      	Set<Object> filtrado = new HashSet<Object>();
      	for (Object r: resultado) {
      		if (r instanceof DataEspectaculo) {
      			for (String esp: port.seleccionarPlataforma(plat).getEspectaculos()) {
      				if (esp.equals(((DataEspectaculo)r).getNombre())) filtrado.add(r);
      			}
      		} else {
      			for (String esp: ((DataPaquete)r).getEspectaculos()) {
      				for (String esp2: port.seleccionarPlataforma(plat).getEspectaculos()) {
          				if (esp2.equals(esp) && !filtrado.contains(r)) filtrado.add(r);
          			}
      			}
      		}
      	}
      	if (filtrado.size() == 0) return null;
      	return filtrado.toArray();
      } else
        return null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    servidor.PublicadorService service = new servidor.PublicadorService();
    servidor.Publicador port = service.getPublicadorPort();
		//Cargar datos de prueba
    port.cargarDatosPrueba();
		Object[] resultado = port.busqueda(request.getParameter("clave")).getItem().toArray();
		if (resultado != null) {
			if (request.getParameter("categoria") != null && !request.getParameter("categoria").equals("Cualquiera")) {
				resultado = filtrarPorCategoria(resultado, request.getParameter("categoria"));
			}
			if (request.getParameter("plataforma") != null && !request.getParameter("plataforma").equals("Cualquiera")) {
				resultado = filtrarPorPlataforma(resultado, request.getParameter("plataforma"), port);
			}
			if (request.getParameter("ordenar") != null && request.getParameter("ordenar").equals("fecha"))
				resultado = ordenar(resultado, false);
			else
				resultado = ordenar(resultado, true);
		}
	    //Llamar a JSP con la información correcta
        request.setAttribute("resultado", resultado);
        request.setAttribute("plataformas", port.getPlataformas().getItem().toArray(new String[0]));
        request.setAttribute("categorias", port.listarCategorias().getItem().toArray(new String[0]));
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/Busqueda.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
