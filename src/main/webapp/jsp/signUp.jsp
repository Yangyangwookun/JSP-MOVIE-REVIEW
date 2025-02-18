<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
 * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #ff9a9e, #fad0c4);
        }

        .login-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 320px;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            text-align: left;
            font-size: 14px;
            margin: 10px 0 5px;
            color: #555;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
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

        .signup-btn {
            background: none;
            color: #ff6f61;
            border: 1px solid #ff6f61;
        }

        .signup-btn:hover {
            background-color: #ff6f61;
            color: white;
        }
        </style>
</head>
<body>
<div class= "login-container">
   <h2>회원가입</h2>
   <form method="post" action="/jspSqlProject/UserServlet?action=signUp">
      <label>아이디</label>
      <input type="text" name="email" maxlength="20" placeholder="이메일 입력" required/><br/>
      <label>비밀번호</label>
      <input type="password" name="password" maxlength="20" placeholder="비밀번호 입력" required/><br/>
      <label>이름</label>
      <input type="text" name="name" maxlength="10" placeholder="이름 입력" required><br/>
      <label>나이</label>
      <input type="number" min="1" max="150" name="age" placeholder="나이 입력" required><br/>
      <label>전화번호</label>
      <input type="tel" name="phoneNumber" maxlength="11"placeholder="-없이 입력해 주세요" required><br/>
      
      <button type="submit">회원가입</button>
   </form>
   </div>
</body>
</html>