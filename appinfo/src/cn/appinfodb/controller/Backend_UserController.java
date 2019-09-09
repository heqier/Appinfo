package cn.appinfodb.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appinfodb.pojo.Backend_User;
import cn.appinfodb.service.user.Backend_UserService;

@Controller
@RequestMapping("/manger")
public class Backend_UserController {
	@Autowired
	private Backend_UserService userService;
	
	private Logger logger=Logger.getLogger(Backend_UserController.class);
	
	@RequestMapping(value="/login")
	public String login() {
		return "/backendlogin";
	}
	
	@RequestMapping(value="/dologin")
	public String dologin(HttpSession session,@RequestParam(value="userCode",required=false)String userCode,
			@RequestParam(value="userPassword",required=false)String userPassword) {
			logger.debug("½øÈëÅÐ¶ÏµÇÂ¼------------------------------");
			logger.debug(userCode+"---------------");
			logger.debug(userPassword+"--------------");
			Backend_User backend=userService.getuser(userCode, userPassword);
			if(backend!=null) {
				logger.debug("ÕËºÅ/ÃÜÂë------------"+backend.getUserCode()+""+backend.getUserPassword());
				 logger.debug("hjkashdahkfhsdfhsadh>>>>>>>>>>"+backend.getUserName());
				 session.setAttribute("user", backend);
				 return "redirect:/manger/backend/main";
			}
				return "/backendlogin";
	}
	@RequestMapping(value="/backend/main")
	public String main() {
		return "/backend/main";
	}
<<<<<<< HEAD
	/**
	 * ×¢Ïú
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index.jsp";
	}
=======
>>>>>>> branch 'master' of https://github.com/heqier/Appinfo.git
}

