<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Historial</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<%@ page import="servidor.DataDatos" %>
		<%@ page import="java.util.*" %>

	</head>
	<body>
		<jsp:include page="./cargarHistorial.jsp" />   
		<div  class="card text-white bg-dark mb-3"  style="max-width: 1080rem;">
			<div class="row no-gutters">
				<div class="card-body">
					<table class="table table-striped table-dark">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">IP</th>
								<th scope="col">URL</th>
								<th scope="col">Browser</th>
								<th scope="col">OS</th>
							</tr >
						</thead>
						<tbody>
							<%
							List<DataDatos> datos = (List<DataDatos>) request.getAttribute("historico");
							for(int i = 0; i < datos.size(); i++) {
							%>
							<tr>
							<td><%= i+1 %></td>
							<td><%= datos.get(i).getIp() %></td>
							<td><%= datos.get(i).getUrl() %></td>
							<td><%= datos.get(i).getBrowser() %></td>
							<td><%= datos.get(i).getOs() %></td>
							</tr>
							<% }%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>