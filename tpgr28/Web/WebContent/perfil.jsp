<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Perfil Espectador</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfilEspectador.css">
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="servidor.DataEspectaculo" %>
		<%@ page import="servidor.DataEspectaculoArray" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="servidor.DataFuncion" %>
		<%@ page import="servidor.DataPaquete" %>
		<%@ page import="servidor.DataPremio" %>	
		<%@ page import="java.util.List" %>	
		<%@ page import="java.util.ArrayList" %>	
	</head>
<body class="bg-image">
	<jsp:include page="./Menu.jsp" />
	<jsp:include page="./cargarHistorial.jsp" />   
	<% DataUsuario u = (DataUsuario)request.getSession().getAttribute("usuario");%>
	<div ><input class="btnConfirmar" type="submit" onclick="window.location.href='${pageContext.request.contextPath}/ModificarPerfil'" value="Modificar Perfil" style="top:10%; left:10%;"><br></div>
	<%if (u instanceof DataEspectador) {%>
		<div ><input type="submit" class="btnConfirmar" onclick="window.location.href='${pageContext.request.contextPath}/ListaPaquetes'" value="Comprar paquete" style="top:10%; left:31%;"><br></div>
		<div ><input type="submit" class="btnConfirmar" onclick="window.location.href='${pageContext.request.contextPath}/home'" value="Registrar a funcion" style="top:10%; left:52%;"><br></div>
	<% } else {%>
		<div ><input type="submit" class="btnConfirmar" onclick="window.location.href='${pageContext.request.contextPath}/altaEspectaculo'" value="Alta de Espectaculo" style="top:10%; left:31%;"><br></div>	
		<div ><input type="submit" class="btnConfirmar" onclick="window.location.href='${pageContext.request.contextPath}/altaFuncion'" value="Alta funcion" style="top:10%; left:52%;"><br></div>
	<%}%>

	<div class="titulo"><h1>Detalles d<span>e Usuario</span></h1></div>
	<div  class="card text-white bg-dark mb-3"  style="max-width: 1080rem;">
	  <div class="row no-gutters">
	    <div class="col-md-4">
	     <% if (u.getUrlImg() != null && !u.getUrlImg().equals("")){ %>
	     	<img src="<%= u.getUrlImg() %>" class="card-img" alt="Responsive image" >
	     <%} else { %>
	     	<img src="https://www.pinclipart.com/picdir/big/203-2030760_doug-petereson-perfil-whatsapp-sin-recortar-clipart.png" class="card-img" alt="Responsive image" >
	     <% } %> 
	    </div>
	    <div class="col-md-8">
	      <div class="card-body">
	      	<dl class="row">
  				<dt class="col-sm-3">Nickname</dt>
 				<dd class="col-sm-9"><%=u.getNickname() %></dd>
				<dt class="col-sm-3">Nombre</dt>
 				<dd class="col-sm-9"><%=u.getNombre() %></dd>
				<dt class="col-sm-3">Apellido</dt>
 				<dd class="col-sm-9"><%=u.getApellido() %></dd>
				<dt class="col-sm-3">Email</dt>
 				<dd class="col-sm-9"><%=u.getEmail() %></dd>
 				<dt class="col-sm-3">Fecha de nacimiento</dt>
 				<dd class="col-sm-9"><%= u.getFechaDate() %></dd>
 				<% if (u instanceof DataArtista){ %>
 					<% DataArtista a = (DataArtista)u;%>
 					<dt class="col-sm-3">Descripcion</dt>
 					<dd class="col-sm-9"><%=a.getDescripcion() %></dd>
					<dt class="col-sm-3">Biografia</dt>
 					<dd class="col-sm-9"><%=a.getBiografia() %></dd>
					<dt class="col-sm-3">Web</dt>
 					<dd class="col-sm-9"><a style="color: inherit" href="<%=a.getWeb() %>"><%= a.getWeb() %></a></dd>
 				<% } %>
				<dt class="col-sm-3">Siguiendo:</dt>
 				<dd class="col-sm-9">
 					<% List<String> siguiendo = u.getNickUsuariosSiguiendo();%>
					<% if (siguiendo != null) {%>
						<% for (int i = 0; i < siguiendo.size(); i++) { %>
							<a tyle="color: inherit" href="${pageContext.request.contextPath}/DetallesUsuario?usuarioseleccionado=<%=siguiendo.get(i)%>"> <%= siguiendo.get(i) %></a>
						<% } %>
					<% } %>
				</dd>
 				<dt class="col-sm-3">Seguidores:</dt>
 				<dd class="col-sm-9">
 					<% List<String> seguidores = u.getNickUsuariosSeguidores();%>
 					<% if (seguidores != null) {%>
					<% for (int j = 0; j < seguidores.size(); j++) { %>
						<a tyle="color: inherit" href="${pageContext.request.contextPath}/DetallesUsuario?usuarioseleccionado=<%=seguidores.get(j)%>"> <%= seguidores.get(j) %></a>
					<% }; %>
					<% } %>
 				</dd>
			</dl>
	      </div>
	    </div>
	  </div>
	</div>
	
	<%if (u instanceof DataEspectador) {%>
 		<%List<DataPremio> premios = (List<DataPremio>)request.getAttribute("premiosespectador"); %>
 	 	<% if (premios != null && premios.size() > 0) {%>
 			<div class="titulo"><h1>Premios <span>Ganados</span></h1></div>
              <div  class="card text-white bg-dark mb-3"  style="max-width: 1080rem;">
		  		<div class="row no-gutters">
		  		  <div class="card-body">
				 	<table class="table table-striped table-dark">
				  	  <thead>
						<tr>
						  <th scope="col">Fecha de sorteo</th>
						  <th scope="col">Espectáculo</th>
						  <th scope="col">Función</th>
						  <th scope="col">Premio</th>
						  <th scope="col">Comprobante</th>
						</tr>
					  </thead>
				  	  <tbody>
				    	<%for(int i = 0; i<premios.size(); i++) {%>
						  <tr>
						    <td><%=premios.get(i).getFecha() %></td>
						    <td><%=premios.get(i).getEspectaculo() %></td>
							<td><%=premios.get(i).getFuncion() %></td>
						    <td><%=premios.get(i).getDescripcion() %></td>
						    <td><a href="${pageContext.request.contextPath}/ComprobantePremio?premio=<%= premios.get(i).getFuncion() %>">PDF</a></td>
						  </tr>
				    	<% }%>
				 	  </tbody>
					</table>
				  </div>
				</div>
			  </div>
 		<% }%>
 	<% }%>
	
	<!-- Carrousel Artista-->
	<% if ((u instanceof DataArtista) && (request.getAttribute("espectaculostotales") != null)){ %>
		<% List<DataEspectaculo> des = (List<DataEspectaculo>)request.getAttribute("espectaculostotales"); %>
		<div class="titulo"><h1>Espect<span>áculos</span></h1></div>
		<div id="carouselEspectaculosPerfil" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<%for (int i=0; i<des.size(); i++) {%>
					<% if(i==0) {%>
						<li data-target="#carouselEspectaculosPerfil" data-slide-to="<%= i %>" class="active"></li>
					<% } else { %>
						<li data-target="#carouselEspectaculosPerfil" data-slide-to="<%= i %>"></li>
					<% } %>
					
				<% } %>
			</ol>
		    <div class="carousel-inner" role="listbox">
		    	<%for (int i=0; i<des.size(); i++) {%>
		    		<% if(i==0) {%>
		    			<div class="carousel-item active">
		    				<% if (des.get(i).getUrlImg() != null) {%>
		    					<img src="<%= des.get(i).getUrlImg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } else { %>
		    					<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } %>
		      				<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        				<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= des.get(i).getNombre() %>"><strong><%= des.get(i).getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } else { %>
		    			<div class="carousel-item">
		    				<% if (des.get(i).getUrlImg() != null) {%>
		    					<img src="<%= des.get(i).getUrlImg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } else { %>
		    					<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } %>
		      				<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        				<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= des.get(i).getNombre() %>"><strong><%= des.get(i).getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } %>
		    	<% } %>
		    	
		  	</div>
		  	<a class="carousel-control-prev" href="#carouselEspectaculosPerfil" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Anterior</span>
		  	</a>
		  	<a class="carousel-control-next" href="#carouselEspectaculosPerfil" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Siguiente</span>
		  	</a>		  	
		</div>
		<hr> 	
	<% }%>
	
	<% if ((u instanceof DataArtista) && (request.getAttribute("espectaculosfinalizados") != null)){ %>
		<% DataEspectaculoArray des = (DataEspectaculoArray)request.getAttribute("espectaculosfinalizados"); %>
		<% List<DataEspectaculo> desfin = des.getItem(); %>
		<div class="titulo"><h1>Espectáculos <span>Finalizados</span></h1></div>
		<div id="carouselEspectaculosFinalizadosPerfil" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<%for (int i=0; i<desfin.size(); i++) {%>
					<% if(i==0) {%>
						<li data-target="#carouselEspectaculosFinalizadosPerfil" data-slide-to="<%= i %>" class="active"></li>
					<% } else { %>
						<li data-target="#carouselEspectaculosFinalizadosPerfil" data-slide-to="<%= i %>"></li>
					<% } %>
					
				<% } %>
			</ol>
		    <div class="carousel-inner" role="listbox">
		    	<%for (int i=0; i<desfin.size(); i++) {%>
		    		<% if(i==0) {%>
		    			<div class="carousel-item active">
		    				<% if (desfin.get(i).getUrlImg() != null) {%>
		    					<img src="<%= desfin.get(i).getUrlImg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } else { %>
		    					<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } %>
		      				<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        				<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= desfin.get(i).getNombre() %>&finalizado=true"><strong><%= desfin.get(i).getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } else { %>
		    			<div class="carousel-item">
		    				<% if (desfin.get(i).getUrlImg() != null) {%>
		    					<img src="<%= desfin.get(i).getUrlImg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } else { %>
		    					<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } %>
		      				<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        				<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= desfin.get(i).getNombre() %>&finalizado=true"><strong><%= desfin.get(i).getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } %>
		    	<% } %>
		    	
		  	</div>
		  	<a class="carousel-control-prev" href="#carouselEspectaculosFinalizadosPerfil" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Anterior</span>
		  	</a>
		  	<a class="carousel-control-next" href="#carouselEspectaculosFinalizadosPerfil" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Siguiente</span>
		  	</a>		  	
		</div>
		<hr> 	
	<% }%>
	
	<!-- Carrousel Espectador-->
	<% if (u instanceof DataEspectador) {%>
		<% if (request.getAttribute("funcionesregistradasperfil") != null) {%>
			<% DataFuncion[] dfs = (DataFuncion[])request.getAttribute("funcionesregistradasperfil"); %>
			<div class="titulo"><h1>Funci<span>ones</span></h1></div>
			<div id="carouselFuncionesPerfil" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
					<%for (int i=0; i<dfs.length; i++) {%>
						<% if(i==0) {%>
							<li data-target="#carouselFuncionesPerfil" data-slide-to="<%= i %>" class="active"></li>
						<% } else { %>
							<li data-target="#carouselFuncionesPerfil" data-slide-to="<%= i %>"></li>
						<% } %>
					
					<% } %>
				</ol>
		    	<div class="carousel-inner" role="listbox">
		    		<%for (int i=0; i<dfs.length; i++) {%>
		    			<% if(i==0) {%>
		    				<div class="carousel-item active">
		    					<% if (dfs[i].getUrlimg() != null) {%>
		    						<img src="<%= dfs[i].getUrlimg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } else { %>
		    						<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } %>
		      					<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        					<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleFuncion?funcion=<%= dfs[i].getNombre() %>&espectaculo=<%= dfs[i].getEspectaculo()%>"><strong><%= dfs[i].getNombre() %></strong></a></h1>
		      					</div>
		    				</div>
		    			<% } else { %>
		    				<div class="carousel-item">
		    					<% if (dfs[i].getUrlimg() != null) {%>
		    						<img src="<%= dfs[i].getUrlimg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } else { %>
		    						<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } %>
		      					<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        					<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleFuncion?funcion=<%= dfs[i].getNombre() %>&espectaculo=<%= dfs[i].getEspectaculo()%>"><strong><%= dfs[i].getNombre() %></strong></a></h1>
		      					</div>
		    				</div>
		    			<% } %>
		    		<% } %>
		  		</div>
		  		<a class="carousel-control-prev" href="#carouselFuncionesPerfil" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    		<span class="sr-only">Anterior</span>
		  		</a>
		  		<a class="carousel-control-next" href="#carouselFuncionesPerfil" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    		<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    		<span class="sr-only">Siguiente</span>
		  		</a>
		  		
			</div>			
			<hr> 	
		<% } %>
		<% if (request.getAttribute("paquetescompradosperfil") != null) {%>
			<% DataPaquete[] dps = (DataPaquete[])request.getAttribute("paquetescompradosperfil"); %>
			<div class="titulo"><h1>Paqu<span>etes</span></h1></div>
			<div id="carouselPaquetesPerfil" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
					<%for (int i=0; i<dps.length; i++) {%>
						<% if(i==0) {%>
							<li data-target="#carouselPquetesPerfil" data-slide-to="<%= i %>" class="active"></li>
						<% } else { %>
							<li data-target="#carouselPaquetesPerfil" data-slide-to="<%= i %>"></li>
						<% } %>
					
					<% } %>
				</ol>
		    	<div class="carousel-inner" role="listbox">
		    		<%for (int i=0; i<dps.length; i++) {%>
		    			<% if(i==0) {%>
		    				<div class="carousel-item active">
		    					<% if (dps[i].getUrlImagen() != null) {%>
		    						<img src="<%= dps[i].getUrlImagen() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } else { %>
		    						<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } %>
		      					<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        					<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetallePaquete?paquete=<%= dps[i].getNombre() %>"><strong><%= dps[i].getNombre() %></strong></a></h1>
		      					</div>
		    				</div>
		    			<% } else { %>
		    				<div class="carousel-item">
		    					<% if (dps[i].getUrlImagen() != null) {%>
		    						<img src="<%= dps[i].getUrlImagen() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } else { %>
		    						<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    					<% } %>
		      					<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        					<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetallePaquete?paquete=<%= dps[i].getNombre() %>"><strong><%= dps[i].getNombre() %></strong></a></h1>
		      					</div>
		    				</div>
		    			<% } %>
		    		<% } %>
		  		</div>
		  		<a class="carousel-control-prev" href="#carouselPaquetesPerfil" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    		<span class="sr-only">Anterior</span>
		  		</a>
		  		<a class="carousel-control-next" href="#carouselPaquetesPerfil" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    		<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    		<span class="sr-only">Siguiente</span>
		  		</a>
			</div>
			<hr> 	
		<% } %>
	<% } %>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>