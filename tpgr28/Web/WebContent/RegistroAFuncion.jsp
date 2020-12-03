<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registro a funcion</title>
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="./css/EstilosRegistroAFuncion.css">	
		

		
  
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataEspectador" %>	
		<%@ page import="servidor.DataFuncion" %>
		<%@ page import="servidor.DataRegistro" %>
		<%@ page import="java.util.*" %>

	</head>
	<body> 
		<jsp:include page="./Menu.jsp" />   
		<jsp:include page="./cargarHistorial.jsp" />   

		<div class=parent>	
		
			<div class=compra-container>
			       <% if(request.getAttribute("error") != null) {%>
<div tabindex="-1" id="modal1" class="bootbox modal show in" role="dialog" style="display: block;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="color:#000000">Error</h4>
                <button class="bootbox-close-button close" aria-hidden="true" type="button" data-dismiss="modal" onclick='cerrarModal();'>�</button>
            </div>
            <div class="modal-body">
            <%
            String error = (String) request.getAttribute("error");
            out.print("<div class='bootbox-body' style='color:#000000'>" + error + "</div>");
            
            %>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal" data-bb-handler="confirm" onclick='cerrarModal();'><i class="fa fa-check"></i> Aceptar</button>
            </div>
        </div>
    </div>
</div>
  <%} %>
				<div class=header-compra>
					<div class=detalleEspec>
						<div class=infoEspec>
							<%
								String img = (String)request.getAttribute("urlImg");
								out.print("<div class=img><img src="+ img + "height='120' width='170 alt='Foto de Funcion No Disponible'></div>");
							%>
							<div class=espectaculo-funcion>
								<%

									String espectaculo = (String)request.getAttribute("espectaculo");
									String funcion = (String)request.getAttribute("funcion");
									out.print("<h3>" + espectaculo + "</h3>");
									out.print("<h4>" + funcion + "</h4>");
								%>   
							</div>   
						</div>     
						<div class=precio>
							<%
								String costo = (String)request.getAttribute("costo");
								out.print("<p>" + costo + "</p>");
							%>            
						</div>                                     
					</div>    
				</div>           
				<div class=datos-compra>
					<div class=opcionalesCompra>
						<div class=header-opcionales>
							<h3>CANJEO DE PAQUETES O REGISTROS</h3>                
						</div>
						
						<form class="registro-form" id="reg-form" method="post">	
							<%
								String plataforma = (String)request.getAttribute("plataforma");
								out.println("<input type='hidden' id='appt' name='plataforma' value='" + plataforma + "'>");
								out.println("<input type='hidden' id='appt' name='espectaculo' value='" + espectaculo + "'>");
								out.println("<input type='hidden' id='appt' name='funcion' value='" + funcion + "'>");

							%>
							<div class=parent-datos>
							
								<div class=fechaRegistro>
									<label for="fechaReg" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Fecha registro (Obligatorio)</label>
									<input type="date" id="appt" name="fechaReg">
								</div>						
							
								<div class=horaRegistro>
									<label for="horaReg" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Hora registro (Obligatorio)</label>
									<input type="time" id="appt" name="horaReg">
								</div>
							
								<div class=paquete-container>
									<label for="paquetes" style="color:#5B5B5B; margin-right: 10px; margin-left: 10px;">Canjear paquete (Opcional)</label>
			  						<select name="paquetes" id="selectOpt" class=paquetes onchange="actualizarPaquetes()">
			  						<%
			  						List<Integer> listaDescuentos = (List<Integer>)request.getAttribute("listaDescuentos");
			  						List<String> listaPaquetes = (List<String>)request.getAttribute("listaPaquetes");
			  						Iterator<Integer> i = null;
			  						if(listaDescuentos != null){
			  							i = listaDescuentos.iterator();
			  						}
			  						Integer desc = 0;
			  						if(listaPaquetes != null){
				  						for (String paq : listaPaquetes) {
				  							out.print("<option value='"+paq+"'");
				  							//out.println(">"+paq+"</option>");
				  							if(!(paq.equals("-"))){
				  								if(i.hasNext()){
				  									desc = i.next();
				  								}
				  								out.println("id="+desc+">"+paq+"</option>");
				  							}
				  							else{
				  								out.println("id=0>"+paq+"</option>");
				  							}
				  						}
			  						}
			  						%>
									</select>
								</div>  
												 
								<div class=registros-container>
								
									<h4>Canjear registros (Opcional)</h4>
									<div class=reg-form>
										<%
				  						List<String> listaRegistros = (List<String>)request.getAttribute("listaRegistros");
				  						if(listaRegistros != null){
											for (String reg : listaRegistros) {
					  							out.println("<input type=checkbox name=registros id=registros class=registros value='"+reg+"' onClick='actualizarRegistros();'</option>");		  						
					  							out.println("<label for='"+reg+"' style='color:#5B5B5B; margin-left:10px; font-size:12px;'>"+reg+"</label>");		  						
												out.println("<br>");
					  						}
				  						}
				  						%>							
									</div>
									
								</div>
								
							
							
							</div>
							
						</form>
					</div> 
					<div class=gastosCompra> 
						<label for="subtotal" style="color:#5B5B5B; margin-left: 10px; font-size:14px;">Subtotal: </label><br>		
						<% out.print("<h4 id='subtotal'>" + costo + "</h4>");%>			
						<label for="paqueteCanjeado" style="color:#5B5B5B; margin-left: 10px; font-size:14px;">Paquete canjeado: </label><br>					
				 		<h4 id=paqueteCanjeado>-</h4>
						<label for="canjeaRegistros" style="color:#5B5B5B; margin-left: 10px; font-size:14px;">Canjea registros?: </label><br>					
				 		<h4 id=canjeaRegistros>No</h4>
						<label for="descuento" style="color:#5B5B5B; margin-left: 10px; font-size:14px;">Descuento: </label><br>					
				 		<h4 id=descuento>0</h4>
						<label for="total" style="color:#5B5B5B; margin-left: 10px; font-size:14px;">Total: </label><br>	
						<% out.print("<h4 id='total'>" + costo + "</h4>");%>				
				 		<button name="confirmar" id="btnReg" type="submit" class="btn-primary" form="reg-form" style="border: none;  border-radius: 10px; width: 90%; height: 40px; background: #BD255E; color: #FFFFFF; font-size: 14px; margin-left: 10px; margin-right: 10px; margin-bottom: 20px; position: absolute; bottom: 0px; display: block;">REGISTRARSE</button><br>
					</div>
					
				</div>	
	  
			</div>
		</div>
		<script>
			function actualizarRegistros() {
				var elems =document.getElementsByClassName("registros");
				var subtotal = document.getElementById("subtotal");
				var cant = 0;
				for(var i = 0; i < elems.length; i++){
					if(elems[i].checked)
						cant = cant + 1;
				}
				if(cant == 3){
					document.getElementById("canjeaRegistros").innerHTML = "Si"
					document.getElementById("total").innerHTML = "0"
				}
				else{
					if (cant > 3){	
						alert('Solo se pueden seleccionar 3 registros');
						var chk = false;
						var j = 0;
						while(j < elems.length && chk == false){
							if (elems[j].checked){
								elems[j].checked = false;
								chk = true;
							}
							j = j + 1;
						}
					}
					document.getElementById("canjeaRegistros").innerHTML = "No"
					document.getElementById("total").innerHTML = subtotal.textContent;
				}
			}
			
			function actualizarPaquetes(desc, paq){
				var opt = document.getElementById("selectOpt");
				var paq = document.getElementById("paqueteCanjeado");
				var desc = document.getElementById("descuento");
				var subtotal = document.getElementById("subtotal");
				var total = document.getElementById("total");
				paq.innerHTML = String(opt.options[opt.selectedIndex].value);
				desc.innerHTML = String(opt.options[opt.selectedIndex].id);
				var auxTotal = parseInt(document.getElementById("subtotal").textContent, 10);
				var descuento = parseInt(desc.textContent, 10)
				auxTotal = auxTotal - ((auxTotal * descuento)/100);
				total.innerHTML = String(auxTotal);				
			}
			
      function cerrarModal(){
          var modal = document.getElementById('modal1');
          modal.style.display="none";


        }
</script>
		
		 		
		
	</body> 
</html>