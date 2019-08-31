package cn.appinfodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.appinfodb.service.app_category.App_CategoryService;

@Controller
@RequestMapping("/category")
public class App_CategoryController {
	
	@Autowired
	private App_CategoryService app_CategoryService;
	
	@RequestMapping(value="/categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object categorylevellist(@RequestParam String pid) {
		return JSON.toJSONString(app_CategoryService.categoryLevel1List(Integer.parseInt(pid)));
	}
}
