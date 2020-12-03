package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataPlataforma;

/**
 * Servlet implementation class AgregarEspectaculoPaquete
 */
@WebServlet(description = "Agregar espectaculo a paquete", urlPatterns = { "/AgregarEspectaculoPaquete" })
public class AgregarEspectaculoPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarEspectaculoPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("plataformaAgregar") == null) {
			
			servidor.PublicadorService service = new servidor.PublicadorService();
		    servidor.Publicador port = service.getPublicadorPort();
			//IEspectaculoController IEC = Fabrica.getInstancia().getIEspectaculoController();
			
			String paquete = (String)request.getParameter("paquete");
			
			//Cargo los datos del request
			String nomPaquete = (String)request.getParameter("paquete");
			
			//Cargo las plataformas
			String[] listaPlataformas = new String[port.getPlataformas().getItem().size()];
			port.getPlataformas().getItem().toArray(listaPlataformas);
			//String[] listaPlataformas = port.getPlataformas();
			
			//Cargar espectaculos no en paquete
			Map<String, List<String>> platEspecs = new HashMap<>();
			

			for(String plataforma: listaPlataformas) {
				DataPlataforma dPlat = port.seleccionarPlataforma(plataforma);
				if(!dPlat.getEspectaculos().isEmpty()) {
					String[] especs = new String[port.listarEspectaculosNoEnPaquete(plataforma, paquete).getItem().size()];
					port.listarEspectaculosNoEnPaquete(plataforma, paquete).getItem().toArray(especs);
					//String[] especs = port.listarEspectaculosNoEnPaquete(plataforma, paquete);
					
					List<String> lstEspec = new ArrayList<>();
					if(especs != null) {
						for(String espec: especs) {
							lstEspec.add(espec);
						}
					}
					platEspecs.put(plataforma, lstEspec);
				}
			}			
			
	        request.setAttribute("listaPlataformas", listaPlataformas);
	        request.setAttribute("platEspecs", platEspecs);
	        request.setAttribute("paquete", paquete);
	        
	
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/AgregarEspectaculoAPaquete.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		//IEspectaculoController IEC = Fabrica.getInstancia().getIEspectaculoController();

    	String plat = request.getParameter("plataformaAgregar");
    	String espec = request.getParameter("espectaculoAgregar");
    	String paq = request.getParameter("paquete");
    	
    	
    	port.agregarEspectaculoAPaquete(plat, espec, paq);
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher =
        context.getRequestDispatcher("/home");
        dispatcher.forward(request, response);
	}

}
