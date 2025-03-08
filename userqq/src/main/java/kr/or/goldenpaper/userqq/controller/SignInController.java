package kr.or.goldenpaper.userqq.controller;

import kr.or.goldenpaper.userqq.dao.AccountDao.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.AccountDao;
import kr.or.goldenpaper.userqq.dto.Account;

@Controller
public class SignInController {

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void mainPageGet() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		AccountDao accountDao = ac.getBean(AccountDao.class);
	}
	
	@RequestMapping(path="/signup", method=RequestMethod.POST, produces = "text/html; charset=utf8")
	public void signIn(@RequestParam(name = "id", required = true)String id, @RequestParam(name = "nickname", required = true)String nickname, @RequestParam(name = "password", required = true)String password, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		AccountDao accountDao = ac.getBean(AccountDao.class);
		boolean isGoodAccount = true;
		if (id.length() > 20 || password.length() > 20 || nickname.length() > 20) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('id, 닉네임, 비밀번호는 20글자 이하여야 합니다.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isGoodAccount = false;
		}
		if (id.length() == 0 || password.length() == 0 || nickname.length() == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('id, 닉네임, 비밀번호를 모두 입력하세요.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isGoodAccount = false;
		}
		for (Account account : accountDao.selectAllAccount()) {
			if (account.getId().equals(id)) {
				response.setContentType("text/html; charset=UTF-8");
				try {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('이미 존재하는 아이디입니다.'); history.go(-1);</script>");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				isGoodAccount = false;
				break;
			}
			if (account.getNickname().equals(nickname)) {
				response.setContentType("text/html; charset=UTF-8");
				try {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('이미 존재하는 닉네임입니다.'); history.go(-1);</script>");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				isGoodAccount = false;
				break;
			}
		}
		
		if (isGoodAccount) {
			String field = "[]";
			JSONArray jsonArray = new JSONArray(field);
			Account newAccount = new Account();
			newAccount.setId(id);
			newAccount.setNickname(nickname);
			newAccount.setPassword(accountDao.regBcrypt(password));
			newAccount.setXp(0);
			newAccount.setCleared_quiz(jsonArray);
			System.out.println("new : " + newAccount);
			accountDao.insert(newAccount);
			
			try {
				response.sendRedirect("/userqq/main");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
