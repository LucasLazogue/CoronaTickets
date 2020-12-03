package controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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


import servidor.ErrorCantidadCanjeExcepcion_Exception;
import servidor.EspectaculoNoExisteException_Exception;
import servidor.EspectadorRegistradoEnFuncionExepcion_Exception;
import servidor.FechaPasadaException_Exception;
import servidor.FuncionLlenaExepcion_Exception;
import servidor.NoEsEspectadorExepcion_Exception;
import servidor.RegistroNoSePuedeCanjearException_Exception;


@WebServlet(description = "Registro a Funcion", urlPatterns = { "/RegistroFuncion" })
public class RegistroFuncion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegistroFuncion() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		
		/*IEspectaculoController ICE = Fabrica.getInstancia().getIEspectaculoController();
			IUsuarioController ICU = Fabrica.getInstancia().getIUsuarioController();*/
			
			//Cargar datos espectador en pagina
			
	    	String espectaculo = (String)request.getParameter("espectaculo");
	    	String funcion = (String)request.getParameter("funcion");
	    	String urlImg = "";
	    	String costo = "";
	    	try {
				urlImg = port.seleccionarEspectaculo(espectaculo).getUrlImg();
		    	costo = String.valueOf(port.seleccionarEspectaculo(espectaculo).getCosto());
			} catch (EspectaculoNoExisteException_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//cargar registros que no tienen costo 0
	    	
	    	//DataRegistro[] registros = new DataRegistro[port.listarFuncionesEspectador((String)((servidor.DataEspectador)request.getSession().getAttribute("usuario")).getNickname()).getItem().size()];
			//port.listarFuncionesEspectador((String)((servidor.DataEspectador)request.getSession().getAttribute("usuario")).getNickname()).getItem().toArray(registros);  		    	
			//DataRegistro[] registros = port.listarFuncionesEspectador((String)((DataEspectador)request.getSession().getAttribute("usuario")).getNickname());
	    	List<servidor.DataRegistro> registros = port.listarFuncionesEspectador((String)((servidor.DataEspectador)request.getSession().getAttribute("usuario")).getNickname()).getItem();  		    	

	    	
	    	List<String> listaRegistros = new ArrayList<String>();
			
			for (servidor.DataRegistro registro : registros) {
				if(registro.getCosto() != 0)
					listaRegistros.add(registro.getFuncion());
			}
			
			//Cargar paquetes que contienen el espectaculo
	    	List<String> tempPaqs = ((servidor.DataEspectador)request.getSession().getAttribute("usuario")).getPaquetes();
			List<String> listaPaquetes = new ArrayList<String>();
			List<Integer> listaDescuentos = new ArrayList<Integer>();
	
			//agregar elemento nulo al inicio
			listaPaquetes.add("-");
			
	    	String plat = "";
			String[] plats = new String[port.getPlataformas().getItem().size()];
			port.getPlataformas().getItem().toArray(plats);
	    	//String[] plats = port.getPlataformas();
	    	
	    	if (plats != null) {
		    	for(String p: plats) {
					String[] esps = new String[port.listarEspectaculos(p).getItem().size()];
					port.listarEspectaculos(p).getItem().toArray(esps);
		    		//String[] esps = port.listarEspectaculos(p);
		    		
		    		if(Arrays.asList(esps).contains(espectaculo)) {
		    			plat = p;
		    			break;
		    		}
		    			
		    	}
	    	}
	    		    	
	    	String esp = (String)request.getParameter("espectaculo");
	    	System.out.println(esp);
	    	for(String tp: tempPaqs) {
				String[] esps = new String[port.listarEspectaculosEnPaquete(plat, tp).getItem().size()];
				port.listarEspectaculosEnPaquete(plat, tp).getItem().toArray(esps);
	        	//String[] esps = port.listarEspectaculosEnPaquete(plat, tempPaqs[i]);
	        	
	    		if(Arrays.asList(esps).contains(esp)) {
	    			listaPaquetes.add(tp);
	    			servidor.DataPaquete p = port.seleccionarPaquete(tp);
	    			listaDescuentos.add(p.getDescuento());
	    		}
	    	}
	    	
	    	/*for(int i = 0; i < tempPaqs.length; i++) {
				String[] esps = new String[port.listarEspectaculosEnPaquete(plat, tempPaqs[i]).getItem().size()];
				port.listarEspectaculosEnPaquete(plat, tempPaqs[i]).getItem().toArray(esps);
	        	//String[] esps = port.listarEspectaculosEnPaquete(plat, tempPaqs[i]);
	        	
	    		if(Arrays.asList(esps).contains(esp)) {
	    			listaPaquetes.add(tempPaqs[i]);
	    			servidor.DataPaquete p = port.seleccionarPaquete(tempPaqs[i]);
	    			listaDescuentos.add(p.getDescuento());
	    		}
	    	}*/    	
			
	        request.setAttribute("listaDescuentos", listaDescuentos);
	        request.setAttribute("listaRegistros", listaRegistros);
	        request.setAttribute("listaPaquetes", listaPaquetes);
	        request.setAttribute("urlImg", urlImg);
	        request.setAttribute("costo", costo);
	        request.setAttribute("espectaculo", espectaculo);
	        request.setAttribute("funcion", funcion);
	        request.setAttribute("plataforma", plat);

			ServletContext context = getServletContext();
			RequestDispatcher dispatcher =
			context.getRequestDispatcher("/RegistroAFuncion.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		
		/*IEspectaculoController IEC = Fabrica.getInstancia().getIEspectaculoController();
    	IUsuarioController ICU = Fabrica.getInstancia().getIUsuarioController();*/
    	
    	String espectaculo = (String)request.getParameter("espectaculo");
    	String funcion = (String)request.getParameter("funcion");
    	String plataforma = (String)request.getParameter("plataforma");
    	
    	String paquete = request.getParameter("paquetes");
    	String[] canjeados = request.getParameterValues("registros");
    	String[] fechaReg = request.getParameterValues("fechaReg");
    	String[] horaReg = request.getParameterValues("horaReg");
    	String usuario = ((servidor.DataEspectador)request.getSession().getAttribute("usuario")).getNickname();
		String s = fechaReg[0] + " " + horaReg[0] + ":00";
		Date auxFecha = null;
		XMLGregorianCalendar fecha = null;
		
		List<String> auxCanje = null;
		net.java.dev.jaxb.array.StringArray canje =  new net.java.dev.jaxb.array.StringArray();
		
		if(canjeados == null)
			auxCanje = new ArrayList<String>();
		else
			auxCanje = Arrays.asList(canjeados);
		
		canje.getItem().addAll(auxCanje);
		

		try {
			auxFecha = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").parse(s);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(auxFecha);
			fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (ParseException | DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(paquete.equals("-"))
			paquete = "";
    
		
		try {
			port.confirmarRegistroAFuncion(plataforma, paquete, espectaculo, funcion, usuario, canje, fecha);
			
			ServletContext context = getServletContext();
	        RequestDispatcher dispatcher =
	        context.getRequestDispatcher("/home");
	        dispatcher.forward(request, response);
		} catch (ErrorCantidadCanjeExcepcion_Exception | EspectadorRegistradoEnFuncionExepcion_Exception
				| FechaPasadaException_Exception | FuncionLlenaExepcion_Exception | NoEsEspectadorExepcion_Exception
				| RegistroNoSePuedeCanjearException_Exception e) {	 
			request.setAttribute("error", e.getMessage());
			doGet(request,response);
		}
	}

}
