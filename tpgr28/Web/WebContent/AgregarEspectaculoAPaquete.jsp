<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Agregar Espectaculo a Paquete</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="./css/EstilosEspectaculoAPaquete.css">
		<%@ page import="servidor.DataFuncion" %>
		<%@ page import="java.util.Map" %>
		<%@ page import="java.util.Map.Entry" %>
		<%@ page import="java.util.*" %>
				<jsp:include page="./Menu.jsp" />  
		
		
	</head> 
	<body>
		<jsp:include page="./cargarHistorial.jsp" />   
		<div class = ppal>
			<div class=principal>
				<div class=header>
					<%
						String paquete = (String)request.getAttribute("paquete");
						out.print("<h3>" + paquete + "</h3>");
					%>   
				</div>
				<div class=datos>
					<div class=header-datos>
						<h3>INGRESAR DATOS DE ESPECTACULO</h3>
					</div>
					<form class="paq-form" id="paq-form" method="post">
						<div class=plataforma-container>
							<label for="plataformaAgregar" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Seleccionar plataforma: </label>
							<select name="plataformaAgregar" id="plataformaAgregar" class=plataforma onchange="actualizarPlataformas()">
		  						<%
		  						Map<String, List<String>> listaPlataformasEspectaculos =  (Map<String, List<String>>)request.getAttribute("platEspecs");
		  						
		  						out.println("<option value=- id=->-</option>");
		  						
		  						for(Entry<String, List<String>> entry :listaPlataformasEspectaculos.entrySet()){
		  	  						List<String> lstEspec = entry.getValue();	
		  	  						if(!lstEspec.isEmpty()){
		  	  							out.print("<option value='"+entry.getKey()+"'");
		  	  							out.print("id="); 
		  	  							for(int i = 0; i < lstEspec.size(); i++){
		  	  								if(lstEspec.size() == 1){
		  	  									out.print("'"+lstEspec.get(i)+"'");
		  	  								}
		  	  								else{
		  	  									if(i == 0){
		  	  										out.print("'");
		  	  									}  	  									
		  	  	  								if(i != lstEspec.size() - 1)
		  	  	  									out.print(""+lstEspec.get(i)+"|");
		  	  	  								else
		  	  	  									out.print(""+lstEspec.get(i)+"'");
		  	  								}
		  	  							}
		  	  						out.println(">"+entry.getKey()+"</option>");
		  	  						}
		  						}  						
		  						%>
							</select>
						</div>  
						<div class=espectaculo-container>
							<label for="espectaculoAgregar" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Seleccionar espectaculo: </label>
							<select name="espectaculoAgregar" id="espectaculoAgregar" class=espectaculo>
							</select>
						</div>
						<div class=aaa>
						 	<button name="btnAdd" id="btnReg" type="submit" class="btn-primary btnAdd" form="paq-form" style="  border: none; border-radius: 10px; width: 90%; height: 40px; background: #BD255E; color: #FFFFFF; font-size: 14px; margin-left: 10px; margin-right: 10px; margin-bottom: 20px; position: absolute; bottom: 0px; display: block;">AGREGAR</button><br>
						 	
						</div>  
					</form>
				</div>
			</div>	
		</div>
		
		<script>
			function actualizarPlataformas(){
				var plataforma = document.getElementById('plataformaAgregar');
				var nomPlataforma = plataforma.value;
				var espectaculo = document.getElementById('espectaculoAgregar');
								
				var c = (plataforma[plataforma.selectedIndex].id.split('|'));
				
				var len = espectaculo.length;
				for (var i = 0; i < len; i++) {
					espectaculo.remove(0);
				}
			
				
				if(nomPlataforma != "-"){
					if(c.length != 1){
						for (i = 0; i < c.length; i++) {
					        var option = document.createElement('option');
					        option.text = option.value = c[i];
					        espectaculo.add(option, 0);
						}
					}
					else{
				        var option = document.createElement('option');
				        option.text = option.value = plataforma[plataforma.selectedIndex].id;
				        espectaculo.add(option, 0);
					}
						
				}
			}
		
		</script>
		
	</body>
</html>