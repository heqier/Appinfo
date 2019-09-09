package cn.appinfodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appinfodb.pojo.App_Category;
import cn.appinfodb.pojo.App_Info;
import cn.appinfodb.pojo.App_Version;
import cn.appinfodb.pojo.Data_Dictionary;
import cn.appinfodb.service.app_category.App_CategoryService;
import cn.appinfodb.service.app_info.App_InfoService;
import cn.appinfodb.service.app_version.App_VersionService;
import cn.appinfodb.service.data_dictionary.Data_DictionaryService;

@Controller
@RequestMapping("/backend")
public class Backend_infoController {
	
	@Autowired
	private App_InfoService app_InfoService;
	@Autowired
	private App_CategoryService app_CategoryService;
	@Autowired
	private Data_DictionaryService data_DictionaryService;
	@Autowired
	private App_VersionService app_VersionService;
	
	/**
	 * ≤È—Øapp–≈œ¢
	 * 
	 * @param info
	 * @param model
	 * @param currentPageNo
	 * @return
	 */
	@RequestMapping(value = "/app/list")
	public String getApp_InfoList(App_Info info, Model model,
			@RequestParam(value = "pageIndex", required = false) String currentPageNo) {
		int currentPage = 0;
		if (currentPageNo == null || currentPageNo == "") {
			currentPageNo = "1";
		}
		info.setStatus(1);
		currentPage = Integer.parseInt(currentPageNo);
		info.setCurrentPageNo(currentPage);
		info.setPageSize(5);
		int totalCount = app_InfoService.getApp_InfoCount(info);
		int totalPageCount = totalCount % info.getPageSize() == 0 ? (totalCount / info.getPageSize())
				: (totalCount / info.getPageSize() + 1);
		List<App_Info> appInfoList = app_InfoService.getApp_InfoList(info);
		List<App_Category> categoryLevel1List = app_CategoryService.categoryLevel1List(0);
		List<Data_Dictionary> flatFormList = data_DictionaryService.flatFormList();
		List<Data_Dictionary> statusList = data_DictionaryService.statusList();
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("statusList", statusList);

		model.addAttribute("querySoftwareName", info.getSoftwareName());
		model.addAttribute("queryStatus", info.getStatus());
		model.addAttribute("queryFlatformId", info.getFlatformId());
		model.addAttribute("queryCategoryLevel1", info.getCategoryLevel1());
		model.addAttribute("queryCategoryLevel2", info.getCategoryLevel2());
		model.addAttribute("queryCategoryLevel3", info.getCategoryLevel3());

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPage);
		model.addAttribute("totalPageCount", totalPageCount);
		return "/backend/applist";
	} 
	
	@RequestMapping(value="/app/check")
	public String getAppinfoById(@RequestParam String aid,@RequestParam String vid,Model model) {
		App_Info info=app_InfoService.getApp_InfoById(Integer.parseInt(aid));
		App_Version version=app_VersionService.App_VersionById(Integer.parseInt(vid));
		model.addAttribute("appInfo", info);
		model.addAttribute("appVersion", version);
		return "/backend/appcheck";
	}
	
	@RequestMapping(value="/app/checksave")
	public String modifyAppinfo(App_Info info) {
		if(app_InfoService.modifyApp_Info(info)) {
			return "redirect:/backend/app/list";
		}
		return "redirect:/backend/app/check";
	}
}
