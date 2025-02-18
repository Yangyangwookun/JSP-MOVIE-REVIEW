<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dao.CommentDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��� �׽�Ʈ</title>
</head>
<body>

<h2>��� �׽�Ʈ ������</h2>

    <!-- �׽�Ʈ�� ���� �⺻�� ���� (articleid, userid) -->
    <div>
        <label for="articleid">�Խñ� ID:</label>
        <input type="text" id="articleid" name="articleid" value="1">
    </div>
    <div>
        <label for="userid">ȸ�� ID:</label>
        <input type="text" id="userid" name="userid" value="1">
        <button onclick='loadComments()'>��� ��ȸ</button>
    </div>
    
    <!-- ��� ��� �� -->
    <div>
        <form id="addCommentForm">
            <label for="content">��� ����:</label>
            <input type="text" id="content" name="content" required>
            <button type="submit">��� ���</button>
        </form>
    </div>
    
    <hr>
    
    <!-- ��� ��� ��� ���� -->
    <h3>��� ���</h3>
    <ul id="commentList">
    
    </ul>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
     <script>
        // ��� ����� �ҷ����� �Լ�
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
                                + " (�ۼ���: " + data[i].tbl_user_id + ") " 
                                + "<button onclick='deleteComment(" + data[i].id + ")'>����</button>"
                                + "</li>";
                    }
                    $("#commentList").html(html);
                },
                error: function(xhr, status, error) {
                    alert("��� ��� �ҷ����� ����: " + error);
                }
            });
        }
        
        // ��� ��� �Լ�
        function addComment() {
            var articleId = $("#articleid").val();
            var userId = $("#userid").val();
            var content = $("#content").val();
            $.ajax({
                url: '/jspSqlProject/CommentService',
                type: 'POST',
                data: { action: 'insert', articleid: articleId, userid: userId, content: content },
                success: function() {
                    alert("����� ��ϵǾ����ϴ�.");
                    $("#content").val(""); // �Է¶� �ʱ�ȭ
                    loadComments();
                },
                error: function(xhr, status, error) {
                	console.log(error);
                    alert("��� ��� ����: " + error);
                }
            });
        }
        
        // ��� ���� �Լ�
        function deleteComment(commentId) {
            $.ajax({
                url: '/jspSqlProject/CommentService',
                type: 'POST',
                data: { action: 'delete', commentid: commentId },
                success: function() {
                    alert("����� �����Ǿ����ϴ�.");
                    loadComments();
                },
                error: function(xhr, status, error) {
                	console.log(commentId);
                    alert("��� ���� ����: " + error);
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