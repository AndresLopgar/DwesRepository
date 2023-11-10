<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/miCss.css">
<title>Empresa</title>
</head>
<body>
	<form action="NominasController" method="post">
		<input type="hidden" name="opcion" value="iniciar">
		<c:set var="mensaje" value="${mensaje}" />

		<c:choose>
			<c:when test="${empty mensaje}">
				<c:set var="mensaje" value="" />
			</c:when>
			<c:otherwise>
				<!-- El atributo 'mensaje' no es nulo, se mantiene sin cambios -->
			</c:otherwise>
		</c:choose>
		
		<!-- Mostrar el mensaje si no es nulo -->
		<c:if test="${not empty mensaje}">
			<p>${mensaje}</p>
		</c:if>
		<h1>INICIO DE SESI&Oacute;N</h1>
		<table border="1">
			<tr>
				<td>Nombre del empleado:</td>
				<td><input type="text" name="nombre" size="50"></td>
			</tr>
			<tr>
				<td>DNI del empleado:</td>
				<td><input type="text" name="dni" size="50"></td>
			</tr>
		</table>
		<input type="submit" value="iniciar">
	</form>
</body>
</html>