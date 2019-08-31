package cn.appinfodb.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.appinfodb.pojo.App_Category;
import cn.appinfodb.pojo.App_Info;
import cn.appinfodb.pojo.Data_Dictionary;
import cn.appinfodb.pojo.Dev_User;
import cn.appinfodb.service.app_category.App_CategoryService;
import cn.appinfodb.service.app_info.App_InfoService;
import cn.appinfodb.service.data_dictionary.Data_DictionaryService;
import cn.appinfodb.service.dev_user.Dev_UserService;

@Controller
@RequestMapping("/info")
public class App_InfoController {
	
	@Autowired
	private App_InfoService app_InfoService;
	@Autowired
	private App_CategoryService app_CategoryService;
	@Autowired
	private Data_DictionaryService data_DictionaryService;
	
	
	
	/**
	 * 查询app信息
	 * @param info
	 * @param model
	 * @param currentPageNo
	 * @return
	 */
	@RequestMapping(value="/flatform/app/list")
	public String getApp_InfoList(App_Info info,Model model,@RequestParam(value = "pageIndex", required = false)String currentPageNo) {
		int currentPage=0;
		if(currentPageNo==null||currentPageNo=="") {
			currentPageNo="1";
		}
		currentPage=Integer.parseInt(currentPageNo);
		info.setCurrentPageNo(currentPage);
		info.setPageSize(5);
		int totalCount=app_InfoService.getApp_InfoCount(info);
		int totalPageCount=totalCount%info.getPageSize()==0?(totalCount/info.getPageSize()):(totalCount/info.getPageSize()+1);
		List<App_Info> appInfoList=app_InfoService.getApp_InfoList(info);
		List<App_Category> categoryLevel1List=app_CategoryService.categoryLevel1List(0);
		List<Data_Dictionary> flatFormList=data_DictionaryService.flatFormList();
		List<Data_Dictionary> statusList=data_DictionaryService.statusList();
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
		return "/developer/appinfolist";
	}
	//跳转到add页面
	@RequestMapping(value="/flatform/app/appinfoadd")
	public String appinfoadd(Model model) {
		return "/developer/appinfoadd";
	}
	
	/**
	 * 新增app信息数据
	 * @param info
	 * @param session
	 * @param request
	 * @param attachs
	 * @return
	 */
	@RequestMapping(value="/flatform/app/appinfoaddsave",method=RequestMethod.POST)
	public String appinfoaddsave(App_Info info,HttpSession session, HttpServletRequest request,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attachs) {
		String a_logoPicPath=null;
		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		if(!attachs.isEmpty()) {
			String oldFileName=attachs.getOriginalFilename();
			String prefix=FilenameUtils.getExtension(oldFileName);
			int filesize=500000;
			if(attachs.getSize()>filesize) {
				request.setAttribute("fileUploadError", "上传大小不能超过500kb");
				return "/developer/appinfoadd";
			}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
				String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
				File targetFile=new File(path,fileName);
				if(targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attachs.transferTo(targetFile);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					request.setAttribute("fileUploadError", "文件上传失败！");
					return "/developer/appinfoadd";
				}
				a_logoPicPath=path+File.separator+fileName;
			}else {
				request.setAttribute("fileUploadError", "文件格式不正确！");
				return "/developer/appinfoadd";
			}
		}
		info.setCreatedBy(((Dev_User)session.getAttribute("devUser")).getId());
		info.setCreationDate(new Date());
		String str = a_logoPicPath;
        String logoPicPath = str.substring(str.lastIndexOf("/")+76,str.length());
        System.out.println(logoPicPath);
		info.setLogoLocPath(a_logoPicPath);
		info.setLogoPicPath(logoPicPath);
		if(app_InfoService.addApp_Info(info)) {
			return "redirect:/info/flatform/app/list";
		}
		return "/developer/appinfoadd";
	}
	
	//查询是否存在相同的APKName
	@RequestMapping(value="/apkexist.json")
	@ResponseBody
	public Object getAPKName(@RequestParam String APKName,Model model) {
		App_Info info=app_InfoService.getApp_InfoByAPKName(APKName);
		HashMap<String, String> result = new HashMap<String, String>();
		if(APKName==null||APKName=="") {
			result.put("APKName", "empty");
		}else if(null!=info) {
			result.put("APKName", "exist");
		}else if(null==info){
			result.put("APKName", "noexist");
		}
		return JSONArray.toJSONString(result);
	}
	
}
