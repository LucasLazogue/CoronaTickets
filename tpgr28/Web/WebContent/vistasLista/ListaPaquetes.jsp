<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Listar Paquetes</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosLista.css">
	<link rel="stylesheet" href="../css/EstilosMenu.css">
	<%@ page import="servidor.DataUsuario" %>
	<%@ page import="servidor.DataArtista" %>
	<%@ page import="servidor.DataPaquete" %>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<jsp:include page="../cargarHistorial.jsp" />
	<div class="cuerpo bg-image mt-sm-4">
		<% if((DataUsuario)request.getSession().getAttribute("usuario") instanceof DataArtista) {%>
			<div class="btn-group" role="group" aria-label="Botones alta">
				<a href="${pageContext.request.contextPath}/altaPaquete" class="btn btn-outline-dark btn-lg" role="button" aria-pressed="true">+ Crear paquete de espectáculos</a>
			</div>
		<% } %>
		<% DataPaquete[] paquetes = (DataPaquete[])request.getAttribute("paquetes"); %>
		<div class="titulo"><h1>Paque<span>tes</span></h1></div>
		<% if (paquetes != null) {%>
		<div id="carouselPaquetes" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<% for (int i = 0; i < paquetes.length; i++) { %>
		    		<li data-target="#carouselPaquetes" data-slide-to=<%= i %> class="<%= i==0? "active" : "" %>"></li>
				<% } %>
			</ol>
		    <div class="carousel-inner carousel-adentro" role="listbox">
			<% for (int i = 0; i < paquetes.length; i++) { %>
		    	<div class="carousel-item  carousel-adentro <%= i==0? "active" : "" %>" >
		      		<img src="<%= paquetes[i].getUrlImagen() != null && !paquetes[i].getUrlImagen().equals("")? paquetes[i].getUrlImagen() : "https://www.antelarena.com.uy/assets/img/Show-Maluma-cels-Slide-9d8643cae1.jpg"%>" class="d-block w-100" alt="<%= paquetes[i].getNombre() %>">
		      		<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        		<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetallePaquete?paquete=<%= paquetes[i].getNombre() %>"><strong><%= paquetes[i].getNombre() %></strong></a></h1>
		      		</div>
		    	</div>
		    <%} %>
		  	</div>
		  	<a class="carousel-control-prev" href="#carouselPaquetes" role="button" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Anterior</span>
		  	</a>
		  	<a class="carousel-control-next" href="#carouselPaquetes" role="button" data-slide="next" onclick="$('#myCarousel').carousel('next')">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Siguiente</span>
		  	</a>
		</div>
		<%} else {%>
		<div class="titulo-invertido"><h3>No hay paquetes ingresados</h3></div>
		<%} %>
		<hr/>
	</div>
</body>
</html>