package cn.appinfodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.appinfodb.pojo.Data_Dictionary;
import cn.appinfodb.service.data_dictionary.Data_DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class Data_DictionaryController {
	
	@Autowired
	private Data_DictionaryService data_DictionaryService;
	
	
	@RequestMapping(value="/datadictionarylist.json")
	@ResponseBody
	public Object datadictionarylist() {
		List<Data_Dictionary> flatFormList=data_DictionaryService.flatFormList();
		return JSON.toJSONString(flatFormList);
	}
}
