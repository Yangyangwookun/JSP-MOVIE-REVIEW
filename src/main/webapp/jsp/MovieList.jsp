<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ȭ ������</title>
<style>
.article_top{
	display: flex;
    justify-content: space-between;
	align-items: center;
}
#article_type{
	margin:30px;
}
.user_set{
	margin:30px;
}

.article_menu{
	width: 100%;
	border: 1px solid #ccc;
}
.menu{
	background-color: transparent;
    border: none;
    outline: none;
    padding: 10px 20px;
    color: #333;
    font-size: 16px;
    cursor: pointer; 
}
.movie_set {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
    padding: 20px;
}

.movie_element {
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 15px;
    text-align: center;
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
    background-color: #fff;
}

.movie_element img {
    width: 150px;
    height: 150px;
    object-fit: cover;
    margin-bottom: 10px;
}

.delete_movie {
    background-color: #ff4d4d;
    color: #fff;
    border: none;
    padding: 5px 10px;
    border-radius: 3px;
    cursor: pointer;
}
.add_movie {
    display: block;
    margin: 30px auto;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
}
</style>
</head>
<body>

<c:if test="${not empty deleteresult}">
    <c:choose>
        <c:when test="${deleteresult}">
            <div style="color: green; margin: 10px;">��ȭ ������ ���������� ó���Ǿ����ϴ�.</div>
        </c:when>
        <c:otherwise>
            <div style="color: red; margin: 10px;">��ȭ ������ �����Ͽ����ϴ�.</div>
        </c:otherwise>
    </c:choose>
</c:if>

	<!-- �޴� ���(�Խñ�, ��ȭ ��� �� �ϳ� ����) -->
	<div class="article_menu">
   <a class="menu" href="/jspSqlProject/MainServlet?action=home&tblUserId=${user.getId()}">����ȭ��</a>
   <button class="menu">ȸ�� ���</button>
   <a class="menu" href="/jspSqlProject/MovieService?tblUserId=${user.getId()}">��ȭȭ��</a>
	</div>

	<div class="article_top">
		<h2 id="article_type">��ȭ ���</h2>
		<div class="user_set">
			<p>ȸ�� �̸�</p>
			<button>ȸ������</button>
			<button onclick="location.href='/jspSqlProject/html/Login.html'">�α׾ƿ�</button>
		</div>
	</div>
	
    <!-- ��ȭ ����� �׸��� ���·� ������ -->
    <div class="movie_set">
        <c:forEach var="m" items="${moveinfo.movlist}">
            <div class="movie_element">
                <h3>${m.title}</h3>
                <%-- <img src="${m.url}" alt="${m.title} ������"> --%>
                <p>������: ${m.makeDate}</p>
                <p>���۱���: ${m.makeCountry}</p>
                <p>����: ${m.director}</p>
                <p>��޻�: ${m.company}</p>
               	<c:if test="${not empty moveinfo.typelist[m.id]}">
    				<p>�帣:
        				<c:forEach var="genre" items="${moveinfo.typelist[ m.id]}">
           		 			${genre}&nbsp;
        				</c:forEach>
    				</p>
				</c:if>
                <button class="delete_movie" value="${m.id}">�����ϱ�</button>
            </div>
        </c:forEach>
    </div>

    <button class="add_movie">���ο� ��ȭ �߰��ϱ�</button>
	
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	
	<script>
	 function deleteMovie(movieId) {
         if(confirm("���� �����Ͻðڽ��ϱ�?")) {
             // ��ȭ ���� �� �ٽ� ����� �ҷ����� ���� MovieService�� �̵�
             location.href = "MovieService?action=delete&movieid=" + movieId;
         }
     }
	</script>
</body>
</html>