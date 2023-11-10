<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/miCss.css">
<title>Empresa</title>
</head>
<body>
	<table>
		<tr>
			<td><h1>MEN&Uacute;</h1></td>
		</tr>
		<tr>
			<td><a href="NominasController?opcion=muestraEmpleados">Mostrar los datos de los empleados.</a></td>
		</tr>
		<tr>
			<td><a href="NominasController?opcion=muestraSalario">Mostrar el salario de un empleado.</a></td>
		</tr>
		<tr>
			<td><a href="NominasController?opcion=modificaEmpleado">Modificar los datos de un empleado.</a></td>
		</tr>
		<tr>
			<td><a href="NominasController?opcion=creaEmpleado">Crear un empleado.</a></td>
		</tr>
	</table>

</body>
</html>