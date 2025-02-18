<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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

.tabs {
    margin: 20px;
    padding: 0;
}
.tabs ul {
    list-style: none;
    display: flex;
    
    padding-left: 0;
}
.tabs ul li {
    margin-right: 10px;
}
.tabs ul li a{
	color: black;
    text-decoration: none;
    border-bottom: 1px solid #ccc;
}

.article{
  	display: flex;
  	flex-direction: column; 
    align-items: center;
    height: 100vh;  
}
.article_table {
    width: 80%;
    border-collapse: collapse;
}
.article_table th, .article_table td {
    padding: 8px;
    text-align: center;
}

</style>
</head>
<body>

<!-- 메뉴 목록(게시글, 영화 목록 중 하나 선택) -->
<div class="article_menu">
	<button class="menu">게시판</button>
	<button class="menu">회원 목록</button>
	<button class="menu">영화 목록</button>
</div>

<div class="article_top">
	<h2 id="article_type">전체</h2>
	<div class="user_set">
		<p>회원 이름</p>
		<button>회원정보</button>
		<button>로그아웃</button>
	</div>
</div>

<div class="article">

<!-- 게시글 탭(리뷰, 영화 작성자, ) -->
<div class="tabs">
	<ul>
		<li><a href="/jspSqlProject/article?action=tab&tabid=1">일반</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=2">리뷰</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=3">평론가</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=4">공지</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=5">한줄</a></li>
		
	</ul>
</div>

<!-- 게시글 목록(탭이나 검색에 따라 출력) -->
<table class="article_table" border="1">
	<tr> 
		<th>아이디</th> 
		<th>게시글 종류</th>
		<th>제목</th> 
		<th>작성자</th>
		<!-- <th>작성자 나이대</th> -->
		<th>영화 제목</th>  
		<th>조회수</th> 
		<th>좋아요 수</th> 
		<th>싫어요 수</th>
		<th>작성일</th> 
	</tr>
	<c:forEach var="a" items="${articlelist}" varStatus="i">
		<tr>
			<td></td> 
			<td></td> 
			<td></td> 
			<td></td> 
			<!-- <td></td>-->
			<td></td> 
			<td></td> 
			<td></td> 
			<td></td>
		</tr>
	</c:forEach>
</table>

<div class="article_search">
	<select name="searchType">
    	<option value="title">제목</option>
        <option value="movieTitle">영화 제목</option>
        <option value="Genre">영화 장르</option>
        <option value="name">닉네임</option>
        
	</select>
	<input type="text" name="word">
	<button>검색하기</button>
</div>
</div>

<script>
	
</script>
</body>
</html>