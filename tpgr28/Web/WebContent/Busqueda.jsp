<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Búsqueda</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosLista.css">
	<link rel="stylesheet" href="../css/EstilosMenu.css">
	<%@ page import="servidor.DataEspectaculo" %>
	<%@ page import="servidor.DataPaquete" %>
</head>
<body>
	<jsp:include page="./Menu.jsp" />
	<jsp:include page="./cargarHistorial.jsp" />   
	<div class="cuerpo bg-image mt-sm-4">
		<% Object[] resultado = (Object[])request.getAttribute("resultado"); %>
		<div class="titulo"><h1>Resultados de<span> la búsqueda</span></h1></div>
		<% if (resultado != null && resultado.length != 0) {%>
		<form id="filtrar" style="background-color: white; width: fit-content; border-radius: 20px 20px 20px 20px;">
			<input type= "hidden" value="<%= request.getParameter("clave") %>" id="filtrar" name="clave">
			<div style="display: flex;" id="filtrar">
				<label class="mt-sm-3 mr-sm-3 ml-sm-3 col-form-label" for="ordenar">Ordenar: </label>
				<select class="form-control mt-sm-3 mr-sm-3" name="ordenar" id="filtrar">
				    <option value="alfabeticamente">Alfabéticamente</option>
				    <% if ((String)request.getParameter("ordenar") != null && ((String)request.getParameter("ordenar")).equals("fecha")) {%>
				    	<option value="fecha" selected>Fecha de publicación</option>
				    <%} else {%>
				    	<option value="fecha">Fecha de publicación</option>
				    <%}%>
				</select>
				<label class="mt-sm-3 mr-sm-3 col-form-label" for="plataforma">Plataforma: </label>
				<select class="form-control mt-sm-3 mr-sm-3" name="plataforma" id="filtrar">
				    <option>Cualquiera</option>
				    <% String[] plataformas = (String[])request.getAttribute("plataformas"); %>
					<% for (String plat: plataformas) { %>
		      			<% if ((String)request.getParameter("plataforma") != null && ((String)request.getParameter("plataforma")).equals(plat)) {%>
		      				<option selected><%= plat%></option>
					    <%} else {%>
		      				<option><%= plat%></option>
					    <%}%>
					<%}%>
				</select>
				<label class="mt-sm-3 mr-sm-3 col-form-label" for="categoria">Categoría: </label>
				<select class="form-control mt-sm-3 mr-sm-3" name="categoria" id="filtrar">
				    <option>Cualquiera</option>
				    <% String[] categorias = (String[])request.getAttribute("categorias"); %>
					<% for (String cat: categorias) { %>
		      			<% if ((String)request.getParameter("categoria") != null && ((String)request.getParameter("categoria")).equals(cat)) {%>
		      				<option selected><%= cat%></option>
					    <%} else {%>
		      				<option><%= cat%></option>
					    <%}%>
					<%}%>
				</select>
			</div>
			<ul class="list-unstyled">
				<div style="display: flex; justify-content: space-between">
					<p class="mt-sm-3 ml-sm-3 col-form-label"><%= resultado.length %> resultado/s</p>
					<input id="filtrar" type="submit" class="form-input btn btn-outline-success btn-sm mt-sm-3 mr-sm-3" form="filtrar" onClick="submit()" value="Filtrar">
				</div>
				<hr/>
				<% for (int i = 0; i < resultado.length; i++) {%>
					<% if (resultado[i] instanceof DataEspectaculo) {%>
			    	<li class="media">
						<img src="<%= ((DataEspectaculo)resultado[i]).getUrlImg() %>" class="mr-3 ml-sm-3" width="64" height="64" alt="Sin imagen">
						<div class="media-body">
					    	<h5 class="mt-0 mb-1"><a href="${pageContext.request.contextPath}/DetalleEspectaculo?espectaculo=<%= ((DataEspectaculo)resultado[i]).getNombre() %>"><%= ((DataEspectaculo)resultado[i]).getNombre() %></a></h5>
					    	<%= ((DataEspectaculo)resultado[i]).getDescripcion() %>
					    	<p style="font-size: small">Fecha de publicación: <%= ((DataEspectaculo)resultado[i]).getFechaRegistro() %></p>
					    </div>
					</li>
					<%} else {%>
					<li class="media">
						<img src="<%= ((DataPaquete)resultado[i]).getUrlImagen() %>" class="mr-3 ml-sm-3" width="64" height="64" alt="Sin imagen">
						<div class="media-body">
					    	<h5 class="mt-0 mb-1"><a href="${pageContext.request.contextPath}/DetallePaquete?paquete=<%= ((DataPaquete)resultado[i]).getNombre() %>"><%= ((DataPaquete)resultado[i]).getNombre() %></a></h5>
					    	<%= ((DataPaquete)resultado[i]).getDescripcion() %>
					    	<p style="font-size: small">Fecha de publicación: <%= ((DataPaquete)resultado[i]).getFechaDeAlta() %></p>
					    </div>
					</li>
					<%} %>
					<hr/>
			    <%} %>
			</ul>
		</form>
		<%} else {%>
		<div class="titulo-invertido"><h3>La búsqueda no ha retornado resultados</h3></div>
		<%} %>
		<hr/>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>