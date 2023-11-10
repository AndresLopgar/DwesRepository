<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Empleado"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/miCss.css">
<title>Opción 1 Menú</title>
</head>
<body>
	<h1>Mostrar Empleados</h1>
	<p><%=request.getAttribute("mensaje")%></p>
	<table border="1">
		<tr>
			<td>DNI</td>
			<td>Nombre</td>
			<td>Sexo</td>
			<td>Categoría</td>
			<td>Años trabajados</td>
		</tr>
		<c:forEach var="empleado" items="${empleados}">
			<tr>
				<td><a
					href="NominasController?opcion=meditar&dni=<c:out value="${ empleado.dni}"></c:out>">
						<c:out value="${ empleado.dni}"></c:out>
				</a></td>
				<td>${empleado.nombre}</td>
				<td>${empleado.sexo}</td>
				<td>${empleado.categoria}</td>
				<td>${empleado.anyosTrabajados}</td>
				<td><a
					href="NominasController?opcion=eliminar&dni=<c:out value="${ empleado.dni}"></c:out>">
						Eliminar </a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="NominasController?opcion=menu">Volver al men&uacute;</a>
</body>
</html>