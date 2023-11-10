<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="views/miCss.css">
<title>Formulario Modifica Empleado</title>
</head>
<body>

	<h1>Formulario de modificaci&oacute;n</h1>
	<p><%=request.getAttribute("mensaje")%></p>
	<form action="NominasController" method="post">
		<table border="1">
			<tr>
				<td>Nombre del empleado:</td>
				<td><input type="text" name="nombre" size="50"></td>
				<td>Categor&iacute;a del empleado:</td>
				<td><input type="text" name="categoria" size="50"></td>
			</tr>
			<tr>
				<td>DNI del empleado:</td>
				<td><input type="text" name="dni" size="50"></td>
				<td>A&ntilde;os trabajados del empleado:</td>
				<td><input type="text" name="anyos_trabajados" size="50"></td>
			</tr>
			<tr>
				<td>Sexo del empleado:</td>
				<td><input type="text" name="sexo" size="50"></td>
			</tr>
		</table>
		<input type="submit" name="opcion" value="mostrar">
	</form>
	<a href="NominasController?opcion=menu">Volver al men&uacute;</a>
</body>
</html>