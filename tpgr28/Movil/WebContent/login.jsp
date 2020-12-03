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
	<body class="text-center log-container">
      <form class="form-signin" style='background-color: white'>
        <div class="header">
		  <h1>Corona<span>Tickets</span></h1>				
	    </div>
	    <label for="inputUsername" class="sr-only">Nombre de usuario o mail</label>
	    <input type="text" id="inputUsername" class="form-control" placeholder="Nombre de usuario o mail" name='login' value='<%= request.getSession().getAttribute("nomUsuario") != null? request.getSession().getAttribute("nomUsuario") : "" %>' required autofocus>
	    <label for="inputPassword" class="sr-only">Contraseña</label>
	    <input type="password" id="inputPassword" class="form-control" placeholder="Contraseña" name='password' value='<%= request.getSession().getAttribute("password") != null? request.getSession().getAttribute("password") : ""%>' required>
	    <div class="checkbox mb-3">
	      <label>
	        <input type="checkbox" value="true" name='recordarme' <%= request.getSession().getAttribute("nomUsuario") != null? "checked" : "" %>> Recordarme
	      </label>
	    </div>
	    <button class="btn btn-lg btn-primary btn-block" type="submit" formmethod="post">Iniciar Sesión</button>
		<% if(request.getAttribute("error") == "usuario"){%>
			  <div class="alert alert-primary" style="margin-top: 2%" role="alert">No hay usuario con ese nickname o email</div>
		<%} %>	
		<% if(request.getAttribute("error") == "password"){%>
		  	<div class="alert alert-primary" style="margin-top: 2%" role="alert">La contraseña es incorrecta</div>
		<%} %>
		<% if(request.getAttribute("error") == "artista"){%>
		  	<div class="alert alert-primary" style="margin-top: 2%" role="alert">No es posible iniciar sesión como artista desde el movil</div>
		<%} %>	
	  </form>
	  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	</body>
</html>