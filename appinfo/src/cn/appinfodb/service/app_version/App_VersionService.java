package cn.appinfodb.service.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Version;

public interface App_VersionService {
	//查询历史版本信息列表
	public List<App_Version> getApp_VersionList(Integer id);
	//新增app版本信息
	public boolean addApp_Version(App_Version version)throws Exception ;
	//根据id查询app版本信息
	public App_Version App_VersionById(Integer id);
	//修改app版本欣喜
	public boolean modifyApp_Version(App_Version version);
	//根据appId删除app版本信息
	public boolean delApp_Version(Integer appId);
}
