<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Empleado"%>
<%@ page import="model.Nomina"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/miCss.css">
<title>Opción 2 Menú</title>
</head>
<body>
	
	<h1>Mostrar Sueldos</h1>
	<p><%=request.getAttribute("mensaje")%></p>
	<table border="1">
		<tr>
			<td>Nombre</td>
			<td>DNI</td>
			<td>Sueldo</td>
		</tr>
		<tr>
			<td><c:out value="${requestScope.empleadoNombre}" /></td>
			<td><c:out value="${requestScope.empleadoDni}" /></td>
			<td><c:out value="${requestScope.sueldo}" /></td>
		</tr>
	</table>
	<a href="NominasController?opcion=menu">Volver al men&uacute;</a>
</body>
</html>