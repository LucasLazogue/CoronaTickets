<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Iniciar sesión</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="./css/EstilosLogin.css">				  
	</head> 
	<body> 
		<jsp:include page="./cargarHistorial.jsp" />   
		<div class=log-container>
			<div class="header">
				<h1>Corona<span>Tickets</span></h1>				
			</div>
			<div class=formLogin>
				<form class="cod-form">				
					<div class="titulo">
						<h3>.</h3>
                   		<h4>Bienvenido</h4>	
					</div>   
					<input id="usrForm" type="text" class="form-input" placeholder="Nombre de usuario" name="login"><br>
					<input id="usrForm" type="password" class="form-input" placeholder="Contraseña" name="password"><br>
					
				    <div class="ingresar">
				    	<input id="buttonLogin" type="submit" class="form-input" onClick="submit()" formmethod= "post" value="INICIAR SESIÓN">
				    	 <p>No estás registrado?</p>
				        <div class="btn-group">
						  <button type="button" id="registrarse" class="boton btn btn-outline-danger dropdown-toggle mb-sm-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Registrarse
						  </button>
						  <div class="dropdown-menu">
						    <a class="dropdown-item" href="./Registro?tipo=artista">Artista</a>
						    <a class="dropdown-item" href="./Registro?tipo=espectador">Espectador</a>
						  </div>
						</div>
				   </div>
				</form> 
			</div>   
			<% if(request.getAttribute("error") == "usuario"){%>
		  		<div class="alert alert-primary" style="margin-top: 2%" role="alert">No hay usuario con ese nickname</div>
			<%} %>	
			<% if(request.getAttribute("error") == "password"){%>
		  		<div class="alert alert-primary" style="margin-top: 2%" role="alert">La contraseña es incorrecta</div>
			<%} %>	    	 			
		</div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	</body>
</html>