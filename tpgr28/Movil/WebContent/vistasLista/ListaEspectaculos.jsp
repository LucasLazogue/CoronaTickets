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
	<%@ page import="servidor.DataEspectaculo" %>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div class="cuerpo bg-image mt-sm-4">
		<div class="mt-4 mb-4" style="display: flex; flex-direction: row;">
			<div class="dropdown mr-2">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    Plataformas
			  </button>
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			    <% String[] plataformas = (String[])request.getAttribute("plataformas"); %>
				<% for (String plat: plataformas) { %>
			    	<a class="dropdown-item" href="./home?plataforma=<%= plat%>"><%= plat %></a>
				<%} %>
			  </div>
			</div>
			<div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    Categorías
			  </button>
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			    <% String[] categorias = (String[])request.getAttribute("categorias"); %>
				<% for (String cat: categorias) { %>
			    	<a class="dropdown-item" href="./home?categoria=<%= cat%>"><%= cat %></a>
			    <%} %>
			  </div>
		  </div>
		</div>		
		<% DataEspectaculo[] espectaculos = (DataEspectaculo[])request.getAttribute("espectaculos"); %>
      <div class="container">
        <div class="row">
	      <% for (int i = 0; i < espectaculos.length; i++) { %>
          <p class="ml-4 mt-4" style="color: white;">Espectáculo: <a href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= espectaculos[i].getNombre() %>"><%= espectaculos[i].getNombre()%></a></p>    
          <img src="<%= espectaculos[i].getUrlImg() != null && !espectaculos[i].getUrlImg().equals("")? espectaculos[i].getUrlImg() : "https://www.antelarena.com.uy/assets/img/Show-Maluma-cels-Slide-9d8643cae1.jpg"%>" class="d-block w-100" alt="<%= espectaculos[i].getNombre() %>">
          <% } %>    
        </div>	 
      </div>     			
	</div>
</body>
</html>