package controladores;

import java.io.IOException;

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
import servidor.EspectaculoRepetidoException_Exception;
import servidor.MaxEspMenorMinEspException_Exception;
import servidor.UsuarioNoExisteException_Exception;

import java.util.Date;
import java.util.GregorianCalendar;


@WebServlet(description = "Crear Espectaculo", urlPatterns = { "/altaEspectaculo" })
public class AltaEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaEspectaculo() {
        super();
  
    }
    
    

    	
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();		
    	port.cargarDatosPrueba();    	     	
    	request.setAttribute("plataformas", port.getPlataformas());
    	request.setAttribute("categorias", port.listarCategorias());    	
    	
    	

        
        
       
    	
    	
    	ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/altaEspectaculo.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	    servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		try {
	    	String nombre = request.getParameter("nombre");
	        int  duracion = Integer.parseInt(request.getParameter("duracion"));
	        
	        int costo = Integer.parseInt(request.getParameter("costo"));
	        
	        String urlesp = request.getParameter("urlesp");
	        int cantmiesp = Integer.parseInt(request.getParameter("cantmiesp"));
	        int cantmaesp = Integer.parseInt(request.getParameter("cantmaesp"));        
	        String urlport = request.getParameter("urlport");
	        String desp = request.getParameter("descripcion"); 
	        
	        String cantpremios = request.getParameter("cantpremio");
	        int cantpremio;
	        if (!cantpremios.equals(""))
	        	cantpremio = Integer.parseInt(cantpremios);
	        else
	        	cantpremio= 0;
    		String premio = (String)request.getParameter("premio");    		
    		String urlvideo = (String)request.getParameter("urlvideo");
	
	        
	        String plat = request.getParameter("plataforma");
	        String[] cat = request.getParameterValues("categoria"); 
	        net.java.dev.jaxb.array.StringArray cat2 = new net.java.dev.jaxb.array.StringArray();
	        for(int i= 0; i < cat.length; i++) {
	        	cat2.getItem().add(cat[i]);	        
	        }
	        
	         
	        
	        
	        
	    	if (request.getSession().getAttribute("usuario") != null  && request.getSession().getAttribute("usuario") instanceof DataArtista) {
	    		DataArtista art = (DataArtista)request.getSession().getAttribute("usuario");
	    		String nickname = art.getNickname();    	 
	    		Date fechaR= new Date();
	    		GregorianCalendar fechaux = new GregorianCalendar();
	    		fechaux.setTime(fechaR);
	    		XMLGregorianCalendar fechaF;
	    		
				try {
					fechaF = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaux);
					port.crearEspectaculo(plat, nickname, nombre, desp, duracion, cantmiesp, cantmaesp, urlesp, costo, fechaF, cat2, urlport, premio, cantpremio, urlvideo);
			        request.getSession().setAttribute("usuario", port.mostrarDatosUsuario(nickname));
					ServletContext context = getServletContext();
					RequestDispatcher dispatcher =
					context.getRequestDispatcher("/home");
					dispatcher.forward(request, response);
				} catch ( DatatypeConfigurationException | EspectaculoRepetidoException_Exception | MaxEspMenorMinEspException_Exception | UsuarioNoExisteException_Exception e) {
					request.setAttribute("plataformas", port.getPlataformas());
			    	request.setAttribute("categorias", port.listarCategorias());  
					request.setAttribute("error", e.getMessage());
			        ServletContext context = getServletContext();
					RequestDispatcher dispatcher =
					context.getRequestDispatcher("/altaEspectaculo.jsp");
					dispatcher.forward(request, response);
				}

	    	}        
	
		} catch (NumberFormatException e) {			
			request.setAttribute("plataformas", port.getPlataformas());
	    	request.setAttribute("categorias", port.listarCategorias());  
			request.setAttribute("error", "Se ha ingresado una letra donde va un número.");
	        ServletContext context = getServletContext();
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/altaEspectaculo.jsp");
			dispatcher.forward(request, response);
		}
	}
}