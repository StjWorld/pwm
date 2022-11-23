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
<% String session_name = null; %>
<% session_name = (String)session.getAttribute("user"); %>

<% if (session_name != null) { %>
<p>Welcome <%=session_name%></p>
<% }  %>

<%@include file="footer.html"%>
</body>
</html>
