package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataUsuario;
import servidor.PasswordErrorException_Exception;
import servidor.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet(description = "Iniciar sesion", urlPatterns = { "/IniciarSesion" })
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IniciarSesion() {
        super();
    }
    
    private void hacerRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*
       * Fabrica f = Fabrica.getInstancia(); IUsuarioController ICU =
       * f.getIUsuarioController();
       */
      servidor.PublicadorService service = new servidor.PublicadorService();
      servidor.Publicador port = service.getPublicadorPort();
      String login = request.getParameter("login");
      String password = request.getParameter("password");
      try {
          //Cargar datos de prueba
          
          port.cargarDatosPrueba();
          //Validar usuario
          if (port.validarUsuario(login, password)) {
              //Guardar en sesión al usuario y redirigir
              DataUsuario u = port.mostrarDatosUsuario(login);
              request.getSession().setAttribute("usuario", u);
              ServletContext context = getServletContext();
              RequestDispatcher dispatcher =
              context.getRequestDispatcher("/home");
              dispatcher.forward(request, response);
          }  
      } catch (UsuarioNoExisteException_Exception e) {
        ServletContext context = getServletContext();
        request.setAttribute("error", "usuario");
        RequestDispatcher dispatcher =
        context.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
      } catch (PasswordErrorException_Exception e) {
        ServletContext context = getServletContext();
        request.setAttribute("error", "password");
        RequestDispatcher dispatcher =
        context.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
      }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameter("login") == null) {
    	     ServletContext context = getServletContext();
           RequestDispatcher dispatcher =
           context.getRequestDispatcher("/login.jsp");
           dispatcher.forward(request, response);
  	    } else {
  	        this.hacerRequest(request, response);
  	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	  if (request.getParameter("login") == null) {
         ServletContext context = getServletContext();
         RequestDispatcher dispatcher =
         context.getRequestDispatcher("/login.jsp");
         dispatcher.forward(request, response);
     }
     this.hacerRequest(request, response);
	}
}
