<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>USER QQ/SOLVE QUIZ</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/solve.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<%
		Map<String, String> typeMap = new HashMap<>();
		typeMap.put("daily", "일상");
		typeMap.put("game", "게임");
		typeMap.put("education", "교육");
		typeMap.put("knowledge", "지식");
		typeMap.put("others", "기타");
		String postAction = "solve?quizid=" + (String)request.getAttribute("quizId");
	%>
	<jsp:include page="Nav.jsp" flush="false"/>
	<div id="quiz-div">
		<h1 id="title">[<%=typeMap.get(request.getAttribute("type")) %>]<%=request.getAttribute("title") %></h1>
		<p id="quiz-content"><%=request.getAttribute("content") %></p>
	</div>
	<div id="solve-div">
		<form method="post" action=<%=postAction %>>
			<input type="text" name="answer" id="my-answer">
			<input type="submit" value="채점하기">
		</form>
	</div>
</body>
</html>