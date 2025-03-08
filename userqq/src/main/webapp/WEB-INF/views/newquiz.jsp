<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>USER QQ/NEW QUIZ</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/newquiz.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<jsp:include page="Nav.jsp" flush="false"/>
	<div id="new-quiz">
		<h1>퀴즈 만들기</h1>
		<form method="post" action="newquiz">
			<h3>퀴즈 분야를 선택해주세요.</h3>
			일상<input type="radio" name="quizType" value="daily" checked>
			게임<input type="radio" name="quizType" value="game">
			교육<input type="radio" name="quizType" value="education">
			지식<input type="radio" name="quizType" value="knowledge">
			기타<input type="radio" name="quizType" value="others">
			<input type="text" placeholder="퀴즈 제목을 입력하세요." id="title" name="title">
			<textarea placeholder="퀴즈 내용을 입력하세요." id="content" name="content"></textarea>
			<input type="text" placeholder="정답을 입력하세요." id="answer" name="answer">
			<input type="submit" value="퀴즈 업로드">
		</form>
	</div>
</body>
</html>