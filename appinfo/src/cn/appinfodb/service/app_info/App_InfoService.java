package cn.appinfodb.service.app_info;

import java.util.List;

import cn.appinfodb.pojo.App_Info;

public interface App_InfoService {
	//����������ѯApp��Ϣ�б�
	public List<App_Info> getApp_InfoList(App_Info info);
	//����������ѯapp��Ϣ����
	public Integer getApp_InfoCount(App_Info info);
}
