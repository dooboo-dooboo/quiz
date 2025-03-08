package kr.or.goldenpaper.userqq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.AccountDao;

@Controller
public class MypageController {
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String myPageGet(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		AccountDao accountDao = ac.getBean(AccountDao.class);
		request.setAttribute("solvedQuizCount", accountDao.selectAllClearedQuiz((String)session.getAttribute("id")).size());
		
		return "mypage";
	}
}
