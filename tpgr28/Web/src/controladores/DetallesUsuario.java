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
import servidor.DataFuncion;
import servidor.DataUsuario;
import servidor.EspectaculoNoExisteException_Exception;
import servidor.FuncionNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;
//import logica.Fabrica;
//import logica.IUsuarioController;

/**
 * Servlet implementation class DetallesUsuario
 */
@WebServlet(description = "Detalles de usuario", urlPatterns = { "/DetallesUsuario" })
public class DetallesUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallesUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//IUsuarioController IUC = Fabrica.getInstancia().getIUsuarioController();
		//IUC.cargarDatosPrueba();
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    port.cargarDatosPrueba();
		try {
			DataUsuario u = port.mostrarDatosUsuarioGeneral(request.getParameter("usuarioseleccionado"));
			//usuarios seleccionado, se supone que siempre existe
			request.setAttribute("detalleusuario", u);
			//System.out.print(u.getNickname());
			//System.out.print(u instanceof DataArtista);
			if(u instanceof DataArtista) {
				DataArtista da = (DataArtista)u;
				//String[] nomEspecs = da.getEspectaculos();
				List<String> nomEspecs = da.getEspectaculos();
				//System.out.print(nomEspecs.length);
				//Se este artista tiene espectaculos aceptados se crea el array de DataEspectaculos
				try {
					if (nomEspecs != null) {
						DataEspectaculo[] des =  new DataEspectaculo[nomEspecs.size()];
						for (int i = 0; i < nomEspecs.size(); i++) {
							//System.out.print(nomEspecs[i]);
							des[i] = port.seleccionarEspectaculoUsuario(da.getNickname(), nomEspecs.get(i));
						}
				        request.setAttribute("espectaculosaceptados", des);
					}
				} catch (EspectaculoNoExisteException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("espectaculosaceptados", null);
				}
				
			}
			else {
				DataEspectador de = (DataEspectador)u;
				//String[] nomFuncs = de.getFunciones();
				List<String> nomFuncs = de.getFunciones();
				DataFuncion[] dfs =  new DataFuncion[nomFuncs.size()];
				//Se este espectador tiene funciones registradas se crea el array de DataFuncion
				try {
					if (nomFuncs != null) {
						for (int i = 0; i < nomFuncs.size(); i++) {
							dfs[i] = port.seleccionarFuncion(de.getNickname(), nomFuncs.get(i));
						}
				        request.setAttribute("funcionesregistradas", dfs);
					}	
				}  catch (FuncionNoExisteException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("funcionesregistradas", null);
				}	
			}
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/vistasDetalle/DetallesDeUsuario.jsp");
			dispatcher.forward(request, response);
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
