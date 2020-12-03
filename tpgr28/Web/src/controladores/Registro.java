package controladores;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import servidor.PasswordErrorException_Exception;
import servidor.UsuarioRepetidoExepcion_Exception;


@WebServlet(description = "Registro de usuario", urlPatterns = { "/Registro" })
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Registro() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r = request.getParameter("tipo");
		if (r!=null) {
			request.setAttribute("tipo", r);
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/Registro.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		String nombre = request.getParameter("nombre");
		String nickname = request.getParameter("nickname");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String urlimg = request.getParameter("urlimg");
		String pass = request.getParameter("Password");
		String valid = request.getParameter("validPass");
		try {
			String fechanac = request.getParameter("fechanac");
			SimpleDateFormat fechastr = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = (Date)fechastr.parse(fechanac);
			GregorianCalendar fechaAux = new GregorianCalendar();
			fechaAux.setTime(fecha);
			XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaAux);
			if (request.getParameter("descripcion") != null ) {
				String descripcion = request.getParameter("descripcion");
				String biografia = request.getParameter("biografia");
				String web = request.getParameter("web");
				try {
					port.nuevoArtista(nickname, email, nombre, apellido, fechaXML, descripcion, biografia, web, pass, valid, urlimg);
	
				} catch (PasswordErrorException_Exception e) {
					request.setAttribute("errorpass", e.getMessage());
					doGet(request, response);
				} catch (UsuarioRepetidoExepcion_Exception e) {
					request.setAttribute("errorusuario", e.getMessage());
					doGet(request, response);
				}
			} else {
				try {
					request.setAttribute("tipo", "espectador");
					port.nuevoEspectador(nickname, email, nombre, apellido, fechaXML, pass, valid, urlimg);
				} catch (UsuarioRepetidoExepcion_Exception e) {
					request.setAttribute("errorusuario", e.getMessage());
					doGet(request, response);
				} catch (PasswordErrorException_Exception e) {
					request.setAttribute("errorpass", e.getMessage());
					doGet(request, response);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = 
		context.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

}
