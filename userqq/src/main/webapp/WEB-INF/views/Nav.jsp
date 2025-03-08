<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="nav">
		<ul id="nav-container">
			<li><a href="/userqq/main"><img src="/userqq/resources/images/Logo.png" alt="logo" id="logo" /></a></li>
			<li class="nav-item"><a href="/userqq/community?page=1" class="nav-main-options">커뮤니티</a></li>
			<li class="nav-item"><a href="/userqq/quiz?page=1" class="nav-main-options">퀴즈</a></li>
			<li>
				<%
					if (session.getAttribute("id") == null) {
				%>
					<ul class="account">
						<li><a href="/userqq/signup">회원가입</a></li>
						<li><a href="/userqq/login">로그인</a></li>
					</ul>
				<%
					} else {
				%>
					<ul class="account">
						<li><a href="/userqq/logout">로그아웃</a></li>
						<li><a href="/userqq/mypage">마이페이지</a></li>
					</ul>
				<%
					}
				%>
			</li>
		</ul>
	</div>