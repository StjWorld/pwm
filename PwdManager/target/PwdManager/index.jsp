<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Manager</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%@include file="navbar.html"%>
<h1>Login</h1>
<div class='box'>
<form class='registerForm' method='post'>
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="usernamee"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br><br>
  <input type="submit" value="Login">
</form>
</div>
<%@include file="footer.html"%>
</body>
</html>