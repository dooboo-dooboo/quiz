<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	session.removeAttribute("id");
	session.removeAttribute("nickname");
	response.sendRedirect("main");
%>
</body>
</html>