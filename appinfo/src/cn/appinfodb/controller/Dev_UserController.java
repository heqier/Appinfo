package cn.appinfodb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appinfodb.pojo.Dev_User;
import cn.appinfodb.service.dev_user.Dev_UserService;

@Controller
@RequestMapping("/dev")
public class Dev_UserController {
	
	@Autowired
	private Dev_UserService dev_UserService;
	
	
	/**
	 * 跳转到App开发者平台
	 * @return
	 */
	@RequestMapping(value="/login")
	public String devlogin() {
		return "devlogin";
	}
	
	/**
	 * 跳转到App开发者平台首页
	 * @return
	 */
	@RequestMapping(value="/flatform/main")
	public String main() {
		return "developer/main";
	}
	
	/**
	 * 登陆验证
	 * @return
	 */
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String devDoLogin(Dev_User user,HttpSession session,HttpServletRequest request) {
		Dev_User duser=dev_UserService.doLogin(user);
		if(null!=duser) {
			session.setAttribute("devUser", duser);
			return "redirect:/dev/flatform/main";
		} else {
			request.setAttribute("error", "用户名或密码不正确");
			return "login";
		}
	}
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("devUser");
		return "redirect:/dev/login";
	}
	
	@RequestMapping(value="/flatform/app/list")
	public String getDev_UserList() {
		return null;
	}
}
