<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>


     
	<head>
		<meta charset="UTF-8">
		<title> Alta Funcion Espectaculo</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="./css/EstiloAltaFuncionEspectaculo.css">
				  
	</head>
	

<body class="todo">
<jsp:include page="./Menu.jsp" />	
<jsp:include page="./cargarHistorial.jsp" />   

<div class="cuerpo">
<form>
	
  <div class="titulo"><h1>Alta <span>Funcion</span></h1></div>
  <% if(request.getAttribute("error") != null){%>
 	<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=(String)request.getAttribute("error")%></div>
  <%} %>
  <div class="altaCont">
 
  
      <label for="exampleFormControlSelect1">Seleccionar Espectaculo</label>
    <select class="form-control" id="espec" name = "espec">
    	<%String[] espectaculen = (String[])request.getAttribute("espectaculos");  %>
           <%for (int i = 0; i < espectaculen.length; i++){%>
          <option><%= espectaculen[i] %></option>
          <%}%>	  
    </select>
    
  
  
  <div class="form-group">
    <label for="exampleFormControlSelect2">Selecionar Artistas invitados</label>
    <select multiple class="form-control" id="artistasinv" name="artistasinv">
    	<%String[] artistasInv = (String[])request.getAttribute("artistas");  %>
           <%for (int i = 0; i < artistasInv.length; i++){%>
          <option><%= artistasInv[i] %></option>
          <%}%>	 
    </select>
  </div>
    
    
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="NombreFuncion" name="nombref">
  </div>

  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Url Portada" name="urlport">
  </div>

  

  
    <div class="form-group"> 
     <label for="exampleFormControlSelect1">Fecha</label>   
    <input type="date" class="form-control" id="formGroupExampleInput2" placeholder="Fecha y Hora Inicio DD/MM/AAAA HH:MM" name="fechai">
    </div>    

    <div class="form-group">   
     <label for="exampleFormControlSelect1">Hora</label> 
    <input type="time" class="form-control" id="formGroupExampleInput2" placeholder="Fecha y Hora Inicio DD/MM/AAAA HH:MM" name="tiempo">
    </div>    

  
   <input id="Alta" type="submit" class="btn btn-primary" onclick="submit()" value= "Submit" formmethod= "post"  >
    

 
</div>
</form>
</div>
</body>

</html>