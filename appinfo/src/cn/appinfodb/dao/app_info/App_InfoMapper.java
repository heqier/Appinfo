package cn.appinfodb.dao.app_info;

import java.util.List;

import cn.appinfodb.pojo.App_Info;

public interface App_InfoMapper {
	//根据条件查询App信息列表
	public List<App_Info> getApp_InfoList(App_Info info);
	//根据条件查询app信息数量
	public Integer getApp_InfoCount(App_Info info);
}
