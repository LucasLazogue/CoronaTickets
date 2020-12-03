<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>


     
	<head>
		<meta charset="ISO-8859-1">
		<title> Alta Espectaculo</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		
		<link rel="stylesheet" href="./css/EstiloAltaFuncionEspectaculo.css">
		 	  
	</head>

<body class="todo">


<jsp:include page="./Menu.jsp" />	
<jsp:include page="./cargarHistorial.jsp" />   
<div class="cuerpo">
<form>




  <div class="titulo"><h1>Alta <span>Espectaculo</span></h1></div>
  <% if(request.getAttribute("error") != null){%>
 	<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%=(String)request.getAttribute("error")%></div>
  <%} %>
  <div class="form-group">
    <div class="form-group">
  
      <label for="exampleFormControlSelect1">Seleccionar Plataforma</label>
      <select name="plataforma" class="form-control" id="plataforma">  
      	  <% net.java.dev.jaxb.array.StringArray plat = (net.java.dev.jaxb.array.StringArray)request.getAttribute("plataformas"); %>   	  
          <%for (int i = 0; i < plat.getItem().size(); i++){%>
          <option><%= plat.getItem().get(i) %></option>
          <%}%>	    
      </select>
    </div>
  
  
  
  <div class="form-group">
    <label for="exampleFormControlSelect2">Selecionar Categoria</label>
    <select multiple class="form-control" id="categoria" name= "categoria" >
      	  <% net.java.dev.jaxb.array.StringArray cat = (net.java.dev.jaxb.array.StringArray)request.getAttribute("categorias"); %>   	  
          <%for (int i = 0; i < plat.getItem().size(); i++){%>
          <option><%= cat.getItem().get(i) %></option>
          <%}%>	  
    </select>
  </div>
    
    
  
  <div class="form-group">    
    <input id="usrForm" type="text" class="form-control" id="formGroupExampleInput" placeholder="NombreEspectaculo" name="nombre">
  </div>
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Duracion" name="duracion">
  </div>
    <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Costo" name="costo">
  </div>
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Url Del Espectaculo" name="urlesp">
  </div>

  
  <div class="row">
    <div class="col">
      <input type="text" class="form-control" placeholder="Cantidad minima de espectadores" name="cantmiesp">
    </div>
    <div class="col">
      <input type="text" class="form-control" placeholder="Cantidad maxima de espectadores" name="cantmaesp">
    </div>
  </div> 
  

  <div class="form-group">    
    <input name="descripcion" class="form-control" id="descripcion"  placeholder="Descripcion" >
  </div>  
  
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Url Portada" name="urlport">
  </div>
  
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Desc. Premio" name="premio">
  </div>
  
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Cant. Premio" name="cantpremio">
  </div>
  
  <div class="form-group">    
    <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Codigo de video (YouTube)" name="urlvideo">
  </div>
  

  <input id="Alta" type="submit" class="btn btn-primary" onclick="submit()" value= "Submit" formmethod= "post"  >
</div>

</form>
</div>

			

</body>
</html>