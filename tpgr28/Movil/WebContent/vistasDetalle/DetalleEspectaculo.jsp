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
		<%@ page import="java.util.List" %>
		<%@ page import="java.text.SimpleDateFormat" %>
		<%@ page import="java.lang.Double" %>
	</head>
	<body class=bg-image>
		<jsp:include page="../Menu.jsp" />
		<div class="cuerpo">
			<% DataEspectaculo espectaculo = (DataEspectaculo)request.getAttribute("espectaculoData"); %>
			<% String plataforma = (String)request.getAttribute("plataforma"); %>
			<div class="jumbotron transparent mt-4">
			  <img src="<%= espectaculo.getUrlImg() %>" class="img-responsive" alt="Imagen no disponible">
			  <h1 class="display-4"><%= espectaculo.getNombre() %></h1>
			  <% SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");%>
			  <p><%= plataforma %> - <%= df.format(espectaculo.getFechaRegistro().toGregorianCalendar().getTime()) %> - <%= espectaculo.getDuracion() %> min</p>
			  <p class="lead"><%= espectaculo.getDescripcion() %></p>
			  <p><% double prom = (double)request.getAttribute("promedio");
			  if(prom < 0.5) {%>
			  	☆☆☆☆☆
			  <%} else if (prom < 1.5) {%>
			  	★☆☆☆☆
			  <%} else if (prom < 2.5) {%>
			  	★★☆☆☆
			  <%} else if (prom < 3.5) {%>
			  	★★★☆☆
			  <%} else if (prom < 4.5) {%>
			  	★★★★☆
			  <%} else {%>
			  	★★★★★
			  <%} %>
			  (<%=Double.toString(prom).substring(0, 3)%>/5) - 
			  <%= (int)(double)request.getAttribute("totalVal") %> votos.</p>
			  <p>Precio: $<%= espectaculo.getCosto() %></p>
			  <a class="lead" href='<%= espectaculo.getUrl() %>'>Ir al sitio web</a> - 
			  <a class="lead" href='https://youtu.be/<%= espectaculo.getUrlVideo() %>'>Ver video</a>
			  <table class="table mt-4 table-bordered table-striped">
			    <thead class="thead-dark">
			      <tr>
			        <th scope="col">Categorias</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% String[] categorias = espectaculo.getNombresCategorias().toArray(new String[0]); %>
			    <% for(String cat: categorias){ %>
			      <tr>
			        <td><%= cat %></td>
			      </tr>
			    <%} %>
			    </tbody>
			  </table>
			  <% if (espectaculo.getNombresFunciones() == null || espectaculo.getNombresFunciones().isEmpty()) {%>
			  <p class="mt-4">El espectáculo no tiene funciones.</p>
			  <%} else { %>
			  <table class="table mt-4 table-bordered table-striped">
			    <thead class="thead-dark">
			      <tr>
			        <th scope="col">Funciones</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% String[] funciones = espectaculo.getNombresFunciones().toArray(new String[0]); %>
			    <% for(String fun: funciones){ %>
			      <tr>
			        <td>
			        	<a href="${pageContext.request.contextPath}/DetalleFuncion?espectaculo=<%= espectaculo.getNombre() %>&funcion=<%= fun %>"><%= fun %></a>
			        </td>
			      </tr>
			    <%} %>
			    </tbody>
			  </table>
			  <%} %>
			  <% if (espectaculo.getNombresPaquetes() == null || espectaculo.getNombresPaquetes().isEmpty()) {%>
			  <p class="mt-4">El espectáculo no pertenece a ningún paquete.</p>
			  <%} else { %>
			  <table class="table mt-4 table-bordered table-striped">
			    <thead class="thead-dark">
			      <tr>
			        <th scope="col">Paquetes</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% String[] paquetes = espectaculo.getNombresPaquetes().toArray(new String[0]); %>
			    <% for(String paq: paquetes){ %>
			      <tr>
			        <td><%= paq %></td>
			      </tr>
			    <%} %>
			    </tbody>
			  </table>
			  <%} %>
			</div>
		</div>
	</body>
</html>