<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>USER QQ/WRITE POST</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/newcommunity.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<jsp:include page="Nav.jsp" flush="false"/>
	<div id="new-post">
		<h1>커뮤니티 글쓰기</h1>
		<form method="post" action="newcommunity">
			<input type="text" placeholder="제목을 입력하세요." id="title" name="title">
			<textarea placeholder="글을 입력하세요." id="content" name="content"></textarea>
			<input type="submit" value="글 업로드">
		</form>
	</div>
</body>
</html>