<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>USER QQ/QUIZ</title>
<link rel="stylesheet" href="/userqq/resources/css/nav.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/common.css?v=<%=System.currentTimeMillis() %>">
<link rel="stylesheet" href="/userqq/resources/css/quiz.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
	<%
		pageContext.setAttribute("page", 1);
	%>
	<jsp:include page="Nav.jsp" flush="false"/>
	<div id="text-parent">
		<h1>퀴즈</h1>
		<a href="/userqq/newquiz" id="new-quiz"><input type="button" value="퀴즈 만들기"></a>
	</div>
	<div id="quiz-container">
		<%
			List<String> quizId = (List<String>)request.getAttribute("quizIds");
			List<String> titles = (List<String>)request.getAttribute("titles");
			List<String> writers = (List<String>)request.getAttribute("writers");
			List<String> dates = (List<String>)request.getAttribute("dates");
			List<String> solvedCount = (List<String>)request.getAttribute("solveCounts");
			List<String> types = (List<String>)request.getAttribute("types");
			Map<String, String> typeMap = new HashMap<>();
			typeMap.put("daily", "일상");
			typeMap.put("game", "게임");
			typeMap.put("education", "교육");
			typeMap.put("knowledge", "지식");
			typeMap.put("others", "기타");
			for (int i = 0; i < titles.size(); i++) {
		%>
				<div class="quiz">
					<%
						String solveUrl = "/userqq/solve?quizid=" + quizId.get(i);
					%>
					<h2 class="no-parent-style">[<%=typeMap.get(types.get(i)) %>] <%=titles.get(i) %></h2>
					<h4 class="quiz-info no-parent-style"><%=writers.get(i) %> / <%=dates.get(i) %></h4>
					<h4 class="solved-count"><%=solvedCount.get(i) %>명 맞춤</h4>
					<a href=<%=solveUrl %> class="solve-link">문제 풀기</a>
				</div>
		<%
			}
		%>
		<!-- 
		<div class="post">
			<h2 class="no-parent-style">제목</h2>
			<h4 class="post-info no-parent-style">guitar / 2025년 03월 01일 11시 37분</h4>
			<p class="no-parent-style">내용</p>
		</div> -->
	</div>
	<div id="change-page">
		<input type="button" value="이전" id="back">
		<span id="now-page"><%=pageContext.getAttribute("page") %></span>
		<input type="button" value="다음" id="next">
	</div>
	<script>
		var totalPage = <%=request.getAttribute("maxPage") %>;
		var back = document.getElementById("back");
		var next = document.getElementById("next");
		var url = new URL(window.location.href);
		var urlParams = url.searchParams;
		var nowPage = urlParams.get("page");
		document.getElementById("now-page").innerText = nowPage;
		
		if (nowPage == 1) {
			back.disabled = true;
		} else {
			back.disabled = false;
		}
		if (nowPage >= totalPage) {
			next.disabled = true;
		} else {
			next.disabled = false;
		}
		
		back.addEventListener("click", () => {
			<%
				request.setAttribute("page", (int)pageContext.getAttribute("page") - 1);
			%>
			location.href = "/userqq/quiz?page=" + (parseInt(nowPage) - 1);
		});
		
		next.addEventListener("click", () =>  {
			<%
				request.setAttribute("page", (int)pageContext.getAttribute("page") + 1);
			%>
			location.href = "/userqq/quiz?page=" + (parseInt(nowPage) + 1);
		});
	</script>
</body>
</html>