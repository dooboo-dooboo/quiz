package kr.or.goldenpaper.userqq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.QuizDao;
import kr.or.goldenpaper.userqq.dto.Quiz;

@Controller
public class NewQuizController {
	@RequestMapping(value="/newquiz", method=RequestMethod.GET)
	public void newQuizPageGet() {
		//null
	}
	
	@ResponseBody
	@RequestMapping(value="/newquiz", method=RequestMethod.POST)
	public void newQuiz(@RequestParam(name = "title", required = true)String title, @RequestParam(name = "content", required = true)String content, @RequestParam(name = "quizType", required = true)String quizType, @RequestParam(name = "answer", required = true)String answer, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		QuizDao quizDao = ac.getBean(QuizDao.class);
		
		boolean isGoodQuiz = true;
		
		response.setCharacterEncoding("UTF-8");
		
		if (isGoodQuiz && title.length() > 25) {
			response.setContentType("text/html; charset=utf8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodQuiz = false;
			try {
				PrintWriter pw = response.getWriter();
				pw.append("<script>");
				pw.append("alert('제목은 25자 이내로 작성해야 합니다.'); history.go(-1);");
				pw.append("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		if (isGoodQuiz && content.length() > 500) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodQuiz = false;
			try {
				PrintWriter pw = response.getWriter();
				pw.append("<script>");
				pw.append("alert('내용은 500자 이내로 작성해야 합니다.'); history.go(-1);");
				pw.append("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		if (isGoodQuiz && title.length() == 0 || content.length() == 0) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodQuiz = false;
			try {
				PrintWriter pw = response.getWriter();
				pw.append("<script>");
				pw.append("alert('제목과 내용을 입력하세요.'); history.go(-1);");
				pw.append("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		if (isGoodQuiz && answer.length() == 0) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodQuiz = false;
			try {
				PrintWriter pw = response.getWriter();
				pw.append("<script>");
				pw.append("alert('정답을 입력하세요.'); history.go(-1);");
				pw.append("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		if (isGoodQuiz && session.getAttribute("id") == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodQuiz = false;
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
		
		if (isGoodQuiz) {
			Quiz quiz = new Quiz();
			quiz.setUser_id((String) session.getAttribute("id"));
			quiz.setTitle(title);
			quiz.setContent(content);
			quiz.setSolved_count(0);
			quiz.setCreated_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")).substring(0, 21));
			quiz.setQuiz_type(quizType);
			quiz.setAnswer(answer);
			
			System.out.println(quizDao.selectLast());
			if (quizDao.selectAllQuiz().size() > 0) {
				quiz.setQuiz_id(quizDao.selectLast().get(0).getQuiz_id() + 1);
			} else {
				quiz.setQuiz_id(1);
			}
			System.out.println(quiz);
			
			quizDao.insert(quiz);
			
			response.setContentType("text/html; charset=UTF-8");
			try {
				response.sendRedirect("/userqq/quiz?page=1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		return;
	}
}
