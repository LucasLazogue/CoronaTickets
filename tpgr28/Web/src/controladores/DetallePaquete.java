package controladores;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

import servidor.DataPaquete;
import servidor.DataUsuario;
import servidor.UsuarioNoExisteException_Exception;
import servidor.YaComproPaqueteException_Exception;

/**
 * Servlet implementation class DetallePaquete
 */
@WebServlet(description = "Pagina con Detalles de Paquete", urlPatterns = "/DetallePaquete")
public class DetallePaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallePaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Cargar datos de prueba
		/*
		 * IUsuarioController ICU = Fabrica.getInstancia().getIUsuarioController();
		 * ICU.cargarDatosPrueba(); //Obtener paquete IEspectaculoController ICE =
		 * Fabrica.getInstancia().getIEspectaculoController();
		 */
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		DataPaquete paquete = port.seleccionarPaquete((String)request.getParameter("paquete"));
		List<String> espPaq = port.listarEspectaculosEnPaquete("", paquete.getNombre()).getItem();
		request.setAttribute("paqueteData", paquete);
		request.setAttribute("espPaq", espPaq);
		//Llamar al JSP
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = 
		context.getRequestDispatcher("/vistasDetalle/DetallePaquete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Traer controlador
		//IUsuarioController ICU = Fabrica.getInstancia().getIUsuarioController();
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		
		//Traer datos
		String nick = ((DataUsuario)request.getSession().getAttribute("usuario")).getNickname();
		String nombrePaquete = request.getParameter("paquete");
		Date fecha = new Date();
		GregorianCalendar fechaAux = new GregorianCalendar();
		fechaAux.setTime(fecha);
		XMLGregorianCalendar fechaXML;
		try {
			fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaAux);
		
			//Ejecutar operacion
			try {
				port.compraPaquete(nick, nombrePaquete, fechaXML);
	            request.getSession().setAttribute("usuario", port.mostrarDatosUsuario(nick));
			} catch (YaComproPaqueteException_Exception | UsuarioNoExisteException_Exception e) {
				request.setAttribute("error", e.getMessage());
		        doGet(request, response);
			}
			doGet(request, response);
		
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
