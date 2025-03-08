<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER QQ</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/main.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<jsp:include page="Nav.jsp" flush="false"/>
	<div id="intro">
		<img src="/userqq/resources/images/Main1.png" alt="USER QQ" id="main1" />
		<div id="intro-text">
			<h1>유저들과 함께하는 퀴즈</h1>
			<p>다른 유저들과 퀴즈를 만들고 풀 수 있는 곳, USER QuizQuiz에 오신 것을 환영합니다!</p>
		</div>
	</div>
</body>
</html>