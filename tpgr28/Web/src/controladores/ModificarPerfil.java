package controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

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

import servidor.DataArtista;
import servidor.DataEspectador;
import servidor.DataUsuario;
import servidor.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class DetallesUsuario
 */
@WebServlet(description = "Modificar datos de perfil", urlPatterns = { "/ModificarPerfil" })
public class ModificarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		port.cargarDatosPrueba();
		DataUsuario usuario = (DataUsuario)request.getSession().getAttribute("usuario");		
		request.setAttribute("fecha", usuario.getFechaDate());
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/ModificarPerfil.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataUsuario u = (DataUsuario)request.getSession().getAttribute("usuario");
		//IUsuarioController IUC = Fabrica.getInstancia().getIUsuarioController();
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		if (u instanceof DataArtista) {
			DataArtista a = (DataArtista)u;
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String descripcion = request.getParameter("descripcion");
			String biografia = request.getParameter("biografia");
			String web = request.getParameter("web");
			String urlimg = request.getParameter("urlimg");
			String fechanac = request.getParameter("fechanac");
			//System.out.print(nombre +" "+ descripcion + " " + fechanac);

			
			SimpleDateFormat fechastr = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date fecha = (Date)fechastr.parse(fechanac);
				//LocalDateTime fechafinal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				GregorianCalendar fechaAux = new GregorianCalendar();
				fechaAux.setTime(fecha);
				XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaAux);
				try {
					port.modificarDatosUsuario(a.getNickname(), nombre, apellido, fechaXML, descripcion, biografia, web, urlimg, "Artista");
				} catch (UsuarioNoExisteException_Exception e) {
					// TODO Auto-generated catch block
					//System.out.print(e);
					e.printStackTrace();
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.print(e);
			} catch (DatatypeConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			DataEspectador e = (DataEspectador)u;
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String urlimg = request.getParameter("urlimg");
			String fechanac = request.getParameter("fechanac");
			SimpleDateFormat fechastr = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			try {
				fecha = (Date)fechastr.parse(fechanac);
				//LocalDateTime fechafinal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				GregorianCalendar fechaAux = new GregorianCalendar();
				fechaAux.setTime(fecha);
				XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaAux);
				try {
					port.modificarDatosUsuario(e.getNickname(), nombre, apellido, fechaXML, "", "", "", urlimg, "Espectador");
				} catch (UsuarioNoExisteException_Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			} catch (ParseException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (DatatypeConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			DataUsuario usuario = port.mostrarDatosUsuario(u.getNickname());
			request.getSession().setAttribute("usuario", usuario);
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

}
