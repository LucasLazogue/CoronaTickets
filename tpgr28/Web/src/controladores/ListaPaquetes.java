package controladores;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataPaquete;

@WebServlet(description = "Página con lista de paquetes", urlPatterns = { "/ListaPaquetes" })
public class ListaPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaPaquetes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    servidor.PublicadorService service = new servidor.PublicadorService();
    servidor.Publicador port = service.getPublicadorPort();
	  //Cargar datos de prueba
    port.cargarDatosPrueba();
    //Agarrar nombres de los paquetes
		String[] nomPaquetes = new String[port.listarPaquetes().getItem().size()];
		port.listarPaquetes().getItem().toArray(nomPaquetes);
	  //Convertir los nombres de los espectaculos en datatypes para tener la imagen
		DataPaquete[] paquetes = null;
		if (nomPaquetes != null) {
			paquetes = new DataPaquete[nomPaquetes.length];
		    for (int i = 0; i < nomPaquetes.length; i++) {
					paquetes[i] = port.seleccionarPaquete(nomPaquetes[i]);
		    }
		}
    //Llamar a JSP con la información correcta
    request.setAttribute("paquetes", paquetes);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/vistasLista/ListaPaquetes.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
