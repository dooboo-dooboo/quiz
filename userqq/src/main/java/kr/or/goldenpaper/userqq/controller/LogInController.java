package kr.or.goldenpaper.userqq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.AccountDao;
import kr.or.goldenpaper.userqq.dto.Account;

@Controller
public class LogInController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void mainPageGet() {
		//null
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST, produces = "text/html; charset=utf8")
	public void signIn(@RequestParam(name = "id", required = true)String id, @RequestParam(name = "password", required = true)String password, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		AccountDao accountDao = ac.getBean(AccountDao.class);
		
		boolean isNotCorrect = true;
		System.out.println(accountDao.selectAllAccount());
		for (Account account : accountDao.selectAllAccount()) {
			if (account.getId().equals(id) && BCrypt.checkpw(password, account.getPassword())) {
				response.setContentType("text/html; charset=UTF-8");
				
				session.setAttribute("id", id);
				session.setAttribute("nickname", account.getNickname());
				
				isNotCorrect = false;
				try {
					response.sendRedirect("/userqq/main");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (isNotCorrect) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('아이디 혹은 비밀번호가 잘못되었습니다.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
