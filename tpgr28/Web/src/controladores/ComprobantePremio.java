package controladores;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import servidor.DataEspectador;
import servidor.DataPremio;


@WebServlet("/ComprobantePremio")
public class ComprobantePremio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ComprobantePremio() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
	    
	    
	    
	    String premio = (String) request.getParameter("premio");
	    DataEspectador espec = (servidor.DataEspectador) request.getSession().getAttribute("usuario");
	    
		DataPremio[] lstPremios = new DataPremio[port.mostrarPremios(espec.getNickname()).getItem().size()];
		port.mostrarPremios(espec.getNickname()).getItem().toArray(lstPremios);
		
		DataPremio dataPrem = null;

	    for(DataPremio p : lstPremios) {
	    	if (p.getFuncion().equals(premio)) {
	    		dataPrem = p;
	    		break;
	    	}
	    }
	    
	    String descPremio = dataPrem.getDescripcion();
	    XMLGregorianCalendar fechaDesde = dataPrem.getFecha();
	    String nomEspec = dataPrem.getEspectaculo();
	    String nomFunc = dataPrem.getFuncion();
	    String ganador = espec.getNickname();
	    
	    
        
	    request.setAttribute("descPremio", descPremio);
        request.setAttribute("fechaDesde", fechaDesde);
        request.setAttribute("nomEspec", nomEspec);
        request.setAttribute("nomFunc", nomFunc);
        request.setAttribute("ganador", ganador);

        ServletContext context = getServletContext();
		RequestDispatcher dispatcher = 
		context.getRequestDispatcher("/ComprobantePremio.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
