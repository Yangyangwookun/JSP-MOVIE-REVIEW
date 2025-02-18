<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dao.CommentDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>댓글 테스트</title>
</head>
<body>

<h2>댓글 테스트 페이지</h2>

    <!-- 테스트를 위한 기본값 설정 (articleid, userid) -->
    <div>
        <label for="articleid">게시글 ID:</label>
        <input type="text" id="articleid" name="articleid" value="1">
    </div>
    <div>
        <label for="userid">회원 ID:</label>
        <input type="text" id="userid" name="userid" value="1">
        <button onclick='loadComments()'>댓글 조회</button>
    </div>
    
    <!-- 댓글 등록 폼 -->
    <div>
        <form id="addCommentForm">
            <label for="content">댓글 내용:</label>
            <input type="text" id="content" name="content" required>
            <button type="submit">댓글 등록</button>
        </form>
    </div>
    
    <hr>
    
    <!-- 댓글 목록 출력 영역 -->
    <h3>댓글 목록</h3>
    <ul id="commentList">
    
    </ul>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
     <script>
        // 댓글 목록을 불러오는 함수
        function loadComments() {
            var articleId = $("#articleid").val();
            $.ajax({
                url: '/jspSqlProject/CommentService',
                data: { action: 'list', articleid: articleId },
                dataType: 'json',
                success: function(data) {
                console.log(data);
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        html += "<li>" 
                                + data[i].content 
                                + " (작성자: " + data[i].tbl_user_id + ") " 
                                + "<button onclick='deleteComment(" + data[i].id + ")'>삭제</button>"
                                + "</li>";
                    }
                    $("#commentList").html(html);
                },
                error: function(xhr, status, error) {
                    alert("댓글 목록 불러오기 실패: " + error);
                }
            });
        }
        
        // 댓글 등록 함수
        function addComment() {
            var articleId = $("#articleid").val();
            var userId = $("#userid").val();
            var content = $("#content").val();
            $.ajax({
                url: '/jspSqlProject/CommentService',
                type: 'POST',
                data: { action: 'insert', articleid: articleId, userid: userId, content: content },
                success: function() {
                    alert("댓글이 등록되었습니다.");
                    $("#content").val(""); // 입력란 초기화
                    loadComments();
                },
                error: function(xhr, status, error) {
                	console.log(error);
                    alert("댓글 등록 실패: " + error);
                }
            });
        }
        
        // 댓글 삭제 함수
        function deleteComment(commentId) {
            $.ajax({
                url: '/jspSqlProject/CommentService',
                type: 'POST',
                data: { action: 'delete', commentid: commentId },
                success: function() {
                    alert("댓글이 삭제되었습니다.");
                    loadComments();
                },
                error: function(xhr, status, error) {
                	console.log(commentId);
                    alert("댓글 삭제 실패: " + error);
                }
            });
        }
        
        $(document).ready(function(){
            //loadComments();
            $("#addCommentForm").submit(function(e){
                e.preventDefault();
                addComment();
            });
        });
    </script>
</body>
</html>