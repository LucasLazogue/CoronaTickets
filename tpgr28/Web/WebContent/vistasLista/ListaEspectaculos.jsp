<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Listar espectáculos</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosLista.css">
	<link rel="stylesheet" href="../css/EstilosMenu.css">
	<%@ page import="servidor.DataUsuario" %>
	<%@ page import="servidor.DataArtista" %>
	<%@ page import="servidor.DataEspectador" %>
	<%@ page import="servidor.DataEspectaculo" %>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<jsp:include page="../cargarHistorial.jsp" />   
	<div class="cuerpo bg-image mt-sm-4">
		<% if((DataUsuario)request.getSession().getAttribute("usuario") instanceof DataArtista) {%>
			<div class="btn-group" role="group" aria-label="Botones alta">
				<a href="${pageContext.request.contextPath}/altaEspectaculo" class="btn btn-outline-dark btn-lg" role="button" aria-pressed="true">+ Alta de espectáculo</a>
				<a href="${pageContext.request.contextPath}/altaFuncion" class="btn btn-outline-dark btn-lg" role="button" aria-pressed="true">+ Alta de función de espectáculo</a>
			</div>
		<% } %>
		<% DataEspectaculo[] espectaculos = (DataEspectaculo[])request.getAttribute("espectaculos"); %>
		<div class="titulo">
			<h1>Espect<span>áculos</span></h1>
			<% String nomPlatOCatElegida = "Twitter Live";
			if (request.getParameter("plataforma") != null)
				nomPlatOCatElegida = (String)request.getParameter("plataforma");
			else if (request.getParameter("categoria") != null)
				nomPlatOCatElegida = (String)request.getParameter("categoria");%>
			<p style="color: white"><%= nomPlatOCatElegida %></p>
		</div>
		<% if (espectaculos != null && espectaculos.length > 0) {%>
		<div id="carouselEspectaculos" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<% for (int i = 0; i < espectaculos.length; i++) { %>
		    		<li data-target="#carouselEspectaculos" data-slide-to=<%= i %> class="<%= i==0? "active" : "" %>"></li>
				<% } %>
			</ol>
			<div class="carousel-inner carousel-adentro" role="listbox">
			<% for (int i = 0; i < espectaculos.length; i++) { %>
		    	<div class="carousel-item carousel-adentro <%= i==0? "active" : "" %>">
		      		<img src="<%= espectaculos[i].getUrlImg() != null && !espectaculos[i].getUrlImg().equals("")? espectaculos[i].getUrlImg() : "https://www.antelarena.com.uy/assets/img/Show-Maluma-cels-Slide-9d8643cae1.jpg"%>" class="d-block w-100" alt="<%= espectaculos[i].getNombre() %>">
		      		<div class="carousel-caption d-none d-md-block tituloCarrusel">
		        		<div class="centrar" style="justify-content: center; flex-direction: row;">
			        		<h1><a style="color: inherit" href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= espectaculos[i].getNombre() %>"><strong><%= espectaculos[i].getNombre() %></strong></a></h1>
			      			<% DataUsuario usuario = (DataUsuario)request.getSession().getAttribute("usuario");
			      			if (usuario instanceof DataEspectador) {%>
				      			<a class="btn btn-outline-warning btn-lg" style="border: none; background: none; box-shadow: none;" href="./home?favorito=<%= espectaculos[i].getNombre()%>">
				      				<% if (!((DataEspectador)usuario).getEspectaculosFavoritos().contains(espectaculos[i].getNombre())) {%>
						      			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill iconFavorito" fill="grey" xmlns="http://www.w3.org/2000/svg">
											<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
										</svg>
									<%} else {%>
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill iconNoFavorito" fill="yellow" xmlns="http://www.w3.org/2000/svg">
											<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
										</svg>
									<%} %>
								</a>
								<div class="card" style="width: 3rem; color: grey"><%= espectaculos[i].getCantVecesEspectaculoFavorito() %></div>
							<%} %>
						</div>
		      		</div>
		    	</div>
		    <%} %>
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
		<%} else {%>
		<div class="titulo-invertido"><h3>No hay espectaculos ingresados y aceptados</h3></div>
		<%} %>
		<hr/>
		<div class="subtitulo"><h3><strong>Filtrar espectáculos por:</strong></h3></div>
		<div class="accordion centrar" id="plataformasCategorias">
		  	<div class="titulo">
		    	<div id="headingOne">
	        		<button class="btn btn-link btn-block collapsed" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	          			<h1>Plataf<span>ormas</span></h1>
	        		</button>
		    	</div>
		    	<% boolean platActivo = true;
		    	if (request.getParameter("categoria") != null) platActivo = false;%>
		    	<div id="collapseOne" class="collapse <%= platActivo? "show" : "" %>" aria-labelledby="headingOne" data-parent="#plataformasCategorias">
		      		<div class="card-body">
		      			<% String[] plataformas = (String[])request.getAttribute("plataformas"); %>
						<% for (String plat: plataformas) { %>
			      			<% if (((String)request.getParameter("plataforma") != null && ((String)request.getParameter("plataforma")).equals(plat)) 
			      					|| (String)request.getParameter("plataforma") == null && (String)request.getParameter("categoria") == null && plat.equals("Twitter Live")) {%>
						    <a type="radio" class="dropdown-item dropdown-pers active" href="./home?plataforma=<%= plat%>"><%= plat%></a>
						    <%} else {%>
						    <a type="radio" class="dropdown-item dropdown-pers" href="./home?plataforma=<%= plat%>"><%= plat%></a>
						    <%}%>
						<%}%>
					</div>
		    	</div>
		  	</div>
		  	<div class="titulo-invertido">
		    	<div id="headingTwo">
	        		<button class="btn btn-link btn-block collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	          			<h1>Categ<span>orías</span></h1>
	        		</button>
		    	</div>
		    	<div id="collapseTwo" class="collapse <%= platActivo? "" : "show" %>" aria-labelledby="headingTwo" data-parent="#plataformasCategorias">
		      		<div class="card-body">
		      			<% String[] categorias = (String[])request.getAttribute("categorias"); %>
						<% for (String cat: categorias) { %>
			      			<% if ((String)request.getParameter("categoria") != null && ((String)request.getParameter("categoria")).equals(cat)) {%>
						    <a type="radio" class="dropdown-item dropdown-pers active" href="./home?categoria=<%= cat%>"><%= cat%></a>
						    <%} else {%>
						    <a type="radio" class="dropdown-item dropdown-pers" href="./home?categoria=<%= cat%>"><%= cat%></a>
						    <%}%>
						<%}%>
		      		</div>
		    	</div>
		  	</div>
		</div>
	</div>	
	</body>
</html>