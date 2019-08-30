package cn.appinfodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appinfodb.pojo.App_Info;
import cn.appinfodb.service.app_info.App_InfoService;
import cn.appinfodb.service.dev_user.Dev_UserService;

@Controller
@RequestMapping("/info")
public class App_InfoController {
	
	@Autowired
	private App_InfoService app_InfoService;
	
	
	@RequestMapping(value="/flatform/app/list")
	public String getApp_InfoList(Model model,@RequestParam(value = "pageIndex", required = false)String currentPageNo) {
		int currentPage=0;
		if(currentPageNo==null||currentPageNo=="") {
			currentPageNo="1";
		}
		currentPage=Integer.parseInt(currentPageNo);
		App_Info info=new App_Info();
		info.setCurrentPageNo(currentPage);
		info.setPageSize(5);
		int totalCount=app_InfoService.getApp_InfoCount(info);
		int totalPageCount=totalCount%info.getPageSize()==0?(totalCount/info.getPageSize()):(totalCount/info.getPageSize()+1);
		List<App_Info> appInfoList=app_InfoService.getApp_InfoList(info);
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPage);
		model.addAttribute("totalPageCount", totalPageCount);
		return "/developer/appinfolist";
	}
}
