<%@page import="dao.UserType"%>
<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>유저타입 조회</title>
</head>
<body>
	<jsp:include page="/jsp/header.jsp">
		<jsp:param name="tblUserId" value="${tblUserId}"/>
	</jsp:include>
	<h2>유저타입 조회</h2>
	<table border="1">
		<tr><th>id</th><th>타입</th></tr>
		<c:forEach var="userType" items="${userTypes}">
		<tr><th>${userType.key}</th><th>${userType.value.getType()}</th></tr>
		</c:forEach>
		<tr><th colspan="2"><button onclick="location.href='/jspSqlProject/UserTypeServlet?action=select'">조회</button></th></tr>
	</table>
</body>
</html>