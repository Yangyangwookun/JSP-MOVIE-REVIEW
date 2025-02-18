<%@page import="dao.MovieDao"%>
<%@page import="dao.ArticleType"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="EUC-KR">
<title>글쓰기</title>
</head>
<body>
	<jsp:include page="/jsp/header.jsp">
		<jsp:param name="tblUserId" value="${tblUserId}"/>
	</jsp:include>
	<h2>글쓰기</h2>
	<form method="post" action="/jspSqlProject/ArticleServlet?action=insert">
		<label>로그인한 유저 id</label><input type="text" name="tblUserId" value="${tblUserId}" readonly/><br/>
		<label>제목</label><input type="text" name="title"/><br/>
		<label>게시물타입</label>
		<select name="tblArticleTypeId">
			<c:forEach var="articleType" items="${articleTypes}">
				<option value="${articleType.key}">${articleType.value.getType()}</option>
			</c:forEach>
		</select><br/>
		<label>영화</label>
		<select name="tblMovieId">
			<c:forEach var="movie" items="${movies}">
				<option value="${movie.key}">${movie.value.getTitle()}</option>
			</c:forEach>
		</select><br/>
		<label>내용</label><textarea name="content"></textarea><br/>
		<button type="submit">작성</button>
	</form>
</body>
</html>