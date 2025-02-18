<%@page import="dao.ViewArticle"%>
<%@page import="dao.User"%>
<%@page import="dao.ArticleType"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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

<!-- �޴� ���(�Խñ�, ��ȭ ��� �� �ϳ� ����) -->
<div class="article_menu">
   <a class="menu" href="/jspSqlProject/MainServlet?action=home&tblUserId=${user.getId()}">����ȭ��</a>
   <button class="menu">ȸ�� ���</button>
   <a class="menu" href="/jspSqlProject/MovieService?tblUserId=${user.getId()}">��ȭȭ��</a>
</div>

<div class="article_top">   
<h2 id="article_type">${articleTypeText}</h2>
   <div class="user_set">
      <p>${user.getName()}</p>
      <button>ȸ������</button>
      <a href="/jspSqlProject/html/index.html">�α׾ƿ�</a>
   </div>
</div>

<div class="article">

<!-- �Խñ� ��(����, ��ȭ �ۼ���, ) -->
<div class="tabs">
   <ul>
	   <c:forEach var="articleType" items="${articleTypes}">
	        <li><a href="/jspSqlProject/MainServlet?action=${articleType.value.getId()}&tblUserId=${user.getId()}">${articleType.value.getType()}</a></li> 
	   </c:forEach>
   </ul>
</div>

<!-- �Խñ� ���(���̳� �˻��� ���� ���) -->
<table class="article_table" border="1">
   <tr> 
      <th>���̵�</th> 
      <th>�Խñ� ����</th>
      <th>����</th> 
      <th>�ۼ���</th>
      <!-- <th>�ۼ��� ���̴�</th> -->
      <th>��ȭ ����</th>  
      <th>��ȸ��</th> 
      <th>���ƿ� ��</th> 
      <th>�Ⱦ�� ��</th>
      <th>�ۼ���</th> 
   </tr>
   <c:forEach var="viewArticle" items="${viewArticles}">
      <tr>
         <td>${viewArticle.value.getId()}</td> 
         <td>${viewArticle.value.getArticleType()}</td> 			
         <td><a href="/jspSqlProject/ArticleServlet?action=detail&id=${viewArticle.value.getId()}&tblUserId=${user.getId()}">${viewArticle.value.getTitle()}</a></td> 
         <td>${viewArticle.value.getUserName()}</td> 
         <td>${viewArticle.value.getMovieTitle()}</td> 
         <td>${viewArticle.value.getViewCount()}</td> 
         <td>${viewArticle.value.getLikeCount()}</td> 
         <td>${viewArticle.value.getDislikeCount()}</td>
         <td>${viewArticle.value.getWriteDate()}</td>
      </tr>
   </c:forEach>
</table>

<div class="article_search">
   <select name="searchType">
       <option value="title">����</option>
        <option value="movieTitle">��ȭ ����</option>
        <option value="Genre">��ȭ �帣</option>
        <option value="name">�г���</option>
        
   </select>
   <input type="text" name="word">
   <button>�˻��ϱ�</button>
</div>
<button onclick="location.href='/jspSqlProject/ArticleServlet?action=insert&tblUserId=${user.getId()}'">�۾���</button>
</div>

<script>
   
</script>
</body>
</html>