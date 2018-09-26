package xin.snowsteps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("index.do")
	public String login() {
		return "login";
	}
	
	@RequestMapping("top.do")
	public String top() {
		return "top";
	}
	
	@RequestMapping("left.do")
	public String left() {
		return "left";
	}
	
	@RequestMapping("welcome.do")
	public String welcome() {
		return "welcome";
	}
	
}
