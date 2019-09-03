package cn.appinfodb.service.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Info;

public interface App_InfoService {
	//����������ѯApp��Ϣ�б�
	public List<App_Info> getApp_InfoList(App_Info info);
	//����������ѯapp��Ϣ����
	public Integer getApp_InfoCount(App_Info info);
	//����app��Ϣ
	public boolean addApp_Info(App_Info info);
	//��ѯ�Ƿ������ͬ��APKName
	public App_Info getApp_InfoByAPKName(String APKName);
	//����id��ѯapp��Ϣ
	public App_Info getApp_InfoById(Integer id);
	//�޸�app��Ϣ
	public boolean modifyApp_Info(App_Info info);
	//�޸�versionId
	public boolean modifyApp_InfoByVersionId(Integer id,Integer versionId);
	//����Idɾ��App��Ϣ
	public boolean delApp_Info(Integer id);
}
