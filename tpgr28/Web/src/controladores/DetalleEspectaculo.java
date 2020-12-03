package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DataArtista;
import servidor.DataEspectaculo;
import servidor.DataEspectador;
import servidor.DataPuntaje;
import servidor.DataUsuario;
import servidor.EspectaculoNoExisteException_Exception;
import servidor.EstadoEspectaculoException_Exception;
import servidor.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class DetalleEspectaculo
 */
@WebServlet(description = "Pagina con Detalles de Espectaculo", urlPatterns = "/DetalleEspectaculo")
public class DetalleEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleEspectaculo() {
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
		 * ICU.cargarDatosPrueba(); //Obtener espectaculo IEspectaculoController ICE =
		 * Fabrica.getInstancia().getIEspectaculoController();
		 */
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		
	    //Si se hace click en boton favorito y usuario es espectador
	    String esp = request.getParameter("favorito");
	    if (esp != null) {
	    	DataUsuario u = (DataUsuario)request.getSession().getAttribute("usuario");
	    	if (u instanceof DataEspectador) {
				try {
		    		if (((DataEspectador)u).getEspectaculosFavoritos().contains(esp))
						port.desmarcarEspectaculoFavorito(u.getNickname(), esp);
					else
		    			port.marcarEspectaculoFavorito(u.getNickname(), esp);
		    		//Actualizar el DataUsuario de la sesion
		    		request.getSession().setAttribute("usuario", port.mostrarDatosUsuario(u.getNickname()));
				} catch (EspectaculoNoExisteException_Exception | UsuarioNoExisteException_Exception e) {
					e.printStackTrace();
				}
	    	}
	    }
	       	
	    
	    try {
	    	DataEspectaculo espectaculo = null;
			String plataforma = null;
	    	if (request.getParameter("finalizado") == null) {
				espectaculo = port.seleccionarEspectaculo((String)request.getParameter("espectaculo"));
				request.setAttribute("espectaculoData", espectaculo);
				//Obtener Plataforma
				boolean terminado = false;
				String[] plataformas = new String[port.getPlataformas().getItem().size()];
				port.getPlataformas().getItem().toArray(plataformas);
				for(int i=0; i<plataformas.length && !terminado; i++) {
					String[] espectaculos = new String[port.listarEspectaculos(plataformas[i]).getItem().size()];
					port.listarEspectaculos(plataformas[i]).getItem().toArray(espectaculos);
					for(int j=0; j<espectaculos.length && !terminado; j++) {
						if(espectaculos[j].equals(espectaculo.getNombre())) {
							plataforma = plataformas[i];
							terminado = true;
							
						}
					}
				}
	    	} else {
	    		List<DataEspectaculo> esps = port.listarEspectaculosFinalizado(((DataUsuario)request.getSession().getAttribute("usuario")).getNickname()).getItem();
	    		for (DataEspectaculo e: esps) {
	    			if (e.getNombre().equals((String)request.getParameter("espectaculo"))) {
	    				espectaculo = e;
	    			}
	    		}
				//Llamar al JSP de finalizados
				request.setAttribute("espectaculoData", espectaculo);
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher =
				context.getRequestDispatcher("/vistasDetalle/DetalleEspectaculoFinalizado.jsp");
				dispatcher.forward(request, response);
	    	}
		    //PARA SABER QUIEN ES EL ARTISTA QUE CREA EL ESPECTACULO
		    DataUsuario usr = (DataUsuario)request.getSession().getAttribute("usuario");
		    if (usr instanceof DataArtista) {
		    	List<String> lstEspecs = ((DataArtista) usr).getEspectaculos();
		    	if (lstEspecs.contains(espectaculo.getNombre())) {
		    		request.setAttribute("creaEspectaculo", true);
		    	} else {
		    		request.setAttribute("creaEspectaculo", false);
		    	}
		    } else {
		    	request.setAttribute("creaEspectaculo", false);
		    }
			
			request.setAttribute("plataforma", plataforma);
			//Trear valoraciones del espectaculo
			List<Integer> valoracionesList = port.valoracionEspectaculo(espectaculo.getNombre()).getItem();
			int[] valoraciones = new int[valoracionesList.size()];
			for(int i = 0; i < valoracionesList.size(); i++) {
				valoraciones[i] = valoracionesList.get(i);
			}
			request.setAttribute("valoraciones", valoraciones);
			double totalVal = valoraciones[4]+valoraciones[3]+valoraciones[2]+valoraciones[1]+valoraciones[0];
			request.setAttribute("totalVal", totalVal);
			double promedioVal = 0;
			if(totalVal>0) {
				promedioVal = (valoraciones[0]+valoraciones[1]*2+valoraciones[2]*3
					+valoraciones[3]*4+valoraciones[4]*5)/totalVal;
			}
			request.setAttribute("promedioVal", promedioVal);
			DataUsuario usuario = (DataUsuario)request.getSession().getAttribute("usuario");
			if(usuario instanceof DataEspectador) {
				DataPuntaje valoracionEspectador = port.existePuntaje(usuario.getNickname(), espectaculo.getNombre());
				request.setAttribute("valoracionEspectador", valoracionEspectador);
			}
			if (usuario != null) {
				boolean habilitadoVal = port.habilitadoVal(usuario.getNickname(), espectaculo.getNombre());
				request.setAttribute("habilitadoVal", habilitadoVal);
			} else {
				request.setAttribute("habilitadoVal", false);
			}
		} catch (EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("espectaculoData", null);
			request.setAttribute("plataforma", null);
		}
		//Llamar al JSP
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/vistasDetalle/DetalleEspectaculo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();

	    DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario");
	    
	    if(user instanceof DataArtista) {
		    		
	    	String usuario = ((servidor.DataArtista)request.getSession().getAttribute("usuario")).getNickname();
	    	String espectaculo = (String)request.getParameter("nomEspectaculo");
	    	
	    	try {
				port.finalizarEspectaculo(usuario, espectaculo);
			} catch (EstadoEspectaculoException_Exception e) {
				request.setAttribute("errorFinalizar", e.getMessage());
			}    	

	    }
	    
	    if(user instanceof DataEspectador) {
	    	int puntaje = Integer.parseInt(request.getParameter("puntaje"));
	    	port.agregarPuntaje(puntaje, (String)request.getParameter("espectaculo"), user.getNickname());
	    }
    	
		doGet(request, response);
	}
}
