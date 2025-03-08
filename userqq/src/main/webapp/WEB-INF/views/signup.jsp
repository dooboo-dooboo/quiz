<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>USER QQ/SIGN UP</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/signup.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<jsp:include page="Nav.jsp" flush="false"/>
	<form method="post" action="signup" id="sign-up-form">
		<h1>회원가입</h1>
		아이디 : <input type="text" name="id"><br>
		닉네임 : <input type="text" name="nickname"><br>
		비밀번호 : <input type="password" name="password"><br>
		<h2>정보는 변경할 수 없습니다. 신중하게 결정해주세요.</h2>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>