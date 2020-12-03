package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CargarAlHistorial")
public class CargarAlHistorial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CargarAlHistorial() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		servidor.PublicadorService service = new servidor.PublicadorService();
	    servidor.Publicador port = service.getPublicadorPort();
		
		String ip = (String) request.getParameter("ip");
		String url = (String) request.getParameter("url");
		String browser = (String) request.getParameter("browser");
		String os = (String) request.getParameter("os");
		
		port.agregarDatoHistorial(ip, url, browser, os);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
