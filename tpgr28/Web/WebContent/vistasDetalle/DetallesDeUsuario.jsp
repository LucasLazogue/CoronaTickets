<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalles de usuario</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/DetallesDEUsuario.css">	
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="servidor.DataEspectaculo" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="servidor.DataFuncion" %>
		<%@ page import="java.util.List" %>
	</head>
	
<body class="bg-image">
	<jsp:include page="../Menu.jsp" />
	<jsp:include page="../cargarHistorial.jsp" />   

	<% DataUsuario u = (DataUsuario)request.getAttribute("detalleusuario");%>
	<div class="titulo"><h1>Detalles d<span>e Usuario</span></h1></div>
	<div  class="card text-white bg-dark mb-3"  class="center-block"  class="card" style="max-width: 1080rem;">
	  <div class="row no-gutters">
	    <div class="col-md-4">
	     <% if (u.getUrlImg() != null){ %>
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
							<a tyle="color: inherit" href="/ctUy-web/DetallesUsuario?usuarioseleccionado=<%=siguiendo.get(i)%>"> <%= siguiendo.get(i) %></a>
						<% } %>
					<% } %>
				</dd>
 				<dt class="col-sm-3">Seguidores:</dt>
 				<dd class="col-sm-9">
 					<% List<String> seguidores = u.getNickUsuariosSeguidores();%>
 					<% if (seguidores != null) {%>
					<% for (int j = 0; j < seguidores.size(); j++) { %>
						<a tyle="color: inherit" href="/ctUy-web/DetallesUsuario?usuarioseleccionado=<%=seguidores.get(j)%>"> <%= seguidores.get(j) %></a>
					<% }; %>
					<% } %>
 				</dd>
			</dl>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- Carrousel Artista-->
	<% if ((u instanceof DataArtista) && (request.getAttribute("espectaculosaceptados") != null)){ %>
		<% DataEspectaculo[] des = (DataEspectaculo[])request.getAttribute("espectaculosaceptados"); %>
		<div class="titulo"><h1>Espect<span>áculos</span></h1></div>
		<div id="carouselEspectaculos" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<%for (int i=0; i<des.length; i++) {%>
					<% if(i==0) {%>
						<li data-target="#carouselEspectaculos" data-slide-to="<%= i %>" class="active"></li>
					<% } else { %>
						<li data-target="#carouselEspectaculos" data-slide-to="<%= i %>"></li>
					<% } %>
					
				<% } %>
			</ol>
		    <div class="carousel-inner" role="listbox">
		    	<%for (int i=0; i<des.length; i++) {%>
		    		<% if(i==0) {%>
		    			<div class="carousel-item active">
		    				<% if (des[i].getUrlImg() != null) {%>
		    					<img src="<%= des[i].getUrlImg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } else { %>
		    					<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } %>
		      				<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        				<h1><a style="color: inherit" href="/ctUy-web/DetalleEspectaculo?espectaculo=<%= des[i].getNombre() %>"><strong><%= des[i].getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } else { %>
		    			<div class="carousel-item">
		    				<% if (des[i].getUrlImg() != null) {%>
		    					<img src="<%= des[i].getUrlImg() %>" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } else { %>
		    					<img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Sin_foto.svg" class="d-block w-100" alt="Imagen <%= i %>">
		    				<% } %>
		      				<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        				<h1><a style="color: inherit" href="/ctUy-web/DetalleEspectaculo?espectaculo=<%= des[i].getNombre() %>"><strong><%= des[i].getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } %>
		    	<% } %>
		    	
		  	</div>
		  	<a class="carousel-control-prev" href="#carouselEspectaculos" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Anterior</span>
		  	</a>
		  	<a class="carousel-control-next" href="#carouselEspectaculos" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Siguiente</span>
		  	</a>
		</div>
		<hr> 	
	<% }%>
	<!-- Carrousel Espectador-->
	<% if ((u instanceof DataEspectador) && (request.getAttribute("funcionesregistradas") != null)){ %>
		<% DataFuncion[] dfs = (DataFuncion[])request.getAttribute("funcionesregistradas"); %>
		<div class="titulo"><h1>Funci<span>ones</span></h1></div>
		<div id="carouselFunciones" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<%for (int i=0; i<dfs.length; i++) {%>
					<% if(i==0) {%>
						<li data-target="#carouselFunciones" data-slide-to="<%= i %>" class="active"></li>
					<% } else { %>
						<li data-target="#carouselFunciones" data-slide-to="<%= i %>"></li>
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
		        				<h1><a style="color: inherit" href="/ctUy-web/DetalleFuncion?funcion=<%= dfs[i].getNombre() %>&espectaculo=<%= dfs[i].getEspectaculo()%>"><strong><%= dfs[i].getNombre() %></strong></a></h1>
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
		        				<h1><a style="color: inherit" href="/ctUy-web/DetalleFuncion?funcion=<%= dfs[i].getNombre() %>&espectaculo=<%=dfs[i].getEspectaculo() %>"><strong><%= dfs[i].getNombre() %></strong></a></h1>
		      				</div>
		    			</div>
		    		<% } %>
		    	<% } %>
		    	
		  	</div>
		  	<a class="carousel-control-prev" href="#carouselFunciones" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Anterior</span>
		  	</a>
		  	<a class="carousel-control-next" href="#carouselFunciones" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Siguiente</span>
		  	</a>
		</div>
		<hr> 	
	<% } %>

</body>
</html>