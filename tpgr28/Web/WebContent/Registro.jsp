<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro de Usuario</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="./css/registroUsuarioArtista.css">		
	</head>
	<body class="bg-image">
		<jsp:include page="./Menu.jsp" />
		<jsp:include page="./cargarHistorial.jsp" />   
		<% if (request.getAttribute("tipo") != null && request.getAttribute("tipo").equals("artista")) {%>
			<div class="center mt-2">
				<div class="parent">
					<div class="titulo-inicial"><h1>Registro d<span>e Artista</span></h1></div>
					<div class="datos-registro" class="center-div"  style = "background: #c2c2c2;">
						<% if (request.getAttribute("errorpass") != null) {%>
							<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=request.getAttribute("errorpass") %></div>
						<% } %>
						<% if (request.getAttribute("errorusuario") != null) {%>
							<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=request.getAttribute("errorusuario") %></div>
						<% } %>
						<form class="needs-validation" style="margin-left: 10px; margin-right: 10px;"novalidate method="post">
						  <div class="form-row">
						  	<div class="col-md-4">
						      <label for="nickname" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Nickname</label>
						      <input type="text" class="form-control" id="nickname" placeholder="Nickname" required name="nickname">
						      <div id="ajaxNickname" style="font-size: small;"></div>
						    </div>
						    <div class="col-md-4 mb-3">
						      <label for="nombre" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Nombre</label>
						      <input type="text" class="form-control" id="nombre" placeholder="Nombre" required name="nombre">
						      <!--
						      <div class="valid-feedback">
						        Looks good!
						      </div>
						       -->
						    </div>
						    <div class="col-md-4 mb-3">
						      <label for="apellido" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Apellido</label>
						      <input type="text" class="form-control" id="apellido" placeholder="Apellido" required name="apellido">
						    </div>
							<div class="col-md-4 mb-3">
								<label for="password" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Contrasenia</label>
							    <input type="password" class="form-control" id="password" placeholder="Validar Contrasenia" required name="Password">
							</div>
							<div class="col-md-4 mb-3">
							    <label for="validPass" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Validar contrasenia</label>
							    <input type="password" class="form-control" id="validPass" placeholder="Contrasenia" required name="validPass">
							</div>
							<div id="verifPass" class="ml-2" style="margin-top: 40px;"></div>
						  </div>
						  <div class="form-row">
						  	<div class="col-md-4">
						      <label for="email" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Email</label>
						      <div class="input-group">
						          <div class="input-group-prepend">
						              <span class="input-group-text" id="inputGroupPrepend">@</span>
						          </div>
						          <input type="text" class="form-control" id="email" placeholder="Email" aria-describedby="inputGroupPrepend" required name="email">
						      </div>
						      <div id="ajaxEmail" style="font-size: small;"></div>
						    </div>
						    <div class="col-md-3 mb-3">
								<label for="fechanac" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >F. de nacimiento</label>
						      <input type="date" class="form-control" id="fechanac" placeholder="F. de nacimiento" required name="fechanac">
						      <!--
						      <div class="invalid-feedback">
						        Please provide a valid state.
						      </div>
						       -->
						    </div>
						    <div class="col-md-4 mb-3">
						      <label for="urlimg" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >url imagen</label>
						      <input type="text" class="form-control" id="urlimg" placeholder="url imagen" name="urlimg">
						      <!--
						      <div class="valid-feedback">
						        Looks good!
						      </div>
						       -->
						    </div>
						    <div class="col-md-6 mb-3">
						      <label for="descripcion" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Descripcion</label>
						      <textarea class="form-control" id="descripcion" placeholder="Descripcion" required  name="descripcion"></textarea>
						      <!-- 
						      <div class="invalid-feedback">
						        Please provide a valid city.
						      </div>
						       -->
						    </div>
							<div class="col-md-6 mb-3">
						      <label for="biografia" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Biografia</label>
						      <textarea class="form-control" id="biografia" placeholder="Biografia" name="biografia"></textarea>
						    </div>
						    <div class="col-md-6 mb-3">
						      <label for="web" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Web</label>
						      <textarea class="form-control" id="web" placeholder="Web" name="web"></textarea>
						    </div>
						    <!-- En caso de que fuera necesario un file este es el codigo 
						    <div class="input-group mb-3">
							  <div class="custom-file">
							    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
							    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
							  </div>
							</div>
							-->
						  </div>
						  <button class="btn btn-primary" type="submit" href="/home" style="border-radius: 10px; background: #BD255E; color: #FFFFFF; font-size: 14px; margin-bottom: 2%;">Registrar artista</button>
						</form>
					</div>
				</div>
			</div>
		<% } %>
		<% if (request.getAttribute("tipo") != null && request.getAttribute("tipo").equals("espectador")) {%>
			<div class="center  mt-2">
				<div class="parent">
					<div class="titulo-inicial"><h1>Registro d<span>e Espectador</span></h1></div>
					<div class="datos-registro" class="center-div"  style = "background: #c2c2c2;" >
					<% if (request.getAttribute("errorpass") != null) {%>
						<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=request.getAttribute("errorpass") %></div>
					<% } %>
					<% if (request.getAttribute("errorusuario") != null) {%>
						<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=request.getAttribute("errorusuario") %></div>
					<% } %>
						<form class="needs-validation" style="margin-right: 10px; margin-left: 10px;" novalidate >
						  	<div class="form-row">
						  		<div class="col-md-4">
						      		<label for="nickname" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Nickname</label>
						      		<input type="text" class="form-control" id="nickname" placeholder="Nickname" required name="nickname">
						      		<div id="ajaxNickname" style="font-size: small;"></div>
						    	</div>
							    <div class="col-md-4 mb-3">
							      <label for="nombre" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Nombre</label>
							      <input type="text" class="form-control" id="nombre" placeholder="Nombre" required name="nombre">
							     
							    </div>
							    <div class="col-md-4 mb-3">
							      <label for="apellido" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Apellido</label>
							      <input type="text" class="form-control" id="apellido" placeholder="Apellido" required name="apellido">
							    </div>
							    <div class="col-md-4 mb-3">
							      <label for="password" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Contrasenia</label>
							      <input type="password" class="form-control" id="password" placeholder="Contrasenia" required name="Password">
							    </div>
							    <div class="col-md-4 mb-3">
							      <label for="validPass" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Validar contrasenia</label>
							      <input type="password" class="form-control" id="validPass" placeholder="Validar Contrasenia" required name="validPass">
							    </div>
							    <div id="verifPass" class="ml-2" style="margin-top: 40px;"></div>
							    
							 </div>
							 <div class="form-row">
							  	<div class="col-md-4">
							     	<label for="email" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >Email</label>
							      	<div class="input-group">
							        	<div class="input-group-prepend">
							          		<span class="input-group-text" id="inputGroupPrepend">@</span>
							        	</div>
							        	<input type="text" class="form-control" id="email" placeholder="Email" aria-describedby="inputGroupPrepend" required name="email">							        
							      	</div>
							      	<div id="ajaxEmail" style="font-size: small;"></div>
							    </div>
							    <div class="col-md-3 mb-3">
									<label for="fechanac" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >F. de nacimiento</label>
							      	<input type="date" class="form-control" id="fechanac" placeholder="F. de nacimiento" required name="fechanac">
							     
							    </div>
							    <div class="col-md-4 mb-3">
							      	<label for="urlimg" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;" >url imagen</label>
							      	<input type="text" class="form-control" id="urlimg" placeholder="url imagen" name="urlimg">
							      <!--
							      <div class="valid-feedback">
							        Looks good!
							      </div>
							       -->
							    </div>
							</div>
						  	<button class="btn btn-primary" type="submit" style="border-radius: 10px; background: #BD255E; color: #FFFFFF; font-size: 14px; margin-bottom: 2%;" formmethod="post">Registrar espectador</button>
						</form>
					</div>
				</div>
			</div>
		<% } %>
	<!-- Validacion para el formulario -->
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }
		        form.classList.add('was-validated');
		      }, false);
		    });
		  }, false);
		})();	
	</script>
	
	<!-- Validacion del nickname usando AJAX -->
	<script>
		$(document).ready(
			function() {
		        $('#nickname').blur(
		        	function() {
		                var nick = $('#nickname').val();
		                $.get('VerificarDatos', { nickname : nick }, 
		               		function(respuesta) {
		                       	$('#ajaxNickname').html(respuesta);
		               		}
		                );
		        	}
		        );
			}
		);
	</script>
	
	<!-- Validacion del mail usando AJAX -->
	<script>
		$(document).ready(
			function() {
		        $('#email').blur(
		        	function() {
		                var mail = $('#email').val();
		                $.get('VerificarDatos', { mail : mail }, 
		               		function(respuesta) {
		                       	$('#ajaxEmail').html(respuesta);
		               		}
		                );
		        	}
		        );
			}
		);
	</script>
	
	<!-- Script para comparar contraseñas al vuelo -->
	<script>
		function verificarPass() {
			var pass1 = $('#validPass').val();
			var pass2 = $('#password').val();
	        if (pass1.localeCompare(pass2) === 0) {
	           	$('#verifPass').html("<p style='color: green;'>✔ La password coincide</p>");
	        } else {
	        	$('#verifPass').html("<p style='color: red;'>✗ La password no coincide</p>");
	        }
		}
		$(document).ready(
			function() {
		        $('#validPass').blur(verificarPass);
		        $('#password').blur(verificarPass);
			}
		);
	</script>
	
	<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	</body> 
</html>