<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modificar perfil</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="./css/registroUsuarioArtista.css">
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="java.util.Date" %>			
	</head>
	<body class="bg-image">
		<jsp:include page="./Menu.jsp" />
		<jsp:include page="./cargarHistorial.jsp" />   
		<%DataUsuario u = (DataUsuario)request.getSession().getAttribute("usuario"); %>
		<%String fechaMostrar = request.getAttribute("fecha").toString().split("T")[0]; %>
			<div class="center">
				<div class="parent">
					<div class="titulo-inicial"><h1>Modificar<span>Perfil</span></h1></div>
					<div class="datos-registro" class="center-div"  style = "background: #c2c2c2;">
						<form class="needs-validation" style="margin-left: 10px; margin-right: 10px;"novalidate method="post">
						  <div class="form-row">
						    <div class="col-md-4 mb-3">
						      <label for="nombre" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Nombre</label>
						      <input type="text" class="form-control" id="nombre" placeholder="Nombre" value = "<%=u.getNombre()%>"required name="nombre">
						    </div>
						    <div class="col-md-4 mb-3">
						      <label for="apellido" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Apellido</label>
						      <input type="text" class="form-control" id="apellido" placeholder="Apellido" value = "<%=u.getApellido()%>" required name="apellido">
                            </div>
						    <div class="col-md-3 mb-3">
								<label for="fechanac" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >F. de nacimiento</label>
						      <input type="date" class="form-control" id="fechanac" placeholder="F. de nacimiento" value = "<%=fechaMostrar%>" required name="fechanac">
						    </div>
						    <div class="col-md-4 mb-3">
						      <label for="urlimg" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >url imagen</label>
						      <input type="text" class="form-control" id="urlimg" placeholder="url imagen" value = "<%=u.getUrlImg() %>" name="urlimg">
						    </div>
						    <% if (u instanceof DataArtista) {%>
						    <div class="col-md-6 mb-3">
						      <label for="descripcion" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Descripcion</label>
						      <textarea class="form-control" id="descripcion" placeholder="Descripcion" required  name="descripcion"><%=((DataArtista)u).getDescripcion() %></textarea>
						    </div>
							<div class="col-md-6 mb-3">
						      <label for="biografia" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Biografia</label>
						      <textarea class="form-control" id="biografia" placeholder="Biografia" name="biografia"><%=((DataArtista)u).getBiografia() %></textarea>
						    </div>
						    <div class="col-md-6 mb-3">
						      <label for="web" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Web</label>
						      <textarea class="form-control" id="web" placeholder="Web" name="web"><%=((DataArtista)u).getWeb() %></textarea>
						    </div>
						    <% } %>
						  </div>
						  <div class="center-block">
						  	<button class="btn btn-primary" type="submit" style="border-radius: 10px; background: #BD255E; color: #FFFFFF; font-size: 14px; margin-bottom: 2%; top: 20%;" formmethod="post">Modificar</button>
						  </div>
						</form>
					</div>
				</div>
			</div>
	</body> 
</html>