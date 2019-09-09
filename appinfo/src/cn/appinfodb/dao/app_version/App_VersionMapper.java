package cn.appinfodb.dao.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Version;

public interface App_VersionMapper {
	//查询历史版本信息列表
	public List<App_Version> getApp_VersionList(@Param("id")Integer id);
	//新增app版本信息
	public int addApp_Version(App_Version version);
	//根据id查询app版本信息
	public App_Version App_VersionById(@Param("id")Integer id);
	//修改app版本信息
	public int modifyApp_Version(App_Version version);
	//根据appId删除app版本信息
	public int delApp_Version(@Param("appId")Integer appId);
}
