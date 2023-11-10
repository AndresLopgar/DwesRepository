<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" type="text/css" href="views/miCss.css">
</head>
<body>
   <%@include file="views/header.jsp" %>
<div class="contenido">
 <c:choose>
        <c:when test="${empty content}">
            <%@ include file="views/login.jsp" %>
        </c:when>
        <c:otherwise>
            <jsp:include page="${content}" />
        </c:otherwise>
    </c:choose>
</div>

    	<%@include file="views/footer.jsp" %>
</body>
</html>