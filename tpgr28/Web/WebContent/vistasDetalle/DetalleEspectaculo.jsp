<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalle Espectaculo</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EstilosDetalle.css">
		<link rel="stylesheet" href="../css/EstilosMenu.css">
		<%@ page import="servidor.DataEspectaculo" %>
		<%@ page import="servidor.DataUsuario" %>
		<%@ page import="servidor.DataEspectador" %>
		<%@ page import="servidor.DataArtista" %>
		<%@ page import="servidor.DataPuntaje" %>
		<%@ page import="java.util.List" %>
		<%@ page import="java.lang.Double" %>
	</head>
	<body>
		<jsp:include page="../Menu.jsp" />
		<jsp:include page="../cargarHistorial.jsp" />   
		<div class="cuerpo">
			<% DataEspectaculo espectaculo = (DataEspectaculo)request.getAttribute("espectaculoData"); %>
			<% DataUsuario user = (DataUsuario)request.getSession().getAttribute("usuario"); %>
			<% String plataforma = (String)request.getAttribute("plataforma"); %>
			<% double totalVal = (double)request.getAttribute("totalVal"); %>
			<% double promedioVal = (double)request.getAttribute("promedioVal"); %>
			<% int[] valoraciones = (int[])request.getAttribute("valoraciones"); %>
			<% DataPuntaje valoracionEspectador = (DataPuntaje)request.getAttribute("valoracionEspectador"); %>
			<% boolean habilitadoVal = (boolean)request.getAttribute("habilitadoVal"); %>
			<div class=bg-image>
				<% if(request.getAttribute("errorFinalizar") != null){%>
		  			<div class="alert alert-primary" style="margin-top: 2%" role="alert"><%= (String)request.getAttribute("errorFinalizar") %></div>
				<%} %>	
				<div class="titulo texto">
					<div class="centrar" style=" display: flex; justify-content: center; flex-direction: row;">
						<h1><%= espectaculo.getNombre() %></h1>
						<% if (user instanceof DataEspectador) {%>
			      			<a class="btn btn-outline-warning btn-lg" style="border: none; background: none; box-shadow: none;" href="./DetalleEspectaculo?espectaculo=<%= espectaculo.getNombre() %>&favorito=<%= espectaculo.getNombre()%>">
			      				<% if (!((DataEspectador)user).getEspectaculosFavoritos().contains(espectaculo.getNombre())) {%>
					      			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill iconFavorito" fill="grey" xmlns="http://www.w3.org/2000/svg">
										<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
									</svg>
								<%} else {%>
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill iconNoFavorito" fill="yellow" xmlns="http://www.w3.org/2000/svg">
										<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
									</svg>
								<%} %>
							</a>
							<div class="card mt-1" style="width: 3rem; color: black; text-align: center"><%= espectaculo.getCantVecesEspectaculoFavorito() %></div>
						<%} %>
					</div>
				</div>
				<div class=center-block>
					<img src="<%= espectaculo.getUrlImg() %>" class="img-responsive" alt="Foto de Espectaculo No Disponible">
				</div>
				<div class="texto">
					<div class="row mr-4">
						<div class="col-xs-12 col-md-6 text-center mt-4">
	                        <h4>Promedio</h4>
	                        <h2><%=Double.toString(promedioVal).substring(0, 3)%></h2>
	                        <h4><%if(promedioVal < 0.5) {%>
							  	☆☆☆☆☆
							  <%} else if (promedioVal < 1.5) {%>
							  	★☆☆☆☆
							  <%} else if (promedioVal < 2.5) {%>
							  	★★☆☆☆
							  <%} else if (promedioVal < 3.5) {%>
							  	★★★☆☆
							  <%} else if (promedioVal < 4.5) {%>
							  	★★★★☆
							  <%} else {%>
							  	★★★★★
							  <%} %>
							</h4>
	                        <div class='mb-2'><%=(int)totalVal%> votos </div>
	                        <% if(habilitadoVal){ %>
	                        	<% if(valoracionEspectador!=null && valoracionEspectador.getPuntaje() > 0){ %>
	                        	<h4>Mi Calificacion: <%=valoracionEspectador.getPuntaje()%></h4>
	                        	<% }else{ %>
	                        		<h5>Sin Calificar</h5>
	                        	<% } %>
	                        	<form>
	                        	<select name="puntaje">
	                        		<option value=1>1 estrella</option>
	                        		<option value=2>2 estrellas</option>
	                        		<option value=3>3 estrellas</option>
	                        		<option value=4>4 estrellas</option>
	                        		<option value=5>5 estrellas</option>
	                        	</select>
	                        	<h4><input class="btn btn-success" type="submit" onClick="submit()" value="Valorar" formmethod= "post"></h4>
	                        	</form>
	                        <% } %>
	                    </div>
	                    <div class="col-xs-12 col-md-6 mt-4">
	                    	<div class="row rating-desc">
	                    		<% if(totalVal>0){ %>
	                    		<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>5
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: <%= valoraciones[4]/totalVal*100 %>%">
                                        	<span class="sr-only"><%= valoraciones[4]/totalVal*100 %>%</span> <%= valoraciones[4] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin5 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>4
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: <%= valoraciones[3]/totalVal*100 %>%">
                                        	<span class="sr-only"><%= valoraciones[3]/totalVal*100 %>%</span> <%= valoraciones[3] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin4 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>3
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: <%= valoraciones[2]/totalVal*100 %>%">
                                        	<span class="sr-only"><%= valoraciones[2]/totalVal*100 %>%</span> <%= valoraciones[2] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin3 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>2
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: <%= valoraciones[1]/totalVal*100 %>%">
                                        	<span class="sr-only"><%= valoraciones[1]/totalVal*100 %>%</span> <%= valoraciones[1] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin2 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>1
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: <%= valoraciones[0]/totalVal*100 %>%">
                                        	<span class="sr-only"><%= valoraciones[0]/totalVal*100 %>%</span> <%= valoraciones[0] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin1 -->
                            	<% }else{ %>
                            		<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>5
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                        	<span class="sr-only">0%</span> <%= valoraciones[4] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin5 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>4
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                        	<span class="sr-only">0%</span> <%= valoraciones[3] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin4 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>3
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                        	<span class="sr-only">0%</span> <%= valoraciones[2] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin3 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>2
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                        	<span class="sr-only">0%</span> <%= valoraciones[1] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin2 -->
                            	<div class="col-xs-3 col-md-3 text-right">
                                	<span class="glyphicon glyphicon-star"></span>1
                            	</div>
                            	<div class="col-xs-8 col-md-9">
                                	<div class="progress">
                                    	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                        	aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                        	<span class="sr-only">0%</span> <%= valoraciones[0] %>
                                    	</div>
                                	</div>
                            	</div>
                            	<!-- fin1 -->
                            	<% } %>
	                    	</div>
	                    </div>
					</div>
					<div class="row">
					<div class="<%=	espectaculo.getUrlVideo() == null || espectaculo.getUrlVideo().equals("")? "" : "col-xs-12 col-md-7" %> ml-4 mr-4">
						<h2>Detalles:</h2>
						<h4>Descripcion:</h4>
						<h5><%= espectaculo.getDescripcion() %></h5>
						<h4>Estado:</h4>
						<h5><%= espectaculo.getEstado() %></h5>
						<h4>Duracion:</h4>
						<h5><%= espectaculo.getDuracion() %></h5>
						<h4>Fecha de Alta:</h4>
						<h5><%= espectaculo.getFechaRegistro().toGregorianCalendar().getTime() %></h5>
						<h4>Plataforma:</h4>
						<% if(plataforma!=null){ %>
							<h5><%= plataforma %></h5>
						<% }else{ %>
							<h5>No se especifica plataforma.</h5>
						<% } %>
						<h4>Categorias:</h4>
						<% List<String> categorias = espectaculo.getNombresCategorias(); %>
						<% if(categorias!=null){ %>
							<h5>
							<% for(int i=0; i<categorias.size()-1; i++){ %>
								<%= categorias.get(i) %>,
							<% } %>
							<% if(categorias.size()!=0){ %>
								<%= categorias.get(categorias.size()-1) %>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No se registraron categorias.</h5>
						<% } %>
						
						<h4>Premio:</h4>
						<% if (espectaculo.getPremio().getCantPremiosPorFuncion() != 0) { %>
							<h5><%= espectaculo.getPremio().getDescripcion() %></h5>
							<h4>Cantidad de premios a sortear:</h4>
							<h5><%= espectaculo.getPremio().getCantPremiosPorFuncion() %></h5>
						<% } else { %>
							<h5>El espectaculo no cuenta con premio</h5>
						<% } %>
	
						
						<h4>Funciones:</h4>
						<% List<String> funciones = espectaculo.getNombresFunciones(); %>
						<% if(funciones!=null){ %>
							<h5>
							<% for(int i=0; i<funciones.size()-1; i++){ %>
								<a href="${pageContext.request.contextPath}/DetalleFuncion?espectaculo=<%= espectaculo.getNombre() %>&funcion=<%= funciones.get(i) %>"><%= funciones.get(i) %></a>,
							<% } %>
							<% if(funciones.size()!=0){ %>
								<a href="${pageContext.request.contextPath}/DetalleFuncion?espectaculo=<%= espectaculo.getNombre() %>&funcion=<%= funciones.get(funciones.size()-1) %>"><%= funciones.get(funciones.size()-1) %></a>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No hay funciones disponibles.</h5>
						<% } %>
						<h4>Paquetes:</h4>
						<% List<String> paquetes = espectaculo.getNombresPaquetes(); %>
						<% if(paquetes!=null){ %>
							<h5>
							<% for(int i=0; i<paquetes.size()-1; i++){ %>
								<a href="${pageContext.request.contextPath}/DetallePaquete?paquete=<%= paquetes.get(i) %>"><%= paquetes.get(i) %></a>,
							<% } %>
							<% if(paquetes.size()!=0){ %>
								<a href="${pageContext.request.contextPath}/DetallePaquete?paquete=<%= paquetes.get(paquetes.size()-1) %>"><%= paquetes.get(paquetes.size()-1) %></a>
							<% } %>
							</h5>
						<% }else{ %>
							<h5>No se ha registrado este espectaculo en ningun paquete</h5>
						<% } %>
						<h4>Precio:</h4>
						<h5>$<%= espectaculo.getCosto() %></h5>
						<% Boolean creaEspec = (Boolean)request.getAttribute("creaEspectaculo"); %>							
						<% if (user instanceof DataArtista && !espectaculo.getEstado().equals("Finalizado") && creaEspec){ %>
							<form class="registro-form" id="reg-form" method="post">	
								<input type='hidden' id='appt' name='nomEspectaculo' value= <% out.print("'" + espectaculo.getNombre() + "'"); %>>				
								<button name="confirmar" id="btnReg" type="submit" class="btn btn-danger" style="left:50%;">Finalizar Espectaculo</button><br>		
							</form>					
						<% } %>
					</div>
					<% if (espectaculo.getUrlVideo() != null && !espectaculo.getUrlVideo().equals("")) {%>
					<div class="col-xs-12 col-md-4">
						<iframe width="500" height="320" src=<%="https://www.youtube.com/embed/" + espectaculo.getUrlVideo()%>>
						</iframe>
					</div>
					<%} %>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>