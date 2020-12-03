package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "Historial", urlPatterns = { "/Historial" })
public class Historial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Historial() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    
	    String codigo = request.getParameter("codigo");
	      if (codigo != null && port.validarCodigo(codigo)) {
            ServletContext context = getServletContext();
            List<servidor.DataDatos> historico = port.listarDatosHistorial().getItem();
            request.setAttribute("historico", historico);
            RequestDispatcher dispatcher =
            context.getRequestDispatcher("/Historial.jsp");
            dispatcher.forward(request, response);
	      }
	      else {
	          ServletContext context = getServletContext();
	          RequestDispatcher dispatcher =
	          context.getRequestDispatcher("/home");
	          dispatcher.forward(request, response);
	      }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
