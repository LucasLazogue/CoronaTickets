package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataEspectador;
import servidor.DataFuncion;
import servidor.DataUsuario;
import servidor.EspectaculoNoExisteException_Exception;

@WebServlet(description = "Pagina con Detalles de Funcion", urlPatterns = "/DetalleFuncion")
public class DetalleFuncion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DetalleFuncion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		try {
			DataFuncion funcion = port.consultaFuncion((String)request.getParameter("funcion"), (String)request.getParameter("espectaculo"));
			request.setAttribute("funcionData", funcion);
		} catch (EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("funcionData", null);
		}
		//Llamar al JSP si el usuario es espectador, sino ir al login
		ServletContext context = getServletContext();
		if ((DataUsuario)request.getSession().getAttribute("usuario") instanceof DataEspectador) {
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/vistasDetalle/DetalleFuncion.jsp");
			dispatcher.forward(request, response);
	    } else {
	    	RequestDispatcher dispatcher =
	    	context.getRequestDispatcher("/IniciarSesion");
	    	dispatcher.forward(request, response);
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
