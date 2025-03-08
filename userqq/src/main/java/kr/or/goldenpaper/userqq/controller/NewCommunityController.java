package kr.or.goldenpaper.userqq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.CommunityDao;
import kr.or.goldenpaper.userqq.dto.Community;

@Controller
public class NewCommunityController {
	@RequestMapping(value="/newcommunity", method=RequestMethod.GET)
	public void communityPageGet(HttpServletRequest request) {
		
	}
	
	@ResponseBody
	@RequestMapping(value="/newcommunity", method=RequestMethod.POST)
	public void newPost(@RequestParam(name = "title", required = true)String title, @RequestParam(name = "content", required = true)String content, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CommunityDao communityDao = ac.getBean(CommunityDao.class);
		
		boolean isGoodPost = true;
		
		response.setCharacterEncoding("UTF-8");
		
		if (isGoodPost && title.length() > 25) {
			response.setContentType("text/html; charset=utf8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodPost = false;
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
		if (isGoodPost && content.length() > 500) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodPost = false;
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
		if (isGoodPost && title.length() == 0 || content.length() == 0) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodPost = false;
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
		if (isGoodPost && session.getAttribute("id") == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			isGoodPost = false;
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
		
		if (isGoodPost) {
			Community community = new Community();
			community.setUser_id((String) session.getAttribute("id"));
			community.setTitle(title);
			community.setContent(content);
			community.setLike_count(0);
			community.setCreated_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")).substring(0, 21));
			
			System.out.println(communityDao.selectLast());
			if (communityDao.selectAllCommunity().size() > 0) {
				community.setPost_id(communityDao.selectLast().get(0).getPost_id() + 1);
			} else {
				community.setPost_id(1);
			}
			System.out.println(community);
			
			communityDao.insert(community);
			
			response.setContentType("text/html; charset=UTF-8");
			try {
				response.sendRedirect("/userqq/community?page=1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		return;
	}
}
