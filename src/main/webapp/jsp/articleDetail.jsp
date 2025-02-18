<%@page import="dao.Article"%>
<%@page import="dao.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 상세</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background: linear-gradient(135deg, #ff9a9e, #fad0c4);
            padding: 20px;
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .article-info {
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
        }

        .content {
            background: #f8f8f8;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .comments {
            margin-top: 20px;
        }

        .comment {
            background: #fff3f3;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
        }

        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            resize: none;
        }

        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #ff6f61;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #e55b50;
        }
    </style>
</head>
<body>
    <jsp:include page="/jsp/header.jsp">
        <jsp:param name="tblUserId" value="${tblUserId}"/>
    </jsp:include>
    
    <div class="container">
        <h2>${article.getTitle()}</h2>
        <p class="article-info">게시물 타입: ${article.getTblArticleTypeId()} | 영화 ID: ${article.getTblMovieId()} | 작성일: ${article.getWriteDate()} | 작성자: ${article.getTblUserId()}</p>
        <div class="content">${article.getContent()}</div>
        
        <h3>댓글</h3>
        <div class="comments">
            <c:forEach var="comment" items="${comments}" >
                <div class="comment">
                    <p><strong>${comment.value.getContent()}</strong></p>
                    <p style="font-size: 12px; color: #777;">작성일: ${comment.value.getWriteDate()} | 좋아요: ${comment.value.getLikeCount()} | 싫어요: ${comment.value.getDislikeCount()}</p>
                </div>
            </c:forEach>
        </div>
        
        <form method="post" action="/jspSqlProject/CommentServlet?action=insert&tblUserId=${tblUserId}&tblArticleId=${article.getId()}">
            <label for="content"></label>
            <textarea id="content" name="content" rows="3" placeholder="댓글을 입력하세요."></textarea>
            <button type="submit">댓글 등록</button>
        </form>
    </div>
</body>
</html>
