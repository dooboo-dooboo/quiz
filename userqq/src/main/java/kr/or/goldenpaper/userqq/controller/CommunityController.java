package kr.or.goldenpaper.userqq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.goldenpaper.userqq.config.ApplicationConfig;
import kr.or.goldenpaper.userqq.dao.AccountDao;
import kr.or.goldenpaper.userqq.dao.CommunityDao;
import kr.or.goldenpaper.userqq.dto.Community;
import kr.or.goldenpaper.userqq.returns.CommunityReturn;

@Controller
public class CommunityController {
	@RequestMapping(value="/community", method=RequestMethod.GET)
	public void communityPageGet(HttpServletRequest request) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CommunityDao communityDao = ac.getBean(CommunityDao.class);
		AccountDao accountDao = ac.getBean(AccountDao.class);
		List<Community> pagePost = communityDao.getTen(Integer.parseInt(request.getParameter("page")) * 10 - 10);
		List<String> titles = new ArrayList<>();
		List<String> contents = new ArrayList<>();
		List<String> writers = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		
		for (Community com : pagePost) {
			titles.add(com.getTitle());
			contents.add(com.getContent());
			writers.add(accountDao.selectNickname(com.getUser_id()));
			dates.add(com.getCreated_time());
		}
		
		request.setAttribute("titles", titles);
		request.setAttribute("contents", contents);
		request.setAttribute("writers", writers);
		request.setAttribute("dates", dates);
		request.setAttribute("maxPage", Math.ceil(communityDao.getCommunityCount() / 10.0));
		System.out.println(request.getAttribute("maxPage"));
	}
	
	/*
	@RequestMapping(path="/community", method=RequestMethod.POST)
	public void uploadPost(@RequestParam(name = "title", required = true)String title, @RequestParam(name = "content", required = true)String content, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CommunityDao communityDao = ac.getBean(CommunityDao.class);
		boolean isGoodPost = true;
		
		if (title.length() > 25) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('제목은 25자 이내로 작성해야 합니다.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isGoodPost = false;
		}
		if (content.length() > 500) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('내용은 500자 이내로 작성해야 합니다.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isGoodPost = false;
		}
		if (title.length() == 0 || content.length() == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('제목과 내용을 입력하세요.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isGoodPost = false;
		}
		if (session.getAttribute("id") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('로그인 후 이용 가능합니다.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isGoodPost = false;
			try {
				response.sendRedirect("/userqq/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			
			try {
				response.sendRedirect("/userqq/community");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(path="/community-back", method=RequestMethod.POST)
	public void pageBack(@RequestParam(name = "back", required = true)int page, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		request.setAttribute("page", page - 1);
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CommunityDao communityDao = ac.getBean(CommunityDao.class);
		List<Community> pagePost = communityDao.getTen((page - 2) * 10);
		List<String> titles = new ArrayList<>();
		List<String> contents = new ArrayList<>();
		List<String> writers = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		
		for (Community com : pagePost) {
			titles.add(com.getTitle());
			contents.add(com.getContent());
			writers.add(com.getUser_id());
			dates.add(com.getCreated_time());
		}
		
		request.setAttribute("titles", titles);
		request.setAttribute("contents", contents);
		request.setAttribute("writers", writers);
		request.setAttribute("dates", dates);
	}
	
	@RequestMapping(path="/community-next", method=RequestMethod.POST)
	public void pageNext(@RequestParam(name = "next", required = true)int page, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		request.setAttribute("page", page + 1);
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CommunityDao communityDao = ac.getBean(CommunityDao.class);
		List<Community> pagePost = communityDao.getTen(page * 10);
		List<String> titles = new ArrayList<>();
		List<String> contents = new ArrayList<>();
		List<String> writers = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		
		for (Community com : pagePost) {
			titles.add(com.getTitle());
			contents.add(com.getContent());
			writers.add(com.getUser_id());
			dates.add(com.getCreated_time());
		}
		
		request.setAttribute("titles", titles);
		request.setAttribute("contents", contents);
		request.setAttribute("writers", writers);
		request.setAttribute("dates", dates);
	}*/
}
