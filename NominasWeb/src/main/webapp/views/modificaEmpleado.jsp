<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/miCss.css">
<title>Modifica empleado</title>
</head>
<body>
	<p><%= request.getAttribute("mensaje") %></p>
 <h1>Editar Empleado</h1>
 <form action="NominasController" method="post">
  <c:set var="empleado" value="${empleado}"></c:set>
  <input type="hidden" name="opcion" value="editar">
  <input type="hidden" name="id" value="${empleado.dni}">
  <table border="1">
   <tr>
    <td>Nombre:</td>
    <td><input type="text" name="nombre" size="50" value="${empleado.nombre}"></td>
   </tr>
   <tr>
    <td>Sexo:</td>
    <td><input type="text" name="sexo" size="50" value="${producto.sexo}"></td>
   </tr>
   <tr>
    <td>Categoria:</td>
    <td><input type="text" name="categoria" size="50" value="${producto.categoria}"></td>
   </tr>
   <tr>
    <td>A&ntilde;os trabajados:</td>
    <td><input type="text" name="anyos_trabajados" size="50" value="${producto.anyosTrabajados}"></td>
   </tr>
  </table>
  <input type="submit" value="Guardar">
 </form>
	<a href="NominasController?opcion=menu">Volver al men&uacute;</a>
</body>
</html>