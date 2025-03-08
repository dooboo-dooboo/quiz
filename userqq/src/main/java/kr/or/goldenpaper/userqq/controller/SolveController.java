package kr.or.goldenpaper.userqq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.AccountDao;
import kr.or.goldenpaper.userqq.dao.QuizDao;
import kr.or.goldenpaper.userqq.dto.Account;
import kr.or.goldenpaper.userqq.dto.Quiz;

@Controller
public class SolveController {
	@RequestMapping(value="/solve", method=RequestMethod.GET)
	public void solvePageGet(HttpServletRequest request) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		QuizDao quizDao = ac.getBean(QuizDao.class);
		String quizId = request.getParameter("quizid");
		if (quizDao.selectQuizById(quizId).size() > 0) {
			Quiz nowQuiz = quizDao.selectQuizById(quizId).get(0);
			request.setAttribute("quizId", quizId);
			request.setAttribute("title", nowQuiz.getTitle());
			request.setAttribute("content", nowQuiz.getContent());
			request.setAttribute("writer", nowQuiz.getUser_id());
			request.setAttribute("type", nowQuiz.getQuiz_type());
			request.setAttribute("answer", nowQuiz.getAnswer());
		}
	}
	
	@RequestMapping(path="/solve", method=RequestMethod.POST, produces = "text/html; charset=utf8")
	public void checkAnswer(@RequestParam(name = "answer", required = true)String myAnswer, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		AccountDao accountDao = ac.getBean(AccountDao.class);
		QuizDao quizDao = ac.getBean(QuizDao.class);
		String quizId = request.getParameter("quizid");
		Quiz nowQuiz = quizDao.selectQuizById(quizId).get(0);
		String answer = nowQuiz.getAnswer();
		String userId = nowQuiz.getUser_id();
		
		if (session.getAttribute("id") == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.append("<script>");
				pw.append("alert('로그인 후 이용 가능합니다.'); location.href='/userqq/login';");
				pw.append("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		Account myAccount = accountDao.selectById((String)session.getAttribute("id"));
		List<Integer> mySolvedQuiz = accountDao.selectAllClearedQuiz((String)session.getAttribute("id"));
		System.out.println(myAccount.getCleared_quiz());
		if (answer.equals(myAnswer)) {
			PrintWriter pw;
			System.out.println("???" + mySolvedQuiz);
			if (mySolvedQuiz.indexOf(Integer.parseInt(quizId)) < 0) {
				try {
					response.setContentType("text/html; charset=utf8");
					response.setContentType("text/html; charset=UTF-8");
					pw = response.getWriter();
					pw.append("<script>");
					pw.append("alert('정답입니다!'); location.href='/userqq/quiz?page=1';");
					pw.append("</script>");
					accountDao.insertClearedQuiz(Integer.parseInt(quizId), userId);
					int solvedCount = quizDao.selectQuizById(quizId).get(0).getSolved_count() + 1;
					quizDao.updateSolvedCount(solvedCount, Integer.parseInt(quizId));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					response.setContentType("text/html; charset=utf8");
					response.setContentType("text/html; charset=UTF-8");
					pw = response.getWriter();
					pw.append("<script>");
					pw.append("alert('이번에도 정답입니다!'); location.href='/userqq/quiz?page=1';");
					pw.append("</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else {
			PrintWriter pw;
			try {
				response.setContentType("text/html; charset=utf8");
				response.setContentType("text/html; charset=UTF-8");
				pw = response.getWriter();
				pw.append("<script>");
				pw.append("alert('오답입니다. 다시 맞춰보세요!'); history.go(-1);");
				pw.append("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
