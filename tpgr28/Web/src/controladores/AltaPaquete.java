package controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import servidor.FinVigAntesIniVigException_Exception;
import servidor.PaqueteRepetidoException_Exception;



/**
 * Servlet implementation class AltaPaquete
 */
@WebServlet(description = "Crear Paquete", urlPatterns = { "/altaPaquete" })
public class AltaPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AltaPaquete() {
        super();        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/altaPaquete.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();	    	
    	try {
    		SimpleDateFormat Format1 = new  SimpleDateFormat("yyyy-MM-dd");
	    	String nombrep = request.getParameter("nombrep");
	    	String descrip = request.getParameter("descripcion");
	    	int descuento = Integer.parseInt(request.getParameter("descuento"));
	    	String urlport = request.getParameter("urlport");
	    	Date fechaR = new Date();
    		GregorianCalendar fechaux = new GregorianCalendar();
    		fechaux.setTime(fechaR);
    		XMLGregorianCalendar fechaRR;
    		fechaRR = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaux);
    		Date fechad = Format1.parse(request.getParameter("fechad"));
    		GregorianCalendar fechaux2 = new GregorianCalendar();
    		fechaux2.setTime(fechad);
    		XMLGregorianCalendar fechadd;
    		fechadd = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaux2);
    		
    		Date fechah = Format1.parse(request.getParameter("fechah"));
    		GregorianCalendar fechaux3 = new GregorianCalendar();
    		fechaux3.setTime(fechah);
    		XMLGregorianCalendar fechahh;
    		fechahh = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaux3);
    		
			port.crearPaquete(nombrep, descrip, fechadd, fechahh, descuento, fechaRR, urlport); 
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
    	} catch (NumberFormatException | ParseException | DatatypeConfigurationException | FinVigAntesIniVigException_Exception | PaqueteRepetidoException_Exception e) {
			request.setAttribute("error", e.getMessage());
	        doGet(request, response);
		}
	}

}
