<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalle Paquete</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosDetalle.css">
		<link rel="stylesheet" href="../css/EstilosMenu.css">
		<%@ page import="servidor.DataPaquete" %>
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="java.util.List" %>
	</head>
	<body>
		<jsp:include page="../Menu.jsp" />
		<jsp:include page="../cargarHistorial.jsp" />   

		<div class="cuerpo">
			<% DataPaquete paquete = (DataPaquete)request.getAttribute("paqueteData"); %>
			<% DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario"); %>
			<div class="bg-image">
				<div class="titulo">
					<div class="texto">
						<h1><%= paquete.getNombre() %></h1>
					</div>
				</div>
				<div class="center-block">
					<img src="<%= paquete.getUrlImagen() %>" class="img-responsive" alt="Foto de Paquete No Disponible">
				</div>
				<div class="texto">
					<div class="ml-4 mr-4">
						<h2>Detalles:</h2>
						<h4>Descripcion:</h4>
						<h5><%= paquete.getDescripcion() %></h5>
						<h4>Vigencia:</h4>
						<h5>Desde: <%= paquete.getInicioVigencia() %></h5>
						<h5>Hasta: <%= paquete.getFinVigencia() %></h5>
						<h4>Descuento:</h4>
						<h5><%= paquete.getDescuento() %>%</h5>
						<h4>Categorias:</h4>
						<% List<String> categorias = paquete.getCategorias(); %>
						<% if(categorias!=null){ %>
							<h5>
							<% for(int i=0; i<categorias.size()-1; i++){ %>
								<%= categorias.get(i) %>,
							<% } %>
							<% if(categorias.size()!=0){ %>
								<%= categorias.get(categorias.size()-1) %>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No se registraron categorias.</h5>
						<% } %>
						<h4>Espectaculos:</h4>
						<% List<String> espectaculos = (List<String>)request.getAttribute("espPaq"); %>
						<% if(espectaculos!=null){ %>
							<h5>
							<% for(int i=0; i<espectaculos.size()-1; i++){ %>
								<a href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= espectaculos.get(i) %>"><%= espectaculos.get(i) %></a>,
							<% } %>
							<% if(espectaculos.size()!=0){ %>
								<a href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= espectaculos.get(espectaculos.size()-1) %>"><%= espectaculos.get(espectaculos.size()-1) %></a>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No hay espectaculos registrados.</h5>
						<% } %>
						<% if(user instanceof DataEspectador){ %>
							<% if(request.getAttribute("error") != null){%>
	 							<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=(String)request.getAttribute("error")%></div>
	  						<%} %>
							<form><h4><input class="btn btn-success" type="submit" onClick="submit()" value="Conseguir" formmethod= "post"></h4></form>
						<% } %>
						<% if(user instanceof DataArtista){ %>
							<a class="btn btn-success" style="left:50%;" href="${pageContext.request.contextPath}/AgregarEspectaculoPaquete?
							paquete=<%= paquete.getNombre() %>">Agregar Espectaculo</a>
						<% } %>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>