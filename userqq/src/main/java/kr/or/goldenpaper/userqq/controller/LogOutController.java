package kr.or.goldenpaper.userqq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogOutController {
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void mainPageGet() {
		//null
	}
}
