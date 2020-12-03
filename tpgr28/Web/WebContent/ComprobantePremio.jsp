<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Comprobante de Premio</title>

	</head>
	<body>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.debug.js"></script>
		<%@ page import="javax.xml.datatype.XMLGregorianCalendar" %>

		<button onClick="printToPDF();">
  			Generar PDF
		</button>
		
		<%
		String descPremio = (String)request.getAttribute("descPremio");
		XMLGregorianCalendar fechaDesde = (XMLGregorianCalendar) request.getAttribute("fechaDesde");
		String nomEspec = (String)request.getAttribute("nomEspec");
		String nomFunc = (String)request.getAttribute("nomFunc");
		String ganador = (String)request.getAttribute("ganador");

		%>

		<div id="printable">
  			<div class=encabezado>
				<h2>Comprobante de Premio</h2>
			</div>
			<div class=datosPremio>
				<h3>Premio: </h3>
				<h4><%=descPremio%></h4><br>
				<h3>Ganador: </h3>
				<h4><%=ganador%></h4><br>
				<h3>valido para canjear desde:</h3>
				<h4><%=fechaDesde.toGregorianCalendar().getTime()%></h4>
				<h5>Valido por 30 dias</h5>				
				<h3>Espectaculo: </h3>
				<h4><%=nomEspec%></h4><br>
				<h3>Funcion: </h3>
				<h4><%=nomFunc%></h4>
			</div>
		</div>

		<script>
		function printToPDF() {
			  console.log('converting...');

			  var printableArea = document.getElementById('printable');

			  html2canvas(printableArea, {
			    useCORS: true,
			    onrendered: function(canvas) {

			      var pdf = new jsPDF('p', 'pt', 'letter');

			      var pageHeight = 980;
			      var pageWidth = 900;
			      for (var i = 0; i <= printableArea.clientHeight / pageHeight; i++) {
			        var srcImg = canvas;
			        var sX = 0;
			        var sY = pageHeight * i; // start 1 pageHeight down for every new page
			        var sWidth = pageWidth;
			        var sHeight = pageHeight;
			        var dX = 0;
			        var dY = 0;
			        var dWidth = pageWidth;
			        var dHeight = pageHeight;

			        window.onePageCanvas = document.createElement("canvas");
			        onePageCanvas.setAttribute('width', pageWidth);
			        onePageCanvas.setAttribute('height', pageHeight);
			        var ctx = onePageCanvas.getContext('2d');
			        ctx.drawImage(srcImg, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight);

			        var canvasDataURL = onePageCanvas.toDataURL("image/png", 1.0);
			        var width = onePageCanvas.width;
			        var height = onePageCanvas.clientHeight;

			        if (i > 0) // if we're on anything other than the first page, add another page
			          pdf.addPage(612, 791); // 8.5" x 11" in pts (inches*72)

			        pdf.setPage(i + 1); // now we declare that we're working on that page
			        pdf.addImage(canvasDataURL, 'PNG', 20, 40, (width * .62), (height * .62)); // add content to the page

			      }
			      pdf.save('Comprobante.pdf');
			    }
			  });
			}
		</script>

	</body>
</html>