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

import servidor.DataEspectaculo;
import servidor.DataEspectador;
import servidor.DataUsuario;
import servidor.EspectaculoNoExisteException_Exception;

@WebServlet(description = "Pagina con Detalles de Espectaculo", urlPatterns = "/DetalleEspectaculo")
public class DetalleEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetalleEspectaculo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		try {
			DataEspectaculo espectaculo = port.seleccionarEspectaculo((String)request.getParameter("espectaculo"));
			request.setAttribute("espectaculoData", espectaculo);
			//Obtener Plataforma
			String plataforma = null;
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
			request.setAttribute("plataforma", plataforma);
			//Obtener promedio
			List<Integer> valoracionesList = port.valoracionEspectaculo(espectaculo.getNombre()).getItem();
			int[] valoraciones = new int[valoracionesList.size()];
			for(int i = 0; i < valoracionesList.size(); i++) {
				valoraciones[i] = valoracionesList.get(i);
			}
			double totalVal = valoraciones[4]+valoraciones[3]+valoraciones[2]+valoraciones[1]+valoraciones[0];
			request.setAttribute("totalVal", totalVal);
			double promedioVal = 0;
			if(totalVal>0) {
				promedioVal = (valoraciones[0]+valoraciones[1]*2+valoraciones[2]*3
					+valoraciones[3]*4+valoraciones[4]*5)/totalVal;
			}
			request.setAttribute("promedio", promedioVal);
		} catch (EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("espectaculoData", null);
			request.setAttribute("plataforma", null);
		}
		//Llamar al JSP si el usuario es espectador, sino ir al login
		ServletContext context = getServletContext();
		if ((DataUsuario)request.getSession().getAttribute("usuario") instanceof DataEspectador) {
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/vistasDetalle/DetalleEspectaculo.jsp");
			dispatcher.forward(request, response);
	    } else {
	    	RequestDispatcher dispatcher =
	    	context.getRequestDispatcher("/IniciarSesion");
	    	dispatcher.forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
