package kr.or.goldenpaper.userqq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/main", method=RequestMethod.GET)
	public void mainPageGet() {
		// 로그인 여부에 따라 페이지 모습 결정
	}
}
