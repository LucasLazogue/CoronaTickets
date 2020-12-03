<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalle Funcion</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosDetalle.css">
		<link rel="stylesheet" href="../css/EstilosMenu.css">
		<%@ page import="servidor.DataFuncion" %>
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="java.util.List" %>
	</head>
	<body>
		<jsp:include page="../Menu.jsp" />
		<jsp:include page="../cargarHistorial.jsp" />   

		<div class="cuerpo">
			<% DataFuncion funcion = (DataFuncion)request.getAttribute("funcionData"); %>
			<% DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario"); %>
			<% String[] espectadores = (String[])request.getAttribute("espectadoresRegistrados"); %>
			<% String[] espectadoresGanadores = (String[])request.getAttribute("espectadoresGanadores"); %>
			<% boolean habilitarSortear = (boolean)request.getAttribute("habilitarSortear"); %>
			<div class="bg-image">
				<div class="titulo">
					<div class="texto">
						<h1><%= funcion.getNombre() %></h1>
					</div>
				</div>
				<div class=center-block>
					<img src="<%= funcion.getUrlimg() %>" class="img-responsive" alt="Foto de Funcion No Disponible">
				</div>
				<div class="texto">
					<div class="ml-4 mr-4">
						<h2>Detalles:</h2>
						<h4>Fecha de Inicio:</h4>
						<h5><%= funcion.getFechaHoraInicio() %></h5>
						<h4>Fecha de Registro:</h4>
						<h5><%= funcion.getFechaAlta() %></h5>
						<h4>Artistas Invitados:</h4>
						<% List<String> artistasInv = funcion.getArtistas(); %>
						<% if(artistasInv!=null){ %>
							<h5>
							<% for(int i=0; i<artistasInv.size()-1; i++){ %>
								<%= artistasInv.get(i) %>,
							<%} %>
							<% if(artistasInv.size()!=0){ %>
								<%= artistasInv.get(artistasInv.size()-1) %>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No hay artistas invitados.</h5>
						<% } %>
						<% if(user instanceof DataEspectador){ %>
							<div class="center-block">
								<a class="btn btn-success" style="left:50%;" href="${pageContext.request.contextPath}/RegistroFuncion?
								espectaculo=<%= funcion.getEspectaculo() %>&funcion=<%= funcion.getNombre() %>">Registrarse</a>
							</div>
						<%} %>
						<% if(user instanceof DataArtista && habilitarSortear && espectadores.length != 0){ %>
							<% if(!funcion.isSorteado()){ %>
								<h4>Sorteo: No Realizado</h4>
								<h4>Espectadores:</h4>
								<h5>
								<% for(int i=0; i<espectadores.length-1; i++){ %>
									<%= espectadores[i] %>,
								<%} %>
								<% if(espectadores.length!=0){ %>
									<%= espectadores[espectadores.length-1] %>
								<% } %>
								</h5>
								<% if(request.getAttribute("error") != null){%>
	 								<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=(String)request.getAttribute("error")%></div>
	  							<%} %>
	  							<% if((boolean)request.getAttribute("noEsAceptado")) {%>
									<form><h4><input class="btn btn-success" type="submit" onClick="submit()" value="Sortear" formmethod= "post"></h4></form>
								<%} %>
							<% }else{ %>
								<h4>Sorteo: Realizado</h4>
								<h4>Espectadores Ganadores:</h4>
								<h5>
								<% for(int i=0; i<espectadoresGanadores.length-1; i++){ %>
									<%= espectadores[i] %>,
								<%} %>
								<% if(espectadoresGanadores.length!=0){ %>
									<%= espectadoresGanadores[espectadoresGanadores.length-1] %>
								<% } %>
								</h5>
							<% } %>
						<%} %>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>