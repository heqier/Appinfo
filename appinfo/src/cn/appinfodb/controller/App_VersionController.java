package cn.appinfodb.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.appinfodb.pojo.App_Version;
import cn.appinfodb.pojo.Dev_User;
import cn.appinfodb.service.app_info.App_InfoService;
import cn.appinfodb.service.app_version.App_VersionService;

@Controller
@RequestMapping("/version")
public class App_VersionController {
	
	@Autowired
	private App_VersionService app_VersionService;
	@Autowired
	private App_InfoService app_InfoService;
	
	@RequestMapping(value="/flatform/app/appversionadd")
	public String appversionadd(@RequestParam String id,Model model) {
		List<App_Version> appVersionList=app_VersionService.getApp_VersionList(Integer.parseInt(id));
		model.addAttribute("appVersionList", appVersionList);
		
		model.addAttribute("appId", id);
		return "/developer/appversionadd";
	}
	
	@RequestMapping(value="/flatform/app/addversionsave")
	public String addversionsave(@RequestParam(value = "a_downloadLink", required = false) MultipartFile attachs,
			App_Version version,HttpSession session, HttpServletRequest request) {
		String a_downloadLink=null;
		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"version");
		if(!attachs.isEmpty()) {
			String oldFileName=attachs.getOriginalFilename();
			String prefix=FilenameUtils.getExtension(oldFileName);
			int filesize=500000000;
			if(attachs.getSize()>filesize){
				request.setAttribute("fileUploadError", "上传大小不能超过500Mb");
				return "/developer/appversionadd";
			}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")
					||prefix.equalsIgnoreCase("apk")) {
				String fileName=System.currentTimeMillis()+version.getVersionNo()+".apk";
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
					return "/developer/appversionadd";
				}
				a_downloadLink=path+File.separator+fileName;
			}else {
				request.setAttribute("fileUploadError", "文件格式不正确！");
				return "/developer/appversionadd";
			}
			
		}
		version.setCreatedBy(((Dev_User)session.getAttribute("devUser")).getId());
		version.setCreationDate(new Date());
		version.setApkLocPath(a_downloadLink);
		String str=a_downloadLink;
		String downloadLink=str.substring(str.lastIndexOf("/")+76, str.length());
		version.setDownloadLink(downloadLink);
		String apkFileName=str.substring(str.lastIndexOf("\\")+1, str.length());
		version.setApkFileName(apkFileName);
		try {
			if(app_VersionService.addApp_Version(version)) {
				if(app_InfoService.modifyApp_InfoByVersionId(version.getAppId(),version.getId())) {
					return "redirect:/info/flatform/app/list";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/developer/appversionadd";
	}
	
	@RequestMapping(value="/flatform/app/appversionmodify")
	public String appversionmodify(@RequestParam String vid,@RequestParam String aid,Model model) {
		List<App_Version> appVersionList=app_VersionService.getApp_VersionList(Integer.parseInt(aid));
		App_Version version=app_VersionService.App_VersionById(Integer.parseInt(vid));
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute("appVersion", version);
		return "/developer/appversionmodify";
	}
	
	@RequestMapping(value="/flatform/app/appversionmodifysave")
	public String appversionmodifysave(App_Version version,HttpSession session) {
		version.setModifyBy(((Dev_User)session.getAttribute("devUser")).getId());
		version.setModifyDate(new Date());
		if(app_VersionService.modifyApp_Version(version)) {
			return "redirect:/info/flatform/app/list";
		}
		return "/developer/appversionmodify";
	}
	
}
