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
		<%@ page import="java.text.SimpleDateFormat" %>
	</head>
	<body class=bg-image>
		<jsp:include page="../Menu.jsp" />
		<div class="cuerpo">
			<% DataFuncion funcion = (DataFuncion)request.getAttribute("funcionData"); %>
			<% DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario"); %>
			<div class="jumbotron transparent mt-4">
			  <img src="<%= funcion.getUrlimg() %>" class="img-responsive" alt="Imagen no disponible">
			  <h1 class="display-4"><%= funcion.getNombre() %></h1>
			  <% SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");%>
			  <p>Espectáculo: <a href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= funcion.getEspectaculo() %>"><%= funcion.getEspectaculo() %></a></p>
			  <p>Fecha de alta: <%= df.format(funcion.getFechaAlta().toGregorianCalendar().getTime()) %></p>
			  <p>Fecha de inicio: <%= df.format(funcion.getFechaHoraInicio().toGregorianCalendar().getTime()) %></p>
			  <% if (funcion.getArtistas() == null || funcion.getArtistas().isEmpty()) {%>
			  <p class="mt-4">No hay artistas invitados.</p>
			  <%} else { %>
			  <table class="table mt-4 table-bordered table-striped">
			    <thead class="thead-dark">
			      <tr>
			        <th scope="col">Artistas Invitados</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% String[] artistas = funcion.getArtistas().toArray(new String[0]); %>
			    <% for(String art: artistas){ %>
			      <tr>
			        <td><%= art %></td>
			      </tr>
			    <%} %>
			    </tbody>
			  </table>
			  <%} %>
			</div>
		</div>
	</body>
</html>