<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>USER QQ/MY PAGE</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/mypage.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<jsp:include page="Nav.jsp" flush="false"/>
	<%
		if (session.getAttribute("id") == null) {
	%>
		<h1>로그인 후 이용 가능합니다.</h1>
		<a href="/userqq/login">로그인 창으로 이동</a>
	<%
		} else {
	%>
		<div id="profile-info">
			<div id="profile-images">
				<img src="/userqq/resources/images/DefaultProfile.png">
			</div>
			<h3><%=session.getAttribute("id") %> / <%=session.getAttribute("nickname") %></h3>
			<h3><%=request.getAttribute("solvedQuizCount") %>개의 퀴즈 정답</h3>
			
		</div>
	<%
		}
	%>
</body>
</html>