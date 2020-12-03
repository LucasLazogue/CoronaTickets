<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<script src="https://unpkg.com/bowser@2.7.0/es5.js"></script>
	<script>
		  function getDatos(json) {
	       		var res = bowser.getParser(window.navigator.userAgent);
	       		var browser = res.parsedResult.browser.name;
	       		var os = res.parsedResult.os.name;
	       		var ip = json.ip;
	       		var url = window.location.href.split('?')[0];
	            $.get('CargarAlHistorial', { ip : ip, url : url, browser : browser, os : os});
	       	}
	
				
	</script>
	
	<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
			
	<script type="application/javascript" src="https://api.ipify.org?format=jsonp&callback=getDatos"></script>

</body>
</html>