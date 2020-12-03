<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>


     
	<head>
		<meta charset="ISO-8859-1">
		<title> Alta Paquete</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstiloAltaFuncionEspectaculo.css">
				  
	</head>

	
<body class="todo">
<jsp:include page="./Menu.jsp" />	
<jsp:include page="./cargarHistorial.jsp" />   
	

<div class="cuerpo">
<form>

  <div class="titulo"><h1>Alta <span>Paquete</span></h1></div>
   <% if(request.getAttribute("error") != null){%>
 	<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=(String)request.getAttribute("error")%></div>
  <%} %>
  <div class="altaCont">
  <div class="form-group">
  
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput" placeholder="NombrePaquete" name="nombrep">
  </div>
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Descripcion" name="descripcion">
  </div>
    <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Descuento" name="descuento">
  </div>
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Url de Portada" name="urlport">
  </div>
  
      
    
   
    <div class="form-group">   
     <label for="exampleFormControlSelect1">Valido desde</label> 
    <input type="date" class="form-control" id="formGroupExampleInput2" placeholder="Valido desde DD/MM/AAAA" name="fechad">
    </div>
    
    <div class="form-group">   
     <label for="exampleFormControlSelect1">Valido hasta</label> 
    <input type="date" class="form-control" id="formGroupExampleInput2" placeholder="Valido hasta DD/MM/AAAA" name="fechah">
    </div> 
    

  <input id="Alta" type="submit" class="btn btn-primary" onclick="submit()" value= "Submit" formmethod= "post"  >
</div>
</div>
</form>
</div>



<body>
</html>