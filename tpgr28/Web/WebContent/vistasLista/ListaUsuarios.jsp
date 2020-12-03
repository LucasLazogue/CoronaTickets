<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Listar Usuarios</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/EstilosMenu.css">
	<jsp:include page="../Menu.jsp"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosLista.css">
	<%@ page import="servidor.DataUsuario" %>
	<%@ page import="java.util.List" %>
</head>
<body>
	<jsp:include page="../cargarHistorial.jsp" />   
	<div class="bg-image cuerpo">
		<div class="titulo"style="border-radius: 20px 20px 20px 20px"><h1>Usua<span>rios</span></h1></div>
		<% if ((DataUsuario[])request.getAttribute("usuarios") != null) {%>
		<hr/>
		<div class="row row-cols-1" style="margin-left: 2%; margin-right: 2%">
			<% boolean haySesion = request.getSession().getAttribute("usuario") != null;%>
			<% for (DataUsuario usuario: (DataUsuario[])request.getAttribute("usuarios")) { %>
		  	<div class="col mb-4">
		    	<div class="card">
		      		<img src="<%= usuario.getUrlImg() != null && !usuario.getUrlImg().equals("")? usuario.getUrlImg() : "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQVYUbi-Jf5QxIW-koSAO97ZmKrOXadXeJ3xQ&usqp=CAU"%>" class="card-img-top" alt="...">
		      		<div class="card-body centrar">
		        		<h5 class="card-title"><a style="color: inherit" href="${pageContext.request.contextPath}/DetallesUsuario?usuarioseleccionado=<%= usuario.getNickname() %>"><%= usuario.getNickname() %></a></h5>
		        		<% boolean esSiMismo = false;
		        		boolean sigue = false;
		        		String[] usuariosSiguiendo = null;
		        		if (haySesion) {
		        			esSiMismo = usuario.getNickname().equals(((DataUsuario)request.getSession().getAttribute("usuario")).getNickname());
		        			List<String> us = ((DataUsuario)request.getSession().getAttribute("usuario")).getNickUsuariosSiguiendo();
		        			usuariosSiguiendo = us == null ? null : us.toArray(new String[0]);
		        		}
		        		if (usuariosSiguiendo != null && usuariosSiguiendo.length != 0) {
			        		for (String siguiendo: usuariosSiguiendo) {
			        			sigue = sigue || siguiendo.equals(usuario.getNickname());
			        		} 
			        	}%>
		        		<button type="button" class="btn btn-outline-<%= sigue? "danger" : "success"%>" style="<%= !haySesion || esSiMismo? "visibility: hidden; height: 0px" : "" %>" onClick="window.location.href='./ListaUsuarios?<%= sigue? "noseguir" : "seguir"%>=<%= usuario.getNickname()%>'"><%= sigue? "Dejar de seguir" : "Seguir usuario" %></button>
		      		</div>
		    	</div>
		  	</div>
		  	<%} %>
		</div>
	<%} else {%>
		<h3 class="mt-4">No hay usuarios en el sistema</h3>
	<%} %>
	</div>
</body>