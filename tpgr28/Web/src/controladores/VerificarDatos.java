package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataUsuario;
import servidor.UsuarioNoExisteException_Exception;

@WebServlet("/VerificarDatos")
public class VerificarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerificarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean nicknameValido(String nick, List<String> usuarios) {
    	if (usuarios.contains(nick)) return false;
    	return true;

    }
    
    public boolean mailValido(String mail, List<DataUsuario> usuarios) {
    	for (DataUsuario usu: usuarios) {
    		if (usu.getEmail().equals(mail)) return false;
    	}
    	return true;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    
	    //Formato de la respuesta
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    try {
		    //Agarrar todos los usuarios
			List<String> nickUsuarios = port.listarUsuarios().getItem();
			List<DataUsuario> usuarios = new ArrayList<DataUsuario>();
			for (String nick: nickUsuarios) {
				usuarios.add(port.mostrarDatosUsuario(nick));
			}
			
			//En funcion del parametro, validar y responder
		    if (request.getParameter("nickname") != null && nicknameValido(request.getParameter("nickname"), nickUsuarios)) {
		    	response.getWriter().println("<p style='color: green;'>✔ El nickname está disponible</p>");
		    } else if (request.getParameter("nickname") != null) {
		    	response.getWriter().println("<p style='color: red;'>✗ El nickname está en uso</p>");
		    } else if (request.getParameter("mail") != null && mailValido(request.getParameter("mail"), usuarios)) {
		    	response.getWriter().println("<p style='color: green;'>✔ El email está disponible</p>");	    	
		    } else if (request.getParameter("mail") != null) {
		    	response.getWriter().println("<p style='color: red;'>✗ El email está en uso</p>");
		    }
		    
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
