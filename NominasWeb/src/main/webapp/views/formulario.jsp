<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miCss.css"><link rel="stylesheet" type="text/css" href="views/miCss.css"><title>Formulario DNI</title>
</head>
<body>

	<h1>Formulario</h1>
	<p><%=request.getAttribute("mensaje")%></p>
	<form action="NominasController" method="post">
		<input type="hidden" name="opcion" value="buscar">
		<table border="1">
			<tr>
				<td>DNI del empleado:</td>
				<td><input type="text" name="dni" size="50"></td>
			</tr>
		</table>
		<input type="submit" value="buscar">
	</form>
	<a href="NominasController?opcion=menu">Volver al men&uacute;</a>
</body>
</html>