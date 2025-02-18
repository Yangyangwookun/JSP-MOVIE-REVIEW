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

<!-- �޴� ���(�Խñ�, ��ȭ ��� �� �ϳ� ����) -->
<div class="article_menu">
	<button class="menu">�Խ���</button>
	<button class="menu">ȸ�� ���</button>
	<button class="menu">��ȭ ���</button>
</div>

<div class="article_top">
	<h2 id="article_type">��ü</h2>
	<div class="user_set">
		<p>ȸ�� �̸�</p>
		<button>ȸ������</button>
		<button>�α׾ƿ�</button>
	</div>
</div>

<div class="article">

<!-- �Խñ� ��(����, ��ȭ �ۼ���, ) -->
<div class="tabs">
	<ul>
		<li><a href="/jspSqlProject/article?action=tab&tabid=1">�Ϲ�</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=2">����</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=3">��а�</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=4">����</a></li>
		<li><a href="/jspSqlProject/article?action=tab&tabid=5">����</a></li>
		
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
    	<option value="title">����</option>
        <option value="movieTitle">��ȭ ����</option>
        <option value="Genre">��ȭ �帣</option>
        <option value="name">�г���</option>
        
	</select>
	<input type="text" name="word">
	<button>�˻��ϱ�</button>
</div>
</div>

<script>
	
</script>
</body>
</html>