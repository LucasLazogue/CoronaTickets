package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataArtista;
import servidor.DataUsuario;
import servidor.PasswordErrorException_Exception;
import servidor.UsuarioNoExisteException_Exception;

@WebServlet(description = "Iniciar sesion", urlPatterns = { "/IniciarSesion" })
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IniciarSesion() {
        super();
    }
    
    private void hacerRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    //Cargar datos de prueba
	    port.cargarDatosPrueba();
	    //Agarrar parametros
	    String login = request.getParameter("login");
	    String password = request.getParameter("password");
	    try {
		    //Validar usuario
		    if (port.validarUsuario(login, password)) {
		        //Guardar en sesión al usuario y redirigir
		        DataUsuario u = port.mostrarDatosUsuario(login);
		        //Solo puede iniciar sesion el espectador
		        if(u instanceof DataArtista) artistaNoIniciaSesion(request, response);
		        //Si selecciona recordarme, guardar datos en sesion
			    if (request.getParameter("recordarme") != null) {
			        request.getSession().setAttribute("nomUsuario", login);
			        request.getSession().setAttribute("password", password);
			    } else {
			    	request.getSession().removeAttribute("nomUsuario");
			    	request.getSession().removeAttribute("password");
			    }
		        request.getSession().setAttribute("usuario", u);
		        ServletContext context = getServletContext();
		        RequestDispatcher dispatcher =
		        context.getRequestDispatcher("/home");
		        dispatcher.forward(request, response);
		    }
	    } catch (UsuarioNoExisteException_Exception e) {
	    	//Verificar si se ingreso email en vez de nombre de usuario
			String[] usuarios;
			try {
				usuarios = port.listarUsuarios().getItem().toArray(new String[0]);
				int cont = 0;
				for (String u: usuarios) {
					DataUsuario dtU = port.mostrarDatosUsuario(u);
					if (dtU.getEmail().equals(login) && 
						port.validarUsuario(dtU.getNickname(), password)) {
						//Solo puede iniciar sesion el espectador
			            if(dtU instanceof DataArtista) artistaNoIniciaSesion(request, response);
				        //Si selecciona recordarme, guardar datos en sesion
					    if (request.getParameter("recordarme") != null) {
					        request.getSession().setAttribute("nomUsuario", login);
					        request.getSession().setAttribute("password", password);
					    } else {
					    	request.getSession().removeAttribute("nomUsuario");
					    	request.getSession().removeAttribute("password");
					    }
			            //Guardar en sesión al usuario y redirigir
				        request.getSession().setAttribute("usuario", dtU);
				        ServletContext context = getServletContext();
				        RequestDispatcher dispatcher =
				        context.getRequestDispatcher("/home");
				        dispatcher.forward(request, response);
					} else { 
						cont++;
						if (cont == usuarios.length) {
							//No es ni nombre de usuario ni email
							ServletContext context = getServletContext();
							request.setAttribute("error", "usuario");
							RequestDispatcher dispatcher =
							context.getRequestDispatcher("/login.jsp");
							dispatcher.forward(request, response);
						}
					}
				}
			} catch (UsuarioNoExisteException_Exception e1) {
				//Nunca deberia romperse por esta excepcion
				e1.printStackTrace();
				
			} catch (PasswordErrorException_Exception e1) {
				ServletContext context = getServletContext();
		        request.setAttribute("error", "password");
		        RequestDispatcher dispatcher =
		        context.getRequestDispatcher("/login.jsp");
		        dispatcher.forward(request, response);
			}
	    } catch (PasswordErrorException_Exception e) {
		    ServletContext context = getServletContext();
		    request.setAttribute("error", "password");
		    RequestDispatcher dispatcher =
		    context.getRequestDispatcher("/login.jsp");
		    dispatcher.forward(request, response);
	    }
    }

	private void artistaNoIniciaSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
	    request.setAttribute("error", "artista");
	    RequestDispatcher dispatcher =
	    context.getRequestDispatcher("/login.jsp");
	    dispatcher.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   ServletContext context = getServletContext();
           RequestDispatcher dispatcher =
           context.getRequestDispatcher("/login.jsp");
           dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.hacerRequest(request, response);
	}
}
