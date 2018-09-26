package xin.snowsteps.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xin.snowsteps.common.Const;
import xin.snowsteps.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	@RequestMapping("login.do")
	public String login(String username, HttpServletRequest req) {		
		boolean flag = iUserService.login(username);
		req.getSession().setAttribute(Const.CURRENT_USER, username);
		if(flag)
			return "main";
		else
			return "login";		
	}
	
	@RequestMapping("logout.do")
	public String loginout(HttpServletRequest req) {		
		req.getSession().removeAttribute(Const.CURRENT_USER);
		Const.MANAGER = "";
		return "login";
		
	}
}
