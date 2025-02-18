<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영화 모음집</title>
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
            <div style="color: green; margin: 10px;">영화 삭제가 성공적으로 처리되었습니다.</div>
        </c:when>
        <c:otherwise>
            <div style="color: red; margin: 10px;">영화 삭제에 실패하였습니다.</div>
        </c:otherwise>
    </c:choose>
</c:if>

	<!-- 메뉴 목록(게시글, 영화 목록 중 하나 선택) -->
	<div class="article_menu">
   <a class="menu" href="/jspSqlProject/MainServlet?action=home&tblUserId=${user.getId()}">메인화면</a>
   <button class="menu">회원 목록</button>
   <a class="menu" href="/jspSqlProject/MovieService?tblUserId=${user.getId()}">영화화면</a>
	</div>

	<div class="article_top">
		<h2 id="article_type">영화 목록</h2>
		<div class="user_set">
			<p>회원 이름</p>
			<button>회원정보</button>
			<button onclick="location.href='/jspSqlProject/html/Login.html'">로그아웃</button>
		</div>
	</div>
	
    <!-- 영화 목록을 그리드 형태로 보여줌 -->
    <div class="movie_set">
        <c:forEach var="m" items="${moveinfo.movlist}">
            <div class="movie_element">
                <h3>${m.title}</h3>
                <%-- <img src="${m.url}" alt="${m.title} 포스터"> --%>
                <p>제작일: ${m.makeDate}</p>
                <p>제작국가: ${m.makeCountry}</p>
                <p>감독: ${m.director}</p>
                <p>배급사: ${m.company}</p>
               	<c:if test="${not empty moveinfo.typelist[m.id]}">
    				<p>장르:
        				<c:forEach var="genre" items="${moveinfo.typelist[ m.id]}">
           		 			${genre}&nbsp;
        				</c:forEach>
    				</p>
				</c:if>
                <button class="delete_movie" value="${m.id}">삭제하기</button>
            </div>
        </c:forEach>
    </div>

    <button class="add_movie">새로운 영화 추가하기</button>
	
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	
	<script>
	 function deleteMovie(movieId) {
         if(confirm("정말 삭제하시겠습니까?")) {
             // 영화 삭제 후 다시 목록을 불러오기 위해 MovieService로 이동
             location.href = "MovieService?action=delete&movieid=" + movieId;
         }
     }
	</script>
</body>
</html>