package controladores;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.UsuarioNoExisteException_Exception;
import servidor.DataUsuario;

@WebServlet(description = "Página con lista de usuarios", urlPatterns = { "/ListaUsuarios" })
public class ListaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaUsuarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    servidor.PublicadorService service = new servidor.PublicadorService();
    servidor.Publicador port = service.getPublicadorPort();
	  //Cargar datos de prueba
		port.cargarDatosPrueba();
    //Agarrar nickname de los usuarios y convertir en datatype para tener imagen
		DataUsuario[] usuarios;
		try {
			String[] nickUsuarios = new String[port.listarUsuarios().getItem().size()];
			port.listarUsuarios().getItem().toArray(nickUsuarios);
			usuarios = new DataUsuario[nickUsuarios.length];
		    for (int i = 0; i < nickUsuarios.length; i++) {
					usuarios[i] = port.mostrarDatosUsuario(nickUsuarios[i]);
		    }
		} catch (UsuarioNoExisteException_Exception e) {
			usuarios = null;
		}
		if (request.getSession().getAttribute("usuario") != null) {
			String nickUsuarioSesion = ((DataUsuario)request.getSession().getAttribute("usuario")).getNickname();
			//Si se pasa por URL para seguir o dejar de seguir a usuario
			if (request.getParameter("seguir") != null)
				port.seguirUsuario(nickUsuarioSesion, request.getParameter("seguir"));
			if (request.getParameter("noseguir") != null)
				port.dejarDeSeguirUsuario(nickUsuarioSesion, request.getParameter("noseguir"));
	        //Actualizar usuario de la sesión debido a posibles cambios en la lista de los usuarios que sigue
			try {
				request.getSession().setAttribute("usuario", port.mostrarDatosUsuario(nickUsuarioSesion));
			} catch (UsuarioNoExisteException_Exception e) {
				e.printStackTrace();
			}
		}
		//Llamar a JSP con la información correcta
        request.setAttribute("usuarios", usuarios);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/vistasLista/ListaUsuarios.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
