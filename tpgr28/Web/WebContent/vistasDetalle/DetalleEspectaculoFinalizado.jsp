<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalle Espectaculo</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosDetalle.css">
		<link rel="stylesheet" href="../css/EstilosMenu.css">
		<%@ page import="servidor.DataEspectaculo" %>
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="servidor.DataPuntaje" %>
		<%@ page import="java.util.List" %>
		<%@ page import="java.lang.Double" %>
	</head>
	<body>
		<jsp:include page="../Menu.jsp" />
		<jsp:include page="../cargarHistorial.jsp" />   
		<div class="cuerpo">
			<% DataEspectaculo espectaculo = (DataEspectaculo)request.getAttribute("espectaculoData"); %>
			<% DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario"); %>
			<div class=bg-image>
				<div class="titulo texto">
					<div class="centrar" style=" display: flex; justify-content: center; flex-direction: row;">
						<h1><%= espectaculo.getNombre() %></h1>
						<% if (user instanceof DataEspectador) {%>
			      			<a class="btn btn-outline-warning btn-lg" style="border: none; background: none; box-shadow: none;" href="./DetalleEspectaculo?espectaculo=<%= espectaculo.getNombre() %>&favorito=<%= espectaculo.getNombre()%>">
			      				<% if (!((DataEspectador)user).getEspectaculosFavoritos().contains(espectaculo.getNombre())) {%>
					      			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill iconFavorito" fill="grey" xmlns="http://www.w3.org/2000/svg">
										<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
									</svg>
								<%} else {%>
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill iconNoFavorito" fill="yellow" xmlns="http://www.w3.org/2000/svg">
										<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
									</svg>
								<%} %>
							</a>
						<%} %>
					</div>
				</div>
				<div class="texto">
					<div class="ml-4 mr-4">
						<h2>Detalles:</h2>
						<h4>Descripcion:</h4>
						<h5><%= espectaculo.getDescripcion() %></h5>
						<h4>Duracion:</h4>
						<h5><%= espectaculo.getDuracion() %></h5>
						<h4>Fecha de Alta:</h4>
						<h5><%= espectaculo.getFechaRegistro().toGregorianCalendar().getTime() %></h5>
						<h4>Plataforma:</h4>
						<h4>Funciones:</h4>
						<% List<String> funciones = espectaculo.getNombresFunciones(); %>
						<% if(funciones!=null){ %>
							<h5>
							<% for(int i=0; i<funciones.size()-1; i++){ %>
								<h5><%= funciones.get(i) %>, </h5>
							<% } %>
							<% if(funciones.size()!=0){ %>
								<h5><%= funciones.get(funciones.size()-1) %></h5>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No hay funciones disponibles.</h5>
						<% } %>
						<h4>Precio:</h4>
						<h5>$<%= espectaculo.getCosto() %></h5>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>