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

import servidor.DataArtista;
import servidor.DataFuncion;
import servidor.DataPlataforma;
import servidor.DataUsuario;
import servidor.EspectaculoNoExisteException_Exception;
import servidor.FechaPasadaException_Exception;
import servidor.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class DetalleFuncion
 */
@WebServlet(description = "Pagina con Detalles de Funcion", urlPatterns = "/DetalleFuncion")
public class DetalleFuncion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleFuncion() {
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
		 * ICU.cargarDatosPrueba(); //Obtener funcion IEspectaculoController ICE =
		 * Fabrica.getInstancia().getIEspectaculoController();
		 */
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		try {
			DataFuncion funcion = port.consultaFuncion((String)request.getParameter("funcion"), (String)request.getParameter("espectaculo"));
			request.setAttribute("funcionData", funcion);
			String[] espectadores = port.getEspectadoresRegistrados((String)request.getParameter("funcion"), (String)request.getParameter("espectaculo")).getItem().toArray(new String[0]);
			request.setAttribute("espectadoresRegistrados", espectadores);
			String[] espectadoresGanadores = port.getEspectadoresGanadores((String)request.getParameter("funcion"), (String)request.getParameter("espectaculo")).getItem().toArray(new String[0]);
			request.setAttribute("espectadoresGanadores", espectadoresGanadores);
			//habilitar sortear
			DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario");
			boolean habilitarSortear = false;
			boolean noEsAceptado = false;
			if(user instanceof DataArtista) {
				DataArtista art = (DataArtista)user;
				String[] espectaculosArt = art.getEspectaculos().toArray(new String[0]);
				String espectaculo = funcion.getEspectaculo();
				for(int i = 0; i < espectaculosArt.length && !habilitarSortear; i++) {
					if(espectaculo.equals(espectaculosArt[i])) {
						habilitarSortear = true;
						if (port.seleccionarEspectaculo(espectaculo).getEstado().equals("Aceptado")) {
							noEsAceptado = true;
						}
					}
				}
			}
			request.setAttribute("habilitarSortear", habilitarSortear);
			request.setAttribute("noEsAceptado", noEsAceptado);
		} catch (EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("funcionData", null);
			request.setAttribute("espectadoresRegistrados", null);
		}
		//Llamar al JSP
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/vistasDetalle/DetalleFuncion.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    
	    String nick = ((DataUsuario)request.getSession().getAttribute("usuario")).getNickname();
	    String funcion = request.getParameter("funcion");
	    String espectaculo = request.getParameter("espectaculo");
	    List<String> plataformas = port.getPlataformas().getItem();
	    String plataformaNombre = null;
	    boolean terminado = false;
	    for(int i = 0; i < plataformas.size() && !terminado; i++) {
	    	DataPlataforma plat = port.seleccionarPlataforma(plataformas.get(i));
	    	List<String> espectaculos = plat.getEspectaculos();
	    	boolean terminadoInterno = false;
	    	for(int j = 0; j < espectaculos.size() && !terminadoInterno; j++) {
	    		if(espectaculos.get(j).equals(espectaculo)) {
	    			plataformaNombre = plat.getNombre();
	    			terminado = true;
	    			terminadoInterno = true;
	    		}
	    	}
	    }
	    Date fecha = new Date();
		GregorianCalendar fechaAux = new GregorianCalendar();
		fechaAux.setTime(fecha);
		XMLGregorianCalendar fechaXML;
		
		try {
			fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaAux);
		
			try {
				port.sortearPremios(plataformaNombre, espectaculo, funcion, fechaXML);
				request.getSession().setAttribute("usuario", port.mostrarDatosUsuario(nick));
			} catch (FechaPasadaException_Exception | UsuarioNoExisteException_Exception e){
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
