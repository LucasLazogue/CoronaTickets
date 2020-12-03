package controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

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

import net.java.dev.jaxb.array.StringArray;
import servidor.DataArtista;
import servidor.EspectaculoNoExisteException_Exception;
import servidor.FuncionRepetidaException_Exception;
import servidor.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class AltaFuncion
 */
@WebServlet(description = "Crear Funcion", urlPatterns = { "/altaFuncion" })
public class AltaFuncion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     
     */
    public AltaFuncion() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
    	port.cargarDatosPrueba();
    	DataArtista u = (DataArtista)request.getSession().getAttribute("usuario");
    	Set<String> especAcetp = new HashSet<String>();
    	Set<String> artistasInv = new HashSet<String>();
    	try {
			StringArray usuarios = port.listarUsuarios();
			for(String user : usuarios.getItem()) {
				if (user != u.getNickname() && (port.mostrarDatosUsuario(user) instanceof DataArtista))
					artistasInv.add(user);
			}
			Object[] o = artistasInv.toArray();
			String[] resArtI = new String[o.length];
			for (int i = 0; i < o.length; i++) {
				resArtI[i] = (String) o[i];
			}
			request.setAttribute("artistas", resArtI);
		} catch (UsuarioNoExisteException_Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	try {
			String[] espec = u.getEspectaculos().toArray(new String[0]);
			for (String esp : espec) {
				if (port.seleccionarEspectaculo(esp).getEstado().equals("Aceptado"))
					especAcetp.add(esp);
			}
			Object[] o = especAcetp.toArray();
			String[] resEA = new String[o.length];
			for (int i = 0; i < o.length; i++) {
				resEA[i] = (String) o[i];
			}
			request.setAttribute("espectaculos", resEA);
			
		} catch (EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/altaFuncionEspectaculo.jsp");
		dispatcher.forward(request, response);
    	
    	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
    	
    	
		String nombref = request.getParameter("nombref");
		String espec = request.getParameter("espec");
		String[] arti = request.getParameterValues("artistasinv");
		List<String> artis = new ArrayList<String>();
		if (arti != null)
			for(String art: arti)
				artis.add(art);
		else 
			artis = null;
		
		String urlport = request.getParameter("urlport");    	
		String[] tiempo = request.getParameterValues("tiempo");
		String fechai = request.getParameter("fechai");
		String fechaPosta = fechai +" " + tiempo[0] + ":00";		
		
		SimpleDateFormat Format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date fechaA= new Date();
			Date fechaR= Format2.parse(fechaPosta);
    		GregorianCalendar fechaux = new GregorianCalendar();
    		fechaux.setTime(fechaR);
    		XMLGregorianCalendar fechaFR;
    		GregorianCalendar fechaux2 = new GregorianCalendar();
    		fechaux2.setTime(fechaA);
    		XMLGregorianCalendar fechaFA;    		
    		fechaFR = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaux);
    		fechaFA = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaux2);
    		StringArray listaArt = new StringArray();
    		if (artis != null) {
    		  listaArt.getItem().addAll(artis);
    		}
    		
			port.ingresarDatosFuncion(nombref, fechaFR, listaArt, fechaFA, espec, urlport);
		} catch (ParseException | EspectaculoNoExisteException_Exception | FuncionRepetidaException_Exception | DatatypeConfigurationException e) { 
			request.setAttribute("error", e.getMessage());
	        doGet(request, response);
		}
	
	    ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}
	


}
