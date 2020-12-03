package controladores;

import java.io.IOException;
import java.util.ArrayList;
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
import servidor.DataEspectaculoArray;
import servidor.DataEspectador;
import servidor.DataFuncion;
import servidor.DataPaquete;
import servidor.DataUsuario;
import servidor.EspectaculoNoExisteException_Exception;
import servidor.FuncionNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;
import servidor.DataPremio;
import servidor.DataPremioArray;


/**
 * Servlet implementation class Perfil
 */
@WebServlet(description = "Perfil", urlPatterns = { "/Perfil" })
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Perfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    port.cargarDatosPrueba();
		if(request.getSession().getAttribute("usuario") != null) {
			DataUsuario usuario = (DataUsuario)request.getSession().getAttribute("usuario");
			
			try {
				DataUsuario u = port.mostrarDatosUsuario(usuario.getNickname());
				if(u instanceof DataArtista) {
					DataArtista da = (DataArtista)u;
					List<String> nomEspecs = da.getEspectaculos();
					//Se este artista tiene espectaculos aceptados se crea el array de DataEspectaculos
					try {
						if (nomEspecs != null && nomEspecs.size() > 0) {
							//DataEspectaculo[] des =  new DataEspectaculo[nomEspecs.size()];
							List<DataEspectaculo> des = new ArrayList<DataEspectaculo>();
							for (int i = 0; i < nomEspecs.size(); i++) {
								//System.out.print(nomEspecs[i]);
								DataEspectaculo de = port.seleccionarEspectaculoUsuario(da.getNickname(), nomEspecs.get(i));
								if (!de.getEstado().equals("Finalizado")) {
								  des.add(de);
								} 
							}
							//System.out.println(nomEspecs);
							//System.out.println(des);
							if (des.size() > 0) {
								request.setAttribute("espectaculostotales", des);
							} else {
								request.setAttribute("espectaculostotales", null);
							}
						}
					} catch (EspectaculoNoExisteException_Exception e) {
						// TODO Auto-generated catch block
						
						//System.out.println(des.length);
						e.printStackTrace();
						request.setAttribute("espectaculostotales", null);
					}
					//String [] nomEspecsFin = port.listarEspectaculosFinalizado(da.getNickname()).getItem().toArray(new String[0]);
					DataEspectaculoArray especsFins = port.listarEspectaculosFinalizado(da.getNickname());
					if(especsFins != null && especsFins.getItem().size()>0) {
						request.setAttribute("espectaculosfinalizados", especsFins);
					} else {
						request.setAttribute("espectaculosfinalizados", null);
					}
				}else {
					DataEspectador de = (DataEspectador)u;
					List<String> nomFuncs = de.getFunciones();
					List<String> nomPaqs = de.getPaquetes();
					//Se este espectador tiene funciones registradas se crea el array de DataFuncion
					try {
						if (nomFuncs != null && nomFuncs.size() > 0) {
							DataFuncion[] dfs =  new DataFuncion[nomFuncs.size()];
							for (int i = 0; i < nomFuncs.size(); i++) {
								dfs[i] = port.seleccionarFuncion(de.getNickname(), nomFuncs.get(i));
							}
						
					        request.setAttribute("funcionesregistradasperfil", dfs);
						}	
					}  catch (FuncionNoExisteException_Exception e) {
						e.printStackTrace();
						request.setAttribute("funcionesregistradasperfil", null);
					}
					if (nomPaqs != null && nomPaqs.size() > 0) {
						DataPaquete[] dps = new DataPaquete[nomPaqs.size()];
						for (int i = 0; i < nomPaqs.size(); i++) {
							dps[i] = port.seleccionarPaquete(nomPaqs.get(i));
						}
					
						request.setAttribute("paquetescompradosperfil", dps);
					
					}
					 List<DataPremio> ldp = port.mostrarPremios(de.getNickname()).getItem();
					 request.setAttribute("premiosespectador", ldp);
				}
			} catch (UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher =
		context.getRequestDispatcher("/perfil.jsp");
		dispatcher.forward(request, response);
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
