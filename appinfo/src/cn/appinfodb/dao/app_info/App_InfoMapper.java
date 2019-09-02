package cn.appinfodb.dao.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Info;

public interface App_InfoMapper {
	//根据条件查询App信息列表
	public List<App_Info> getApp_InfoList(App_Info info);
	//根据条件查询app信息数量
	public Integer getApp_InfoCount(App_Info info);
	//新增app信息
	public int addApp_Info(App_Info info);
	//查询是否存在相同的APKName
	public App_Info getApp_InfoByAPKName(@Param("APKName")String APKName);
	//根据id查询app信息
	public App_Info getApp_InfoById(@Param("id")Integer id);
	//修改app信息
	public int modifyApp_Info(App_Info info);
}
