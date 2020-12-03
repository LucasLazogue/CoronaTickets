package controladores;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.EspectaculoNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;
import servidor.DataEspectaculo;
import servidor.DataEspectador;
import servidor.DataUsuario;

@WebServlet(description = "Página con lista de espectáculos", urlPatterns = { "/home" })
public class PaginaPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaPrincipal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador port = service.getPublicadorPort();
	
	    //Cargar datos de prueba
	    port.cargarDatosPrueba();
	    
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
    	
    	//Agarrar nombres de los especáculos de la plataforma seleccionada
		String[] nomEspectaculos;
		if (request.getParameter("plataforma") == null) {
		  nomEspectaculos = new String[port.listarEspectaculos("Twitter Live").getItem().size()];
			port.listarEspectaculos("Twitter Live").getItem().toArray(nomEspectaculos);

		} else {
      nomEspectaculos = new String[port.listarEspectaculos((String)request.getParameter("plataforma")).getItem().size()];
			port.listarEspectaculos((String)request.getParameter("plataforma")).getItem().toArray(nomEspectaculos);
		}
	    //Si se había seleccionado categoría, agarrar espectáculos de la categoría seleccionada
	    if (request.getParameter("categoria") != null) {
	      nomEspectaculos = new String[port.listarEspectaculosCategoria((String)request.getParameter("categoria")).getItem().size()];
	    	port.listarEspectaculosCategoria((String)request.getParameter("categoria")).getItem().toArray(nomEspectaculos); }
	    //Convertir los nombres de los espectaculos en datatypes para tener la imagen
	    DataEspectaculo[] espectaculos = null;
	    if (nomEspectaculos != null) {
		    espectaculos = new DataEspectaculo[nomEspectaculos.length];
		    for (int i = 0; i < nomEspectaculos.length; i++) {
		    	try {
					espectaculos[i] = port.seleccionarEspectaculo(nomEspectaculos[i]);
				} catch (EspectaculoNoExisteException_Exception e) {
					e.printStackTrace();
				}
		    }
	    }
	    //Llamar a JSP con la información correcta
        request.setAttribute("espectaculos", espectaculos);
        String[] plat = new String[port.getPlataformas().getItem().size()];
        port.getPlataformas().getItem().toArray(plat);
        String[] cat = new String[port.listarCategorias().getItem().size()];
        port.listarCategorias().getItem().toArray(cat);
        request.setAttribute("plataformas", plat);
        request.setAttribute("categorias", cat);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/vistasLista/ListaEspectaculos.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
