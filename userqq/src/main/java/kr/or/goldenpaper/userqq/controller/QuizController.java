package kr.or.goldenpaper.userqq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.AccountDao;
import kr.or.goldenpaper.userqq.dao.CommunityDao;
import kr.or.goldenpaper.userqq.dao.QuizDao;
import kr.or.goldenpaper.userqq.dto.Community;
import kr.or.goldenpaper.userqq.dto.Quiz;

@Controller
public class QuizController {
	@RequestMapping(value="/quiz", method=RequestMethod.GET)
	public void quizPageGet(HttpServletRequest request) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		/*
		    List<String> quizId = (List<String>)request.getAttribute("quizIds");
			List<String> titles = (List<String>)request.getAttribute("titles");
			List<String> writers = (List<String>)request.getAttribute("writers");
			List<String> dates = (List<String>)request.getAttribute("dates");
			List<String> solvedCount = (List<String>)request.getAttribute("solveCounts");
			List<String> types = (List<String>)request.getAttribute("types");
		*/
		
		QuizDao quizDao = ac.getBean(QuizDao.class);
		AccountDao accountDao = ac.getBean(AccountDao.class);
		List<Quiz> pageQuiz = quizDao.getTen(Integer.parseInt(request.getParameter("page")) * 10 - 10);
		List<String> quizId = new ArrayList<>();
		List<String> titles = new ArrayList<>();
		List<String> writers = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		List<String> solvedCount = new ArrayList<>();
		List<String> types = new ArrayList<>();
		
		for (Quiz quiz : pageQuiz) {
			quizId.add(Integer.toString(quiz.getQuiz_id()));
			titles.add(quiz.getTitle());
			writers.add(accountDao.selectNickname(quiz.getUser_id()));
			dates.add(quiz.getCreated_time());
			solvedCount.add(Integer.toString(quiz.getSolved_count()));
			types.add(quiz.getQuiz_type());
		}
		
		request.setAttribute("quizIds", quizId);
		request.setAttribute("titles", titles);
		request.setAttribute("writers", writers);
		request.setAttribute("dates", dates);
		request.setAttribute("solveCounts", solvedCount);
		request.setAttribute("types", types);
		request.setAttribute("maxPage", Math.ceil(quizDao.getQuizCount() / 10.0));
	}
}
