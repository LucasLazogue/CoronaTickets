<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Menú</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="./css/EstilosMenu.css">
	<%@ page import="servidor.DataUsuario" %>
</head>
<body>
	<nav class="navbar-expand-lg navbar navbar-dark bg-dark">
	  <a class="navbar-brand tituloMenu" href="${pageContext.request.contextPath}/home">corona<span>Tickets</span></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/home">Espectáculos</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/ListaPaquetes">Paquetes</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/ListaUsuarios">Usuarios</a>
	      </li>
	    </ul>
	    <div class="form-inline my-2 my-lg-0">
	      <input class="form-control mr-sm-2" type="search" placeholder="Espectáculos, paquetes" aria-label="Espectáculos, paquetes" id="clave">
	      <button class="btn btn-outline-success my-2 my-sm-0 mr-sm-4"  onClick="window.location.href='${pageContext.request.contextPath}/Busqueda?clave=' + document.getElementById('clave').value">Buscar</button>
	    </div>
	    <% if((DataUsuario)request.getSession().getAttribute("usuario") != null) {%>
		    <a class="boton btn btn-outline-danger  mr-sm-2" href="${pageContext.request.contextPath}/Perfil">Perfil</a>
		    <a class="boton btn btn-outline-danger" href="${pageContext.request.contextPath}/CerrarSesion">Cerrar sesión</a>
		<%} else {%>
			<div class="btn-group">
			  <button type="button" class="boton btn btn-outline-danger dropdown-toggle mr-sm-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    Registrarse
			  </button>
			  <div class="dropdown-menu">
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/Registro?tipo=artista">Artista</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/Registro?tipo=espectador">Espectador</a>
			  </div>
			</div>
			<a class="boton btn btn-outline-danger" href="${pageContext.request.contextPath}/IniciarSesion">Iniciar sesión</a>
		<%} %>
	  </div>
	</nav>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>